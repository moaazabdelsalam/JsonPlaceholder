package com.task.task.presentation.home

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
import androidx.navigation.fragment.findNavController
import com.task.task.R
import com.task.task.data.remote.ApiState
import com.task.task.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: HomeAdapter by lazy {
        HomeAdapter {
            onPostClick(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectPostsState()
    }

    private fun collectPostsState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postsState.collectLatest {
                    when (it) {
                        is ApiState.Loading -> {
                            binding.apply {
                                postsRecyclerView.visibility = View.GONE
                                progressIndicator.visibility = View.VISIBLE
                            }
                        }

                        is ApiState.Success -> {
                            binding.apply {
                                progressIndicator.visibility = View.GONE
                                postsRecyclerView.visibility = View.VISIBLE
                                postsRecyclerView.adapter = adapter
                                adapter.submitList(it.data)
                            }
                        }

                        is ApiState.Failure -> {
                            binding.progressIndicator.visibility = View.GONE
                            Toast.makeText(requireContext(), "${it.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun onPostClick(postId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(postId)
        if (findNavController().currentDestination?.id == R.id.homeFragment) {
            findNavController().navigate(action)
        }
    }
}