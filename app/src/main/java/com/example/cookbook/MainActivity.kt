package com.example.cookbook

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dish_list.*

class MainActivity : AppCompatActivity(), DishList.OnFragmentInteractionListener {
    internal lateinit var db :DBHelper
    internal var firstDish:List<DishModel> = ArrayList<DishModel>()
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.container,DishList())
                   .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container,Dish())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**     db = DBHelper(this)
        firstDish = db.allDish
  db.addDish(DishModel(1,"asdasdas","sadasd"))
        val adapter = ListDishAdapter(this@MainActivity,firstDish)
        list1.adapter= adapter*/

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}
