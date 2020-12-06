package id.radikz.tmdbmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import id.radikz.tmdbmvvm.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false
        )

        val detailFragmentArgs by navArgs<DetailFragmentArgs>()

        val viewModel: DetailFragmentViewModel by lazy {
            val viewModelFactory = DetailFragmentViewModelFactory(detailFragmentArgs.movies)
            ViewModelProvider(this, viewModelFactory).get(DetailFragmentViewModel::class.java)
        }

        binding.detailViewModel = viewModel

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            Glide.with(this)
                .load("http://image.tmdb.org/t/p/w1280${movies.backdropPath}")
                .transform(CenterCrop())
                .into(binding.movieBackdrop)

            Glide.with(this)
                .load("http://image.tmdb.org/t/p/w342${movies.posterPath}")
                .transform(CenterCrop())
                .into(binding.moviePoster)
        })

        return binding.root
    }
}