package com.example.kasir.ui.home.dashboard.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kasir.databinding.ActivityTransactionPendingBinding

class TransactionPendingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionPendingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionPendingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}