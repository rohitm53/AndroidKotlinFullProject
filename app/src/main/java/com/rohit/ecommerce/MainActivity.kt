package com.rohit.ecommerce

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        doAsync {
//
//            val db = Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java, "database-name"
//            ).build()
//
//            db.productDao().insertAll(ProductFromDatabase(null,"Socks-one dozen",1.99))
//            val products = db.productDao().getAll()
//
//
//            uiThread {
//
//                d("rohit","Product size : ${products.size} ${products[0]}")
//
//            }
//        }



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