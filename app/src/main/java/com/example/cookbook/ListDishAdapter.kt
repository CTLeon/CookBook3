package com.example.cookbook

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_layout.view.*

class ListDishAdapter(
    val context: Context?,
    internal var firstDish:List<DishModel>
                      ):BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
       val rowView : View
        rowView = LayoutInflater.from(context).inflate(R.layout.row_layout,null)
        rowView.dishName.text = firstDish[position].dishname.toString()
        rowView.dishDescription.text = firstDish[position].dishdescription.toString()
        return rowView
    }

    override fun getItem(position: Int): Any {
        return firstDish[position]
    }

    override fun getItemId(position: Int): Long {
        return firstDish[position].id.toLong()
    }

    override fun getCount(): Int {
       return firstDish.size
    }
}