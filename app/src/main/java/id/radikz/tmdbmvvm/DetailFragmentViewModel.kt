package id.radikz.tmdbmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.radikz.tmdbmvvm.model.Movie

class DetailFragmentViewModel(detailMovie: Movie) : ViewModel() {

    private val _movies = MutableLiveData<Movie>()
    val movies: LiveData<Movie>
        get() = _movies

    init {
        _movies.value = detailMovie
    }
}