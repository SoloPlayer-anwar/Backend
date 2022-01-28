package com.example.kasir.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kasir.databinding.ActivityDetailsSearchBinding
import com.example.kasir.response.search.Data
import com.example.kasir.utils.Helpers.formatPrice

class DetailsSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Data>("data")

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvPrice.formatPrice(data?.harga.toString())
        binding.tvName.text = data?.nmMenu
    }
}