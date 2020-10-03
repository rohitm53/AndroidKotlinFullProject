package com.rohit.ecommerce

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rohit.ecommerce.models.Product
import com.rohit.ecommerce.productdetails.ProductDetails
import com.rohit.ecommerce.repos.ProductsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main,container,false)
        root.progressBar.visibility= View.GONE
        val listCategories = listOf("Jeans","Socks","Skirts","Dresser","Daniel","Rohit","Shirts","Troussar")

        root.rv_categories.apply {
            layoutManager = LinearLayoutManager(
                activity,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = CategoriesAdapter(listCategories)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility=View.VISIBLE

        viewModel = ViewModelProvider(requireActivity()).get(MainFragmentViewModel::class.java)

        viewModel.products.observe(requireActivity(),{
            loadRecylerView(it)
        })

        viewModel.setup()

        btnSearch.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            viewModel.searchProduct(ed_search.text.toString())
        }

        ed_search.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(str: Editable?) {
                progressBar.visibility=View.VISIBLE
                viewModel.searchProduct(str.toString())
            }

        })
    }

    fun loadRecylerView(listProducts: List<Product>) {
        rv_items.apply {
            layoutManager= GridLayoutManager(activity,2)
            adapter = ProductAdapter(listProducts) { position , photoView ->
                val intent = Intent(activity, ProductDetails::class.java)
                intent.putExtra("title", listProducts[position].title)
                val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(activity as AppCompatActivity,photoView,"photoToAnimate")

                startActivity(intent,options.toBundle())
            }
        }
        progressBar.visibility=View.GONE
    }

}