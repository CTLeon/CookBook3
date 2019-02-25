package com.example.cookbook.Activitys


import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

import com.example.cookbook.*
import com.example.cookbook.Fragments.Dish
import com.example.cookbook.Fragments.DishAdd
import com.example.cookbook.Fragments.DishList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DishList.OnFragmentInteractionListener, DishAdd.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    internal lateinit var db : DBHelper
    internal var firstDish:List<DishModel> = ArrayList<DishModel>()
    override fun onFragmentInteraction(dish: DishModel) {
        val dishName:String = dish.dishname.toString()
        val dishDescription:String = dish.dishdescription.toString()
        val dishID: Int = dish.id.toInt()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.contened,
                Dish.newInstance(dishName, dishDescription, dishID)
            )
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.contened, DishList())
                   .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contened,DishAdd())
                    .commit()

                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction()
                .replace(R.id.contened, DishList())
                .commit()


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}
