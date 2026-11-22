package com.example.gatogo.data.di

import android.content.Context
import androidx.room.Room
import com.example.gatogo.data.database.AppDatabase
import com.example.gatogo.data.database.dao.AvistamientoDao
import com.example.gatogo.data.database.dao.GatoDao
import com.example.gatogo.data.database.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "gatogo.db"
    ).addMigrations(AppDatabase.MIGRATION_1_2).build()

    @Provides
    fun provideUsuarioDao(database: AppDatabase): UsuarioDao =
        database.usuarioDao()

    @Provides
    fun provideGatoDao(database: AppDatabase): GatoDao =
        database.gatoDao()

    @Provides
    fun provideAvistamientoDao(database: AppDatabase): AvistamientoDao =
        database.avistamientoDao()
}
