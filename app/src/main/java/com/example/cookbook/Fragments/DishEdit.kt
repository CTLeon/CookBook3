package com.example.cookbook.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.cookbook.Activitys.DishActivity
import com.example.cookbook.Activitys.MainActivity
import com.example.cookbook.DBHelper
import com.example.cookbook.DishModel
import com.example.cookbook.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DishEdit.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DishEdit.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DishEdit : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = null
    private var listener: OnFragmentInteractionListenerDisEdit? = null
    internal lateinit var db : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dish_edit, null, false)
        val textDishName :EditText= view.findViewById(R.id.textView)

        textDishName.text=Editable.Factory.getInstance().newEditable(param1)
        val textDishDescription : EditText = view.findViewById(R.id.textView2)
        textDishDescription.text=Editable.Factory.getInstance().newEditable(param2)
        val btnEdit: Button = view.findViewById(R.id.buttonSave) as Button

        btnEdit.setOnClickListener{
            val model = DishModel()
            model.id= this!!.param3!!
            model.dishname = textDishName.text.toString()
            model.dishdescription = textDishDescription.text.toString()
            db = DBHelper(this.context!!)
            db.updateDish(model)
            val intent = Intent (activity, MainActivity::class.java)
            activity!!.startActivity(intent)
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteractionDishEdit(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListenerDisEdit) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListenerDisEdit {
        // TODO: Update argument type and name
        fun onFragmentInteractionDishEdit(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DishEdit.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,param3: Int) =
            DishEdit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}
