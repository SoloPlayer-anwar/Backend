package com.example.kasir.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kasir.databinding.ActivityDetailsBinding
import com.example.kasir.response.menu.Data
import com.example.kasir.utils.Helpers.formatPrice

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Data>("data")

        binding.tvPrice.formatPrice(data?.harga.toString())
        binding.tvName.text = data?.nmMenu

        binding.ivBack.setOnClickListener {
            finish()
        }

    }
}