package com.levelup.gamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelup.gamer.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ContactFormState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val message: String = "",
    val nameError: String = "",
    val emailError: String = "",
    val phoneError: String = "",
    val messageError: String = "",
    val showErrors: Boolean = false,
    val isSubmitting: Boolean = false,
    val submitSuccess: Boolean = false
)

class ContactViewModel : ViewModel() {

    private val _formState = MutableStateFlow(ContactFormState())
    val formState: StateFlow<ContactFormState> = _formState.asStateFlow()

    fun updateName(name: String) {
        _formState.value = _formState.value.copy(name = name)
        if (_formState.value.showErrors) {
            validateName()
        }
    }

    fun updateEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
        if (_formState.value.showErrors) {
            validateEmail()
        }
    }

    fun updatePhone(phone: String) {
        _formState.value = _formState.value.copy(phone = phone)
        if (_formState.value.showErrors) {
            validatePhone()
        }
    }

    fun updateMessage(message: String) {
        _formState.value = _formState.value.copy(message = message)
        if (_formState.value.showErrors) {
            validateMessage()
        }
    }

    private fun validateName() {
        val validation = ValidationUtils.validateFullName(_formState.value.name)
        _formState.value = _formState.value.copy(
            nameError = if (!validation.first) validation.second else ""
        )
    }

    private fun validateEmail() {
        val validation = ValidationUtils.validateEmail(_formState.value.email)
        _formState.value = _formState.value.copy(
            emailError = if (!validation.first) validation.second else ""
        )
    }

    private fun validatePhone() {
        val validation = ValidationUtils.validatePhone(_formState.value.phone)
        _formState.value = _formState.value.copy(
            phoneError = if (!validation.first) validation.second else ""
        )
    }

    private fun validateMessage() {
        val validation = ValidationUtils.validateMessage(_formState.value.message)
        _formState.value = _formState.value.copy(
            messageError = if (!validation.first) validation.second else ""
        )
    }

    fun submitForm() {
        _formState.value = _formState.value.copy(showErrors = true)
        
        validateName()
        validateEmail()
        validatePhone()
        validateMessage()

        val errors = ValidationUtils.validateContactForm(
            _formState.value.name,
            _formState.value.email,
            _formState.value.phone,
            _formState.value.message
        )

        if (errors.isEmpty()) {
            viewModelScope.launch {
                _formState.value = _formState.value.copy(isSubmitting = true)
                kotlinx.coroutines.delay(1000)
                
                _formState.value = ContactFormState(submitSuccess = true)
            }
        }
    }

    fun resetForm() {
        _formState.value = ContactFormState()
    }

    fun dismissSuccessDialog() {
        _formState.value = _formState.value.copy(submitSuccess = false)
    }

    fun isFormValid(): Boolean {
        return _formState.value.name.isNotBlank() &&
                _formState.value.email.isNotBlank() &&
                _formState.value.phone.isNotBlank() &&
                _formState.value.message.isNotBlank()
    }
}
