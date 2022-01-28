package com.example.kasir.ui.home.dashboard

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasir.R
import com.example.kasir.databinding.FragmentHomeBinding
import com.example.kasir.response.category.CategoryResponse
import com.example.kasir.response.category.Data
import com.example.kasir.response.distribusi.DistribusiResponse
import com.example.kasir.response.menu.MenuResponse
import com.example.kasir.ui.home.dashboard.menu.AdapterDistribusi
import com.example.kasir.ui.home.dashboard.menu.AdapterMenu
import com.example.kasir.ui.home.detail.DetailsActivity
import com.example.kasir.ui.home.search.SearchActivity


class HomeFragment : Fragment(), HomeContract.View, AdapterHome.ItemAdapterCallback, AdapterDistribusi.ItemAdapterCallback  {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: HomePresenter
    private var dineIn: ArrayList<com.example.kasir.response.menu.Data> = ArrayList()
    private var gojek: ArrayList<com.example.kasir.response.menu.Data> = ArrayList()
    private var dilevery: ArrayList<com.example.kasir.response.menu.Data> = ArrayList()
    var loadingDialog: Dialog? = null
    var id_dis: Int = 1
    var id_lokasi: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return (binding.root)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)
        initLoading()
        presenter.getHome()
        presenter.getDistribusi()


        binding.pageNext.setOnClickListener {
            var qty = binding.quantity.text.toString().toInt()
            qty += 1
            binding.quantity.text = qty.toString()
            presenter.menuSuccess(
                id_lokasi,
                id_dis,
                qty
            )
        }

        binding.pageBack.setOnClickListener {
           var  qty = binding.quantity.text.toString().toInt()
            if(qty > 1) qty -= 1
            binding.quantity.text = qty.toString()
            presenter.menuSuccess(
                id_lokasi,
                id_dis,
                qty
            )
        }



        presenter.menuSuccess(
            1,
            1,
            1,
        )

    }

    private fun initLoading() {
        loadingDialog = Dialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog, null)
        loadingDialog.let {
            it?.setContentView(dialogView)
            it?.setCancelable(false)
            it?.window?.setBackgroundDrawableResource(R.color.tsp)
        }
    }

    override fun categorySuccess(categoryResponse: CategoryResponse) {
        val adapter = AdapterHome(categoryResponse.data, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter = adapter

    }

    override fun categoryFailure(message: String) {
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }

    override fun distribusiSuccess(distribusiResponse: DistribusiResponse) {
        val adapter = AdapterDistribusi(distribusiResponse.data, this)
        binding.rvDistribusi.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvDistribusi.adapter = adapter
    }

    override fun distribusiFailure(message: String) {
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }

    override fun menuSuccess(menuResponse: MenuResponse) {


        for(a in menuResponse.data.indices) {
            val items: List<String> = menuResponse.data[a].nmMenu?.split("") ?: ArrayList()
            for(x in items.indices) {
                when {
                    items[x].equals("DINE-IN / TAKEWAY", true) -> {
                        dineIn.add(menuResponse.data[a])
                    }
                    items[x].equals("GOJEK", true) -> {
                        gojek.add(menuResponse.data[a])
                    }
                    items[x].equals("DELIVERY", true) -> {
                        dilevery.add(menuResponse.data[a])
                    }
                }

            }
        }

        val adapter = AdapterMenu(menuResponse.data) {
            val menu = Intent(context, DetailsActivity::class.java)
                .putExtra("data",it)
            startActivity(menu)
        }
        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvMenu.adapter = adapter

    }

    override fun menuFailure(message: String) {
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
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

    override fun onClick(v: View, data: Data) {
        TODO("Not yet implemented")
    }



    override fun onClick(v: View, data: com.example.kasir.response.distribusi.Data) {

        binding.searcView.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
                .putExtra("data", data)
            startActivity(intent)
        }

        id_dis = data.idDistribusi
        id_lokasi = data.idDistribusi

        presenter.menuSuccess(
            id_lokasi,
            id_dis,
            1,
        )
    }

}