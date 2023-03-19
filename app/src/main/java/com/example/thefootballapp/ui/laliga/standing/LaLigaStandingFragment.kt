package com.example.thefootballapp.ui.laliga.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.ui.viewmodel.FootBallViewModel
import com.example.thefootballapp.util.ResponseType
import com.example.thefootballapp.databinding.FragmentLaLigaStandingBinding
import com.example.thefootballapp.ui.team.TeamFragment
import com.example.thefootballapp.ui.team.detail.TeamDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaLigaStandingFragment : Fragment() {
    private var _binding: FragmentLaLigaStandingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootBallViewModel by activityViewModels()

    private val mAdapter by lazy {
        LaLigaStandingAdapter {
            viewModel.footballObject = it
            if (!viewModel.teamSelected){
                viewModel.footballObject = it
                val modalBottomSheet = TeamFragment()
                modalBottomSheet.show(childFragmentManager, TeamDetailFragment.TAG)
                viewModel.teamSelected = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaLigaStandingBinding.inflate(inflater, container, false)

        binding.rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.standingResultPD.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                }
                is ResponseType.SUCCESS<List<Table>> -> {
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
        viewModel.getLaLigaTableList()

        return binding.root
    }

    private fun initViews(data: List<Table>){
        data.let {
            mAdapter.updateLaLigaTableAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}