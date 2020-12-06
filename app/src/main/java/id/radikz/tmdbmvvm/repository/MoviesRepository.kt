package id.radikz.tmdbmvvm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import id.radikz.tmdbmvvm.model.Movie
import id.radikz.tmdbmvvm.model.MoviesResponse
import id.radikz.tmdbmvvm.retrofit.Api
import id.radikz.tmdbmvvm.util.TLSSocketFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

object MoviesRepository {
    private val api: Api
    private val apiKey: String = "ef89e61c8a5eab5624af0b6b8802021e"

    init {
        var client = OkHttpClient()
        try {
            client = OkHttpClient.Builder()
                .sslSocketFactory(TLSSocketFactory())
                .build()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

//    MutableLiveData<List<Movie>>
    fun getPopularMovies() : MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()
        api.getPopularMovies(apiKey = apiKey, page = 1)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
//                            onSuccess.invoke(responseBody.movies)
//                            data.value = responseBody.movies
                            data.postValue(responseBody.movies)
                        } else {
//                            onError.invoke()
                            Log.i("Error", "error")
                        }
                    } else {
//                        onError.invoke()
                        Log.i("Error", "error")
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                    onError.invoke()
                    Log.i("Error", t.message!!)
                }
            })
        return data
    }

}