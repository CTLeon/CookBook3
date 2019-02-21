package com.example.cookbook

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context:Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {


    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "dshs.db"

        private val TABLE_NAME = "dish"
        private val COL_ID = "Id"
        private val COL_DISH_NAME = "DishName"
        private val COL_DISH_DESCRIPTION = "DishDescription"

    }

    override fun onCreate(db: SQLiteDatabase?) {
     val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_DISH_NAME TEXT,$COL_DISH_DESCRIPTION TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)

    }


    val allDish:List<DishModel>
    get() {
        val firstDish = ArrayList<DishModel>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db =this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)
        if (cursor.moveToFirst()){
            do {
                val dish = DishModel()
                dish.id=cursor.getInt(cursor.getColumnIndex(COL_ID))
                dish.dishname=cursor.getString(cursor.getColumnIndex(COL_DISH_NAME))
                dish.dishdescription=cursor.getString(cursor.getColumnIndex(COL_DISH_DESCRIPTION))

                firstDish.add(dish)
            }while (cursor.moveToNext())
        }
        db.close()
        return firstDish
    }

    fun addDish (dishModel: DishModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,dishModel.id)
        values.put(COL_DISH_NAME,dishModel.dishname)
        values.put(COL_DISH_DESCRIPTION,dishModel.dishdescription)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun updateDish (dishModel: DishModel):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,dishModel.id)
        values.put(COL_DISH_NAME,dishModel.dishname)
        values.put(COL_DISH_DESCRIPTION,dishModel.dishdescription)
        return db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(dishModel.id.toString()))

    }
    fun deleteDish (dishModel: DishModel){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(dishModel.id.toString()))
        db.close()

    }
}