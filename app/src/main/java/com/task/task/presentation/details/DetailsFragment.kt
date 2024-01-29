package com.task.task.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.task.task.data.remote.ApiState
import com.task.task.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = args.id
        viewModel.getPostDetails(postId)
        collectPostState()
    }

    private fun collectPostState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postDetails.collect { state ->
                    when (state) {
                        is ApiState.Loading -> {
                            binding.progressIndicator.visibility = View.VISIBLE
                        }

                        is ApiState.Success -> {
                            binding.progressIndicator.visibility = View.GONE
                            binding.postTitleTxt.text = "Title: ${state.data.title}"
                            binding.postDescriptionTxt.text = "Description: ${state.data.body}"
                        }

                        is ApiState.Failure -> {
                            binding.progressIndicator.visibility = View.GONE
                            Toast.makeText(requireContext(), "${state.error}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}