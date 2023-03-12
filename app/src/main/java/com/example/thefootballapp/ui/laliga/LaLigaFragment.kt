package com.example.thefootballapp.ui.laliga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.thefootballapp.R
import com.example.thefootballapp.ui.laliga.match.LaLigaMatchFragment
import com.example.thefootballapp.ui.laliga.standing.LaLigaStandingFragment
import com.example.thefootballapp.ui.premier.match.PremierMatchFragment
import com.example.thefootballapp.ui.premier.standing.PremierStandingFragment
import com.google.android.material.tabs.TabLayout

class LaLigaFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_la_liga, container, false)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager() {
        val adapter = MyPagerAdapter(childFragmentManager)
        adapter.addFragment(LaLigaStandingFragment(), "Standing")
        adapter.addFragment(LaLigaMatchFragment(), "Matches")
        viewPager.adapter = adapter
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