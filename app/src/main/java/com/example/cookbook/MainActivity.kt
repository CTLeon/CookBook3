package com.example.cookbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DishList.OnFragmentInteractionListener, Dish.OnFragmentInteractionListener {
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

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



            supportFragmentManager.beginTransaction()
                .replace(R.id.container,DishList())
                .commit()


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    fun transferDish (dish: DishModel){
        val dishName : String = dish.dishname.toString()
        val dishDescription : String = dish.dishdescription.toString()
        supportFragmentManager.beginTransaction()
        .replace(R.id.container,Dish.newInstance(dishName,dishDescription))
        .commit()
}


}
