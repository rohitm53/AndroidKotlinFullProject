package com.rohit.ecommerce

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.rohit.ecommerce.cart.CartActivity
import com.rohit.ecommerce.database.AppDatabase
import com.rohit.ecommerce.database.CartModel
import com.rohit.ecommerce.database.ProductFromDatabase
import com.rohit.ecommerce.repos.ProductsRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null,"Socks-one dozen",1.99))
            val products = db.productDao().getAll()

            val cartDao  = db.cartDao()
            cartDao.insertAll(CartModel(null,"Test Product",12.99,10))
            cartDao.insertAll(CartModel(null,"Test Product",12.99,10))
            cartDao.insertAll(CartModel(null,"Test Product",12.99,10))
            cartDao.insertAll(CartModel(null,"Test Product",12.99,10))

            val cartItems =  cartDao.getAll()
            uiThread {
                d("rohit","Product size : ${products.size} ${products[0]}")
                cartItems.forEach {
                    d("rohit","Item in cart : ${it.title}")
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout,MainFragment())
            .commit()


        navigationView.setNavigationItemSelectedListener {
            it.isChecked=true
            drawerlayout.closeDrawers()
            when(it.itemId){
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout,MainFragment())
                        .commit()
                }
                R.id.actionJeans -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout,JeansFragment())
                            .commit()
                }
                R.id.actionShorts -> {

                }
                R.id.actionAdmin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout,AdminFragment())
                        .commit()
                }
            }
            return@setNavigationItemSelectedListener true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==R.id.actionCart){
            startActivity(Intent(this,CartActivity::class.java))
            return true
        }
        drawerlayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

}