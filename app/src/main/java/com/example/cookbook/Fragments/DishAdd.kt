package com.example.cookbook.Fragments

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.ButterKnife
import com.example.cookbook.Activitys.MainActivity
import com.example.cookbook.DBHelper
import com.example.cookbook.DishModel
import com.example.cookbook.R
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.fragment_dish_add.*



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
internal lateinit var db : DBHelper


class DishAdd : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var image_rui: Uri?= null




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
        Fresco.initialize(this.context)
        val view = inflater.inflate(R.layout.fragment_dish_add, null, false)
        ButterKnife.bind(view)
        val dishName : EditText = view!!.findViewById(R.id.textView)
        val dishDescription : EditText = view!!.findViewById(R.id.textView2)
        val buttonSave : Button = view.findViewById(R.id.buttonSave)
        val buttonCamera : Button = view.findViewById(R.id.buttonCamera)

        buttonCamera.setOnClickListener{


            openCamera ()
        }
        buttonSave.setOnClickListener{
            db = DBHelper(this.context!!)
            if (dishName.getText().length == 0 || dishDescription.getText().length == 0){
                Toast.makeText(this.context,"Поля пусты",Toast.LENGTH_LONG).show()
            } else{
                val model = DishModel()
                if (db.allDish.isEmpty()){
                    model.id= 1
                }
                else{
                    val id :Int = db.allDish.last().id
                    model.id= id+1
                }
                model.dishname= dishName.text.toString()
                model.dishdescription = dishDescription.text.toString()
                addDish(model)
            }
        }
        return view
    }
    fun openCamera (){
        val values= ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        val resolver: ContentResolver  = getActivity()!!.getContentResolver()
        image_rui = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        val intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, 123)
        activity!!.startActivityForResult(intent,123)

    }
    fun addDish(model :DishModel){
        db = DBHelper(this.context!!)
        db.addDish(model)
        val intent = Intent (activity,MainActivity::class.java)
        activity!!.startActivity(intent)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
       imageView2.setImageURI(image_rui)

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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DishAdd().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
