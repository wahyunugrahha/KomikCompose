package com.example.submissioncompose.di

import com.example.submissioncompose.data.KomikRepository

object Injection {
    fun provideRepository(): KomikRepository {
        return KomikRepository.getInstance()
    }
}