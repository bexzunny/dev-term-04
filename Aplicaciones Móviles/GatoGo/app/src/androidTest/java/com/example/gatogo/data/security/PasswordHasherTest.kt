package com.example.gatogo.data.security

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordHasherTest {
    @Test
    fun hashUsesUniqueSaltAndCanBeVerified() {
        val first = PasswordHasher.hash("segura-123")
        val second = PasswordHasher.hash("segura-123")

        assertNotEquals(first, second)
        assertTrue(PasswordHasher.verify("segura-123", first))
        assertFalse(PasswordHasher.verify("otra", first))
    }

    @Test
    fun recognizesLegacyPlainTextOnlyForMigration() {
        assertTrue(PasswordHasher.matchesLegacyPlainText("anterior", "anterior"))
        assertFalse(PasswordHasher.matchesLegacyPlainText("incorrecta", "anterior"))
        assertFalse(PasswordHasher.matchesLegacyPlainText("segura-123", PasswordHasher.hash("segura-123")))
    }
}
