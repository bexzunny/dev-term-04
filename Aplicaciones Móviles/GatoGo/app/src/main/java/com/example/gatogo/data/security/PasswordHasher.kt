package com.example.gatogo.data.security

import java.security.MessageDigest
import java.security.SecureRandom
import android.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/** Hash local de contraseñas con sal aleatoria y PBKDF2. */
object PasswordHasher {
    private const val ITERATIONS = 600_000
    private const val KEY_LENGTH_BITS = 256
    private const val SALT_LENGTH_BYTES = 16
    private const val PREFIX = "pbkdf2"
    private const val SHA256 = "PBKDF2WithHmacSHA256"
    private const val SHA1_FALLBACK = "PBKDF2WithHmacSHA1"
    private val random = SecureRandom()

    fun isHashed(value: String): Boolean = value.startsWith("${PREFIX}\$")

    fun hash(password: String): String {
        val algorithm = algoritmoDisponible()
        val salt = ByteArray(SALT_LENGTH_BYTES).also(random::nextBytes)
        val derived = derive(password, salt, ITERATIONS, algorithm)
        return listOf(
            PREFIX,
            algorithm,
            ITERATIONS.toString(),
            Base64.encodeToString(salt, Base64.NO_WRAP),
            Base64.encodeToString(derived, Base64.NO_WRAP)
        ).joinToString("$")
    }

    fun verify(password: String, storedValue: String): Boolean {
        if (!isHashed(storedValue)) return false
        return try {
            val parts = storedValue.split('$')
            if (parts.size != 5 || parts[0] != PREFIX) return false
            val algorithm = parts[1]
            if (algorithm != SHA256 && algorithm != SHA1_FALLBACK) return false
            val iterations = parts[2].toIntOrNull() ?: return false
            if (iterations <= 0) return false
            val salt = Base64.decode(parts[3], Base64.NO_WRAP)
            val expected = Base64.decode(parts[4], Base64.NO_WRAP)
            val actual = derive(password, salt, iterations, algorithm)
            MessageDigest.isEqual(expected, actual)
        } catch (_: Exception) {
            false
        }
    }

    fun matchesLegacyPlainText(password: String, storedValue: String): Boolean =
        !isHashed(storedValue) && MessageDigest.isEqual(
            password.toByteArray(Charsets.UTF_8),
            storedValue.toByteArray(Charsets.UTF_8)
        )

    private fun algoritmoDisponible(): String =
        runCatching { SecretKeyFactory.getInstance(SHA256) }.fold(
            onSuccess = { SHA256 },
            onFailure = { SHA1_FALLBACK }
        )

    private fun derive(
        password: String,
        salt: ByteArray,
        iterations: Int,
        algorithm: String
    ): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, iterations, KEY_LENGTH_BITS)
        return try {
            SecretKeyFactory.getInstance(algorithm).generateSecret(spec).encoded
        } finally {
            spec.clearPassword()
        }
    }
}
