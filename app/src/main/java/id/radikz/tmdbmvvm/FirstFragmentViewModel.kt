package id.radikz.tmdbmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.radikz.tmdbmvvm.model.Movie
import id.radikz.tmdbmvvm.repository.MoviesRepository
import kotlinx.coroutines.*

class FirstFragmentViewModel : ViewModel() {

    private val movies = liveData(Dispatchers.IO) {
        val popularMovie = MoviesRepository.getPopularMovies().movies
        emit(popularMovie)
    }

    fun getMovie(): LiveData<List<Movie>> {
        return movies
    }
}