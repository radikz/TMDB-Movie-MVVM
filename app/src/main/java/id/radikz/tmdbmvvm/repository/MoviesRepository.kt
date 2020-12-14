package id.radikz.tmdbmvvm.repository

import okhttp3.OkHttpClient
import id.radikz.tmdbmvvm.retrofit.Api
import id.radikz.tmdbmvvm.util.TLSSocketFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

object MoviesRepository {
    private val api: Api
    private val apiKey: String = "YOUR API KEY"

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

    suspend fun getPopularMovies() = api.getPopularMovies(apiKey = apiKey, page = 1)

}
