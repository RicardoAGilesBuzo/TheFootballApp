package com.example.thefootballapp.ui.premier

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.thefootballapp.R
import com.example.thefootballapp.databinding.FragmentPremierBinding
import com.example.thefootballapp.ui.premier.match.PremierMatchFragment
import com.example.thefootballapp.ui.premier.standing.PremierStandingFragment
import com.google.android.material.tabs.TabLayout

class PremierFragment : Fragment() {

    private val binding by lazy {
        FragmentPremierBinding.inflate(layoutInflater)
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
        adapter.addFragment(PremierStandingFragment(), "Standing")
        adapter.addFragment(PremierMatchFragment(), "Matches")
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