package com.infi.ecommerce

import android.os.Bundle
import android.util.Log.d
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.infi.ecommerce.models.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
            }
            return@setNavigationItemSelectedListener true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerlayout.openDrawer(GravityCompat.START)
        return true
    }

}