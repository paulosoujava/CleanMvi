package com.jorge.paulo.mvi.domain.use_case

import android.util.Log
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.domain.repository.MyRepositoryApi
import com.jorge.paulo.mvi.domain.repository.MyRepositoryLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

data class GetAnimalUseCase(
    private val repositoryApi: MyRepositoryApi,
    private val repositoryLocal: MyRepositoryLocal
) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend operator fun invoke(): List<Animal> {

        var listAnimal = mutableListOf<Animal>()

        val def = coroutineScope.async {

            if (repositoryLocal.getAll().isEmpty()) {
                Log.d("ANIMAL", "INTERNET")
                repositoryApi.doNetWorkCall().forEach {
                    repositoryLocal.insert(it)
                    listAnimal.add(it)
                }
            } else {
                Log.d("ANIMAL", "CACHE")
                listAnimal.addAll(repositoryLocal.getAll())

                coroutineScope.launch {
                    checkListInNetwork(listAnimal)
                }


            }
        }

        def.await()


        return listAnimal
    }

    private suspend fun checkListInNetwork(localList: List<Animal>){
        val list = repositoryApi.doNetWorkCall()

        if(list != localList){
            list.forEach {
                repositoryLocal.insert(it)
            }
        }
    }
}