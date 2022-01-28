package com.example.kasir.ui.home.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kasir.R
import com.example.kasir.databinding.FragmentTransactionSuccessBinding


class TransactionSuccessFragment : Fragment() {
    private lateinit var binding: FragmentTransactionSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionSuccessBinding.inflate(layoutInflater)
        return (binding.root)
    }

    companion object {

        fun newInstance() =
            TransactionSuccessFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}