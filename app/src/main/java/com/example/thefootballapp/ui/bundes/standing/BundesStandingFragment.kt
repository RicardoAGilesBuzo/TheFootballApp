package com.example.thefootballapp.ui.bundes.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thefootballapp.data.model.standing.Table
import com.example.thefootballapp.databinding.FragmentBundesStandingBinding
import com.example.thefootballapp.ui.team.detail.TeamDetailFragment
import com.example.thefootballapp.ui.team.TeamFragment
import com.example.thefootballapp.ui.viewmodel.FootBallViewModel
import com.example.thefootballapp.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BundesStandingFragment : Fragment() {
    private var _binding: FragmentBundesStandingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootBallViewModel by activityViewModels()

    private val mAdapter by lazy {
        BundesStandingAdapter {
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
        _binding = FragmentBundesStandingBinding.inflate(inflater, container, false)

        binding.rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.standingResultBL.observe(viewLifecycleOwner) {
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
        viewModel.getBundesTableList()

        return binding.root
    }

    private fun initViews(data: List<Table>){
        data.let {
            mAdapter.updateBundesTableAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}