package com.example.thefootballapp.ui.premier.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefootballapp.data.model.match.Matche
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.databinding.FragmentPremierMatchBinding
import com.example.thefootballapp.databinding.FragmentPremierStandingBinding
import com.example.thefootballapp.ui.premier.standing.PremierStandingAdapter
import com.example.thefootballapp.ui.viewmodel.FootBallViewModel
import com.example.thefootballapp.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PremierMatchFragment : Fragment() {
    private var _binding: FragmentPremierMatchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootBallViewModel by activityViewModels()

    private val mAdapter by lazy {
        PremierMatchAdapter {
            viewModel.matchObject = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPremierMatchBinding.inflate(inflater, container, false)

        binding.rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.matchResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                }
                is ResponseType.SUCCESS<List<Matche>> -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getPremierMatchList()

        return binding.root
    }

    private fun initViews(data: List<Matche>){
        data.let {
            mAdapter.updatePremierMatchAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}