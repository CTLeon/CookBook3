package com.example.cookbook


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast






class DishList : Fragment() {
    // TODO: Rename and change types of parameters
    internal lateinit var db :DBHelper
    internal var firstDish:List<DishModel> = ArrayList<DishModel>()
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recyclerlist, null, false)

        val MainActivity = MainActivity()


        db = DBHelper(this.context!!)
        firstDish = db.allDish
        val reclist : RecyclerView = view!!.findViewById<RecyclerView>(R.id.reclist)
        reclist.layoutManager = LinearLayoutManager(this.context,LinearLayout.VERTICAL,false)
        val adapter = DishAdapterRecycler(firstDish,{dish : DishModel -> DishItemClicked(dish)})
        reclist.adapter= adapter


        return view
    }

    private fun DishItemClicked(dish : DishModel) {
        val dishName:String = dish.dishname.toString()
        val dishDescription:String = dish.dishdescription.toString()
        val intent = Intent(activity, Main2Activity::class.java)
        intent.putExtra("dish_name", dishName)
        intent.putExtra("dish_description", dishDescription)
        activity!!.startActivity(intent)

    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): DishList {
            val fragment = DishList()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
