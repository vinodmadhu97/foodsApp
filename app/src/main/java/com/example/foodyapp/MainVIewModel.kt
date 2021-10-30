package com.example.foodyapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapp.data.Repository
import com.example.foodyapp.models.FoodRecipe
import com.example.foodyapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

//SERVAVE THE DATA FROM ACTIVITY OR FRAGMENT LIFE CYCLE
@HiltViewModel
class MainVIewModel @Inject constructor(private val repository: Repository,application: Application) : AndroidViewModel(application){

    var recipeResponse : MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(query:Map<String,String>) = viewModelScope.launch {
        getRecipesSafeCall(query)
    }

    private suspend fun getRecipesSafeCall(query: Map<String, String>) {
        recipeResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response = repository.remote.getRecipes(query)
                recipeResponse.value = handleFoodRecipesResponse(response)
            }catch (e:Exception){

            }
        }else{
            recipeResponse.value = NetworkResult.Error("No internet Connection")

        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when{
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 ->{
                return NetworkResult.Error("API key limited")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found")
            }

            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe!!)
            }
            else ->{
                return NetworkResult.Error(response.message())
            }
        }
    }

    //CHECK THE INTERNET CONNECTION
    private fun hasInternetConnection():Boolean{

        val connectivityManager = getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false

        }
    }

}