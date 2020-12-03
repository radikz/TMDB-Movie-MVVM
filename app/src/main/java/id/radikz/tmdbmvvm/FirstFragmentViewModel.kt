package id.radikz.tmdbmvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.radikz.tmdbmvvm.model.Movie
import id.radikz.tmdbmvvm.repository.MoviesRepository

class FirstFragmentViewModel : ViewModel() {
    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        Log.i("FirstFragmentViewModel", "FirstFragmentViewModel created")
        getPopularMovies()
    }

    private fun getPopularMovies(){
        movies = MoviesRepository.getPopularMovies()
    }

    fun getMovie(): MutableLiveData<List<Movie>> {
        return movies
    }
}