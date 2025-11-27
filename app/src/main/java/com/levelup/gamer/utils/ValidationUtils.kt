package com.levelup.gamer.utils

object ValidationUtils {

    fun validateEmail(email: String): Pair<Boolean, String> {
        return when {
            email.isBlank() -> Pair(false, "El email es requerido")
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                Pair(false, "Formato de email inválido")
            else -> Pair(true, "")
        }
    }

    fun validatePassword(password: String): Pair<Boolean, String> {
        return when {
            password.isBlank() -> Pair(false, "La contraseña es requerida")
            password.length < 6 -> Pair(false, "Mínimo 6 caracteres")
            !password.any { it.isDigit() } -> Pair(false, "Debe contener al menos un número")
            !password.any { it.isLetter() } -> Pair(false, "Debe contener al menos una letra")
            else -> Pair(true, "")
        }
    }

    fun validateFullName(name: String): Pair<Boolean, String> {
        return when {
            name.isBlank() -> Pair(false, "El nombre es requerido")
            name.length < 3 -> Pair(false, "Mínimo 3 caracteres")
            !name.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) ->
                Pair(false, "Solo se permiten letras y espacios")
            else -> Pair(true, "")
        }
    }

    fun validatePhone(phone: String): Pair<Boolean, String> {
        val cleanPhone = phone.replace(Regex("[\\s-]"), "")
        return when {
            phone.isBlank() -> Pair(false, "El teléfono es requerido")
            cleanPhone.length !in 8..9 -> Pair(false, "Debe tener 8 o 9 dígitos")
            !cleanPhone.all { it.isDigit() } -> Pair(false, "Solo se permiten números")
            !cleanPhone.startsWith("9") && cleanPhone.length == 9 ->
                Pair(false, "Debe comenzar con 9")
            else -> Pair(true, "")
        }
    }

    fun validateMessage(message: String): Pair<Boolean, String> {
        return when {
            message.isBlank() -> Pair(false, "El mensaje es requerido")
            message.length < 10 -> Pair(false, "Mínimo 10 caracteres")
            message.length > 500 -> Pair(false, "Máximo 500 caracteres")
            else -> Pair(true, "")
        }
    }

    fun validatePasswordMatch(password: String, confirmPassword: String): Pair<Boolean, String> {
        return when {
            confirmPassword.isBlank() -> Pair(false, "Confirma tu contraseña")
            password != confirmPassword -> Pair(false, "Las contraseñas no coinciden")
            else -> Pair(true, "")
        }
    }

    fun validateLoginForm(email: String, password: String): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        val emailValidation = validateEmail(email)
        if (!emailValidation.first) {
            errors["email"] = emailValidation.second
        }

        val passwordValidation = validatePassword(password)
        if (!passwordValidation.first) {
            errors["password"] = passwordValidation.second
        }

        return errors
    }

    fun validateRegisterForm(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        val nameValidation = validateFullName(name)
        if (!nameValidation.first) {
            errors["name"] = nameValidation.second
        }

        val emailValidation = validateEmail(email)
        if (!emailValidation.first) {
            errors["email"] = emailValidation.second
        }

        val passwordValidation = validatePassword(password)
        if (!passwordValidation.first) {
            errors["password"] = passwordValidation.second
        }

        val confirmValidation = validatePasswordMatch(password, confirmPassword)
        if (!confirmValidation.first) {
            errors["confirmPassword"] = confirmValidation.second
        }

        return errors
    }

    fun validateContactForm(
        name: String,
        email: String,
        phone: String,
        message: String
    ): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        val nameValidation = validateFullName(name)
        if (!nameValidation.first) {
            errors["name"] = nameValidation.second
        }

        val emailValidation = validateEmail(email)
        if (!emailValidation.first) {
            errors["email"] = emailValidation.second
        }

        val phoneValidation = validatePhone(phone)
        if (!phoneValidation.first) {
            errors["phone"] = phoneValidation.second
        }

        val messageValidation = validateMessage(message)
        if (!messageValidation.first) {
            errors["message"] = messageValidation.second
        }

        return errors
    }
}

