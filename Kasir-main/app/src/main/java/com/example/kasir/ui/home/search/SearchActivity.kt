package com.example.kasir.ui.home.search

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasir.R
import com.example.kasir.databinding.ActivitySearchBinding
import com.example.kasir.response.distribusi.Data
import com.example.kasir.response.search.SearchResponse
import com.example.kasir.ui.home.detail.DetailsSearchActivity

class SearchActivity : AppCompatActivity(),SearchContract.View{
    private lateinit var binding: ActivitySearchBinding
    private lateinit var presenter: SearchPresenter
    var loadingDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SearchPresenter(this)
        initLoading()

        binding.ivBack.setOnClickListener {
            finish()
        }

        val data = intent.getParcelableExtra<Data>("data")
        val idLocation = data?.idDistribusi
        val idDistribusi = data?.idDistribusi


        presenter.getSearch(
            idLocation!!,
            idDistribusi!!
        )
    }

    private fun initLoading() {
        loadingDialog = Dialog(this)
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog, null)
        loadingDialog.let {
            it?.setContentView(dialogView)
            it?.setCancelable(false)
            it?.window?.setBackgroundDrawableResource(R.color.tsp)
        }
    }

    override fun searchSuccess(searchResponse: SearchResponse) {
        val searchList = searchResponse.data as ArrayList<com.example.kasir.response.search.Data>
        var adapter = AdapterSearch(searchResponse.data) {
            val intent = Intent(this, DetailsSearchActivity::class.java)
                .putExtra("data",it)
            startActivity(intent)
        }

        binding.rvMenuSearch.layoutManager = GridLayoutManager(this, 3)
        binding.rvMenu.layoutManager = GridLayoutManager(this, 3)
        binding.rvMenu.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = false

            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0 != null) {
                    if(p0.isEmpty()) {
                        binding.rvMenu.visibility = View.VISIBLE
                        binding.rvMenuSearch.visibility = View.INVISIBLE
                    }else if (p0.length > 2) {
                        val filter = searchList.filter { it.nmMenu?.contains("$p0", true) == true }
                        adapter = AdapterSearch(filter as ArrayList<com.example.kasir.response.search.Data>) {
                            val intent = Intent(applicationContext, DetailsSearchActivity::class.java)
                                .putExtra("data",it)
                            startActivity(intent)
                        }
                    }
                    if (p0.isNotEmpty()) {
                        binding.rvMenu.visibility = View.INVISIBLE
                        binding.rvMenuSearch.visibility = View.VISIBLE
                        binding.rvMenuSearch.adapter = adapter
                    }

                    else {
                        binding.rvMenu.visibility = View.VISIBLE
                        binding.rvMenuSearch.visibility = View.INVISIBLE
                    }
                }

                return false
            }

        })
    }

    override fun searchFailure(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    override fun showLoading(loading: Boolean) {
        when(loading) {
            true -> {
                loadingDialog?.show()
            }

            false -> {
                loadingDialog?.dismiss()
            }
        }
    }


}