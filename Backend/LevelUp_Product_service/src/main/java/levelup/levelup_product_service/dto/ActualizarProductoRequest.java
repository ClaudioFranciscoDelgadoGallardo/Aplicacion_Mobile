package levelup.levelup_product_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarProductoRequest {

    private String codigo;

    @Size(max = 200, message = "El nombre debe tener máximo 200 caracteres")
    private String nombre;

    private String descripcion;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precioVenta;

    private Long categoriaId;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stockActual;

    private String imagenUrl;

    private Boolean destacado;

    private Boolean activo;

    private String marca;

    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo")
    private BigDecimal descuento;
}
