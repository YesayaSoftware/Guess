package software.yesaya.guess.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import software.yesaya.guess.R
import software.yesaya.guess.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFacrtory: ScoreViewModelFacrtory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )

        viewModelFacrtory = arguments?.let { ScoreFragmentArgs.fromBundle(it).score }?.let { ScoreViewModelFacrtory(it) }!!
        viewModel = ViewModelProviders.of(this, viewModelFacrtory).get(ScoreViewModel::class.java)

        binding.scoreText.text = ScoreFragmentArgs.fromBundle(arguments!!).score.toString()

        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())

                viewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}
