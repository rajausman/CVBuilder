package com.miu.cvbuilder.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.cvbuilder.R
import com.miu.cvbuilder.adapter.WorkAdapter
import com.miu.cvbuilder.data.Work
import com.miu.cvbuilder.ui.dialog.WorkDialog

class WorkFragment : Fragment(R.layout.fragment_work) {

    private var workList = mutableListOf<Work>()
    private lateinit var adapter: WorkAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        if (context != null) {
            workList = mutableListOf(
                Work(
                    getString(R.string.albertsons),
                    getString(R.string.virtual_reality_content_engineer),
                    getString(R.string._2020_present),
                    R.drawable.convo
                ),
                Work(
                    getString(R.string.google),
                    getString(R.string.application_developer_for_youtube),
                    getString(R.string._2018_2020),
                    R.drawable.ns
                ),
                Work(
                    getString(R.string.amazon),
                    getString(R.string.sde_ii),
                    getString(R.string._2016_2018),
                    R.drawable.amazon
                ),
                Work(
                    getString(R.string.kforce),
                    getString(R.string.sr_full_stack_engineer),
                    getString(R.string._2014_2016),
                    R.drawable.kforce
                )
            )
            setupRecyclerView()
        }

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { showWorkDialog() }
    }

    private fun setupRecyclerView() {
        if (::recyclerView.isInitialized) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = WorkAdapter(requireContext(), workList)
            recyclerView.adapter = adapter
        }
    }

    private fun showWorkDialog() {
        val dialog = WorkDialog()
        dialog.show(parentFragmentManager, WorkDialog::class.java.name)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onAddWOrk(work: Work) {
        workList.add(work)
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        } else {
            setupRecyclerView()
        }
    }

}