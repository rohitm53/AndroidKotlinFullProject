package com.rohit.ecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.rohit.ecommerce.database.AppDatabase
import com.rohit.ecommerce.models.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main,container,false)

//        doAsync {
//            val json = URL("https://finepointmobile.com/data/products.json").readText()
//
//            uiThread {
//                val products = Gson().fromJson(json,Array<Product>::class.java).toList()
//                root.rv_items.apply {
//                    layoutManager= GridLayoutManager(activity,2)
//                    adapter = ProductAdapter(products)
//                    root.progressBar.visibility=View.GONE
//                }
//            }
//        }



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

        btnSearch.setOnClickListener {

            val term = ed_search.text.toString()

            doAsync {

                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                val productsFromDatabase = db.productDao().searchFor("%${term}%")
                val products = productsFromDatabase.map {
                    Product(it.title,"https://finepointmobile.com/data/jeans1.jpg",it.price,true,it.title)
                }

                uiThread {
                    rv_items.apply {
                        layoutManager= GridLayoutManager(activity,2)
                        adapter = ProductAdapter(products)
                    }
                   progressBar.visibility=View.GONE
                }
            }

        }

    }

}