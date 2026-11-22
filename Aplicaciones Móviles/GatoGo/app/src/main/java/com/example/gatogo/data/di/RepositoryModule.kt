package com.example.gatogo.data.di

import com.example.gatogo.data.repository.AvistamientoRepositoryImp
import com.example.gatogo.data.repository.GatoRepositoryImp
import com.example.gatogo.data.repository.UsuarioRepositoryImp
import com.example.gatogo.domain.repository.AvistamientoRepository
import com.example.gatogo.domain.repository.GatoRepository
import com.example.gatogo.domain.repository.UsuarioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUsuarioRepository(
        repository: UsuarioRepositoryImp
    ): UsuarioRepository

    @Binds
    @Singleton
    abstract fun bindGatoRepository(
        repository: GatoRepositoryImp
    ): GatoRepository

    @Binds
    @Singleton
    abstract fun bindAvistamientoRepository(
        repository: AvistamientoRepositoryImp
    ): AvistamientoRepository
}
