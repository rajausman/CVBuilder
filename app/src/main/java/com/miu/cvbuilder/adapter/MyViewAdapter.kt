package com.miu.cvbuilder.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.cvbuilder.data.Work
import com.miu.cvbuilder.ui.fragment.AboutFragment
import com.miu.cvbuilder.ui.fragment.ContactFragment
import com.miu.cvbuilder.ui.fragment.HomeFragment
import com.miu.cvbuilder.ui.fragment.WorkFragment


class MyViewAdapter(fm:FragmentManager,lc:Lifecycle) : FragmentStateAdapter(fm,lc) {
    override fun getItemCount(): Int = 4
    val workFragment = WorkFragment()
    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0-> HomeFragment()
            1-> AboutFragment()
            2-> workFragment
            3-> ContactFragment()
            else-> Fragment()
        }
    }

    fun addWork(work: Work){
        workFragment.onAddWOrk(work)
    }
}

