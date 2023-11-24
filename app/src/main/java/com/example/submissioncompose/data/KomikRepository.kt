package com.example.submissioncompose.data

import com.example.submissioncompose.model.Komik
import com.example.submissioncompose.model.KomikData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class KomikRepository {
    private val toolList = KomikData.appList

    fun searchTools(query: String): List<Komik> {
        return toolList.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getAllTools(): Flow<List<Komik>> {
        return flowOf(toolList)
    }

    fun getToolByName(name: String): Komik {
        return toolList.first {
            it.name == name
        }
    }

    companion object {
        @Volatile
        private var instance: KomikRepository? = null

        fun getInstance(): KomikRepository =
            instance ?: synchronized(this) {
                KomikRepository().apply {
                    instance = this
                }
            }
    }
}