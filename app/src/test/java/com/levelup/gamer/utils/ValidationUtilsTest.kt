package com.levelup.gamer.utils

import org.junit.Test
import org.junit.Assert.*

class ValidationUtilsTest {

    @Test
    fun `validateEmail con email valido retorna true`() {
        val result = ValidationUtils.validateEmail("test@example.com")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validateEmail con email vacio retorna false`() {
        val result = ValidationUtils.validateEmail("")
        assertFalse(result.first)
        assertEquals("El email es requerido", result.second)
    }

    @Test
    fun `validateEmail con formato invalido retorna false`() {
        val result = ValidationUtils.validateEmail("emailinvalido")
        assertFalse(result.first)
        assertEquals("Formato de email inválido", result.second)
    }

    @Test
    fun `validateEmail con email sin dominio retorna false`() {
        val result = ValidationUtils.validateEmail("test@")
        assertFalse(result.first)
    }

    @Test
    fun `validatePassword con password valido retorna true`() {
        val result = ValidationUtils.validatePassword("password123")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validatePassword con password vacio retorna false`() {
        val result = ValidationUtils.validatePassword("")
        assertFalse(result.first)
        assertEquals("La contraseña es requerida", result.second)
    }

    @Test
    fun `validatePassword con menos de 6 caracteres retorna false`() {
        val result = ValidationUtils.validatePassword("abc12")
        assertFalse(result.first)
        assertEquals("Mínimo 6 caracteres", result.second)
    }

    @Test
    fun `validatePassword sin numeros retorna false`() {
        val result = ValidationUtils.validatePassword("abcdefgh")
        assertFalse(result.first)
        assertEquals("Debe contener al menos un número", result.second)
    }

    @Test
    fun `validatePassword sin letras retorna false`() {
        val result = ValidationUtils.validatePassword("123456")
        assertFalse(result.first)
        assertEquals("Debe contener al menos una letra", result.second)
    }

    @Test
    fun `validateFullName con nombre valido retorna true`() {
        val result = ValidationUtils.validateFullName("Juan Pérez")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validateFullName con nombre vacio retorna false`() {
        val result = ValidationUtils.validateFullName("")
        assertFalse(result.first)
        assertEquals("El nombre es requerido", result.second)
    }

    @Test
    fun `validateFullName con menos de 3 caracteres retorna false`() {
        val result = ValidationUtils.validateFullName("AB")
        assertFalse(result.first)
        assertEquals("Mínimo 3 caracteres", result.second)
    }

    @Test
    fun `validateFullName con numeros retorna false`() {
        val result = ValidationUtils.validateFullName("Juan123")
        assertFalse(result.first)
        assertEquals("Solo se permiten letras y espacios", result.second)
    }

    @Test
    fun `validateFullName con caracteres especiales retorna false`() {
        val result = ValidationUtils.validateFullName("Juan@Pérez")
        assertFalse(result.first)
    }

    @Test
    fun `validatePhone con telefono chileno valido 9 digitos retorna true`() {
        val result = ValidationUtils.validatePhone("987654321")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validatePhone con telefono fijo valido 8 digitos retorna true`() {
        val result = ValidationUtils.validatePhone("22345678")
        assertTrue(result.first)
    }

    @Test
    fun `validatePhone con telefono vacio retorna false`() {
        val result = ValidationUtils.validatePhone("")
        assertFalse(result.first)
        assertEquals("El teléfono es requerido", result.second)
    }

    @Test
    fun `validatePhone con menos de 8 digitos retorna false`() {
        val result = ValidationUtils.validatePhone("1234567")
        assertFalse(result.first)
        assertEquals("Debe tener 8 o 9 dígitos", result.second)
    }

    @Test
    fun `validatePhone con mas de 9 digitos retorna false`() {
        val result = ValidationUtils.validatePhone("12345678901")
        assertFalse(result.first)
    }

    @Test
    fun `validatePhone con letras retorna false`() {
        val result = ValidationUtils.validatePhone("9876abc21")
        assertFalse(result.first)
        assertEquals("Solo se permiten números", result.second)
    }

    @Test
    fun `validatePhone celular que no empieza con 9 retorna false`() {
        val result = ValidationUtils.validatePhone("887654321")
        assertFalse(result.first)
        assertEquals("Debe comenzar con 9", result.second)
    }

    @Test
    fun `validateMessage con mensaje valido retorna true`() {
        val result = ValidationUtils.validateMessage("Este es un mensaje válido de prueba")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validateMessage con mensaje vacio retorna false`() {
        val result = ValidationUtils.validateMessage("")
        assertFalse(result.first)
        assertEquals("El mensaje es requerido", result.second)
    }

    @Test
    fun `validateMessage con menos de 10 caracteres retorna false`() {
        val result = ValidationUtils.validateMessage("Hola")
        assertFalse(result.first)
        assertEquals("Mínimo 10 caracteres", result.second)
    }

    @Test
    fun `validateMessage con mas de 500 caracteres retorna false`() {
        val longMessage = "a".repeat(501)
        val result = ValidationUtils.validateMessage(longMessage)
        assertFalse(result.first)
        assertEquals("Máximo 500 caracteres", result.second)
    }

    @Test
    fun `validatePasswordMatch con passwords iguales retorna true`() {
        val result = ValidationUtils.validatePasswordMatch("password123", "password123")
        assertTrue(result.first)
        assertEquals("", result.second)
    }

    @Test
    fun `validatePasswordMatch con passwords diferentes retorna false`() {
        val result = ValidationUtils.validatePasswordMatch("password123", "password456")
        assertFalse(result.first)
        assertEquals("Las contraseñas no coinciden", result.second)
    }

    @Test
    fun `validatePasswordMatch con confirmacion vacia retorna false`() {
        val result = ValidationUtils.validatePasswordMatch("password123", "")
        assertFalse(result.first)
        assertEquals("Confirma tu contraseña", result.second)
    }

    @Test
    fun `validateLoginForm con datos validos retorna mapa vacio`() {
        val errors = ValidationUtils.validateLoginForm("test@example.com", "password123")
        assertTrue(errors.isEmpty())
    }

    @Test
    fun `validateLoginForm con email invalido retorna error de email`() {
        val errors = ValidationUtils.validateLoginForm("emailinvalido", "password123")
        assertTrue(errors.containsKey("email"))
        assertEquals("Formato de email inválido", errors["email"])
    }

    @Test
    fun `validateLoginForm con password invalido retorna error de password`() {
        val errors = ValidationUtils.validateLoginForm("test@example.com", "123")
        assertTrue(errors.containsKey("password"))
    }

    @Test
    fun `validateRegisterForm con datos validos retorna mapa vacio`() {
        val errors = ValidationUtils.validateRegisterForm(
            "Juan Pérez",
            "test@example.com",
            "password123",
            "password123"
        )
        assertTrue(errors.isEmpty())
    }

    @Test
    fun `validateRegisterForm con passwords no coincidentes retorna error`() {
        val errors = ValidationUtils.validateRegisterForm(
            "Juan Pérez",
            "test@example.com",
            "password123",
            "password456"
        )
        assertTrue(errors.containsKey("confirmPassword"))
        assertEquals("Las contraseñas no coinciden", errors["confirmPassword"])
    }

    @Test
    fun `validateContactForm con datos validos retorna mapa vacio`() {
        val errors = ValidationUtils.validateContactForm(
            "Juan Pérez",
            "test@example.com",
            "987654321",
            "Este es un mensaje de prueba válido"
        )
        assertTrue(errors.isEmpty())
    }

    @Test
    fun `validateContactForm con multiple errores retorna todos los errores`() {
        val errors = ValidationUtils.validateContactForm(
            "AB",
            "emailinvalido",
            "123",
            "Corto"
        )
        assertEquals(4, errors.size)
        assertTrue(errors.containsKey("name"))
        assertTrue(errors.containsKey("email"))
        assertTrue(errors.containsKey("phone"))
        assertTrue(errors.containsKey("message"))
    }
}

