package com.example.thefootballapp.ui.bundes.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.databinding.FragmentBundesMatchBinding
import com.example.thefootballapp.ui.viewmodel.FootBallViewModel
import com.example.thefootballapp.util.ResponseType

class BundesMatchFragment : Fragment() {
    private var _binding: FragmentBundesMatchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootBallViewModel by activityViewModels()

    private val mAdapter by lazy {
        BundesMatchAdapter {
            viewModel.matchObject = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBundesMatchBinding.inflate(inflater, container, false)

        binding.rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.matchResultBL.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                }
                is ResponseType.SUCCESS<List<Matche>> -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {
                    //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getBundesMatchList()

        return binding.root
    }

    private fun initViews(data: List<Matche>){
        data.let {
            mAdapter.updateBundesMatchAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}