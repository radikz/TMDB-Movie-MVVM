package id.radikz.tmdbmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.radikz.tmdbmvvm.adapter.MoviesAdapter
import id.radikz.tmdbmvvm.databinding.FragmentFirstBinding
import id.radikz.tmdbmvvm.model.Movie

class FirstFragment : Fragment() {

    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private lateinit var viewModel: FirstFragmentViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        val progress = binding.progressMovies
        val popularMovies = binding.popularMovies

        popularMoviesLayoutMgr = LinearLayoutManager(this.activity)
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf())
        popularMovies.adapter = popularMoviesAdapter

        viewModel.getMovie().observe(viewLifecycleOwner, Observer { newMovies ->
            onPopularMoviesFetched(newMovies)
            progress.visibility = View.GONE
        })

        return binding.root
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
    }
}