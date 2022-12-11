package com.miu.cvbuilder.ui.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.cvbuilder.R
import com.miu.cvbuilder.adapter.MyViewAdapter
import com.miu.cvbuilder.data.Work
import com.miu.cvbuilder.databinding.ActivityMainBinding
import com.miu.cvbuilder.ui.dialog.DialogCommunicator
import com.miu.cvbuilder.ui.dialog.SettingsDialog
import com.miu.cvbuilder.ui.dialog.WorkDialogCommunicator
import com.miu.cvbuilder.utils.AppUtils

class MainActivity : AppCompatActivity(), DialogCommunicator, WorkDialogCommunicator {

    private lateinit var binding: ActivityMainBinding
    private var sharedPref: SharedPreferences = AppUtils.getSharedPref()
    private lateinit var adapter: MyViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val theme = AppUtils.getPref(getString(R.string.saved_theme))
        val user = AppUtils.getPref(getString(R.string.login_user_key))
        if (theme != null) AppUtils.decideTheme(theme)

        showWorkDialog()
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.home_menu)
                1 -> tab.text = getString(R.string.about_me_menu)
                2 -> tab.text = getString(R.string.work_menu)
                3 -> tab.text = getString(R.string.contact_menu)
            }
        }.attach()


        user?.apply { binding.toolbar.title = "$user's CV" }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_main_setting -> {
                    showNoticeDialog()
                    return@setOnMenuItemClickListener true
                }
                R.id.menu_main_logout -> {
                    finish()
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }

    private fun showNoticeDialog() {
        val dialog = SettingsDialog()
        dialog.show(supportFragmentManager, SettingsDialog::class.java.name)
    }

    override fun onChangeTheme(theme: String) {
        with(sharedPref.edit()) {
            putString(getString(R.string.saved_theme), theme)
            apply()
        }
        AppUtils.decideTheme(theme)
    }

    private fun showWorkDialog() {
        adapter = MyViewAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter
    }

    override fun onAddWOrk(work: Work) {
        if (::adapter.isInitialized) {
            adapter.addWork(work)
        } else {
            showWorkDialog()
            adapter.addWork(work)
        }
    }

}