package com.example.kasir.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.kasir.R
import com.example.kasir.databinding.ActivityBottomNavigationBinding
import com.example.kasir.ui.home.dashboard.HomeFragment
import com.example.kasir.ui.home.dashboard.TransactionSuccessFragment

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment.newInstance())
        binding.bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_transaction_success))

        binding.bottomNavigation.setOnClickMenuListener {
            when(it.id) {
                0 -> {
                    replaceFragment(HomeFragment.newInstance())
                }

                1 -> {
                    replaceFragment(TransactionSuccessFragment.newInstance())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment)
        fragmentTransition.commit()
    }
}