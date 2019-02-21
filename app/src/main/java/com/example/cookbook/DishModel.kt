package com.example.cookbook

class DishModel {
    var id:Int=0
    var dishname:String?= null
    var dishdescription:String?= null

    constructor(id:Int,dishname:String,dishdescription:String){
        this.id=id
        this.dishname=dishname
        this.dishdescription=dishdescription

    }

    constructor()
}