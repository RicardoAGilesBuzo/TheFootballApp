package com.example.thefootballapp.ui.bundes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.thefootballapp.databinding.FragmentBundesBinding
import com.example.thefootballapp.databinding.FragmentLaLigaBinding
import com.example.thefootballapp.ui.bundes.match.BundesMatchFragment
import com.example.thefootballapp.ui.bundes.standing.BundesStandingFragment
import com.example.thefootballapp.ui.laliga.match.LaLigaMatchFragment
import com.example.thefootballapp.ui.laliga.standing.LaLigaStandingFragment

class BundesFragment : Fragment() {
    private val binding by lazy {
        FragmentBundesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun setupViewPager() {
        val adapter = MyPagerAdapter(childFragmentManager)
        adapter.addFragment(BundesStandingFragment(), "Standing")
        adapter.addFragment(BundesMatchFragment(), "Matches")
        binding.viewPager.adapter = adapter
    }

    class MyPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList = mutableListOf<Fragment>()
        private val fragmentTitleList = mutableListOf<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

    }
}