package id.radikz.tmdbmvvm.retrofit

import id.radikz.tmdbmvvm.model.MoviesResponse
//import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MoviesResponse
}