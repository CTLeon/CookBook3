package com.example.cookbook


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class DishAdapterRecycler(internal var firstDish:List<DishModel>, val clicklistener : (DishModel)-> Unit): RecyclerView.Adapter<DishAdapterRecycler.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        var DishName:TextView = itemView.findViewById(R.id.dishName)
        val DishDescription:TextView = itemView.findViewById(R.id.dishDescription)

        fun bind(Dish: DishModel, clickListener: (DishModel) -> Unit) {

            itemView.setOnClickListener { clickListener(Dish)}
        }


    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val Dish: DishModel = firstDish[p1]
        p0.DishName.text= Dish.dishname
        p0.DishDescription.text= Dish.dishdescription
        (p0 as ViewHolder).bind(firstDish[p1], clicklistener)




    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val rowView = LayoutInflater.from(p0.context).inflate(R.layout.row_layout,p0,false)
       return ViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return firstDish.size
    }


}






