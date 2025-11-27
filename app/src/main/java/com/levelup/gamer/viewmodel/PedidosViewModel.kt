package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.model.Pedido
import com.levelup.gamer.model.EstadoPedido
import com.levelup.gamer.repository.pedido.PedidoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PedidosUiState(
    val pedidos: List<Pedido> = emptyList(),
    val pedidoActual: Pedido? = null,
    val isLoading: Boolean = false,
    val mostrarDetalle: Boolean = false,
    val progresandoEstado: Boolean = false
)

class PedidosViewModel(private val repository: PedidoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(PedidosUiState())
    val uiState: StateFlow<PedidosUiState> = _uiState.asStateFlow()

    fun cargarPedidos(userId: Int) {
        viewModelScope.launch {
            repository.obtenerPedidosPorUsuario(userId).collect { pedidos ->
                _uiState.value = _uiState.value.copy(pedidos = pedidos)
            }
        }
    }

    fun mostrarDetallePedido(pedido: Pedido) {
        _uiState.value = _uiState.value.copy(
            pedidoActual = pedido,
            mostrarDetalle = true
        )
    }

    fun cerrarDetalle() {
        _uiState.value = _uiState.value.copy(
            pedidoActual = null,
            mostrarDetalle = false
        )
    }

    fun iniciarProgresionAutomatica(pedidoId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(progresandoEstado = true)
            
            var pedido = repository.obtenerPedidoPorId(pedidoId) ?: return@launch
            
            // Progresión automática de estados cada 15 segundos
            while (pedido.estado != EstadoPedido.ENTREGADO) {
                delay(15000) // 15 segundos
                
                val siguienteEstado = pedido.estado.siguiente()
                if (siguienteEstado != null) {
                    repository.actualizarEstadoPedido(pedido, siguienteEstado)
                    pedido = repository.obtenerPedidoPorId(pedidoId) ?: break
                    
                    // Actualizar el pedido actual si está siendo visualizado
                    if (_uiState.value.pedidoActual?.id == pedidoId) {
                        _uiState.value = _uiState.value.copy(pedidoActual = pedido)
                    }
                } else {
                    break
                }
            }
            
            _uiState.value = _uiState.value.copy(progresandoEstado = false)
        }
    }
}
