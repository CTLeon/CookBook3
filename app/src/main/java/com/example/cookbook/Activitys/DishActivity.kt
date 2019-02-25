package com.example.cookbook.Activitys
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cookbook.Fragments.Dish
import com.example.cookbook.Fragments.DishEdit
import com.example.cookbook.R


class DishActivity : AppCompatActivity(), Dish.OnFragmentInteractionListener, DishEdit.OnFragmentInteractionListenerDisEdit {
    override fun onFragmentInteractionDish(uri: String?, dishDescription: String?, dishID: Int?) {
        val dishName:String? = uri
        val dishDescription:String? = dishDescription
        val dishID: Int? = dishID
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.dishFrame,
                DishEdit.newInstance(dishName!!, dishDescription!!, dishID!!)
            )
            .commit()
    }

    override fun onFragmentInteractionDishEdit(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        val dishId: Int =intent.getIntExtra("dishID",0)
        val dishName: String = intent.getStringExtra("dishName")
        val dishDescription:String = intent.getStringExtra("dishDescription")

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.dishFrame,
                Dish.newInstance(dishName, dishDescription, dishId)
            )
            .commit()

    }
}
