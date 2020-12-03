package id.radikz.tmdbmvvm.retrofit

import id.radikz.tmdbmvvm.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>
}