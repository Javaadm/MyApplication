package com.example.lamaftllocal.myapplication

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.widget.ImageView

/**
 * Created by LaMa.ftl(local) on 15.08.2017.
 */
class Man {
    constructor(name:String, passWorld:String, login:String, id:Int){
        this.name=name
        this.login=login
        this.passWorld=passWorld
        idd=id

    }

     private var out:Boolean=false

    var idd:Int=0
    val id:Int=idd
    fun setOut(out:Boolean){
        this.out=out
    }
    fun getOut():Boolean {
        return out
    }


    fun getid(): Int {
        return id
    }
    val char = Char1()
    private var kolvOchivok:Int=25

    private  var name:String=""
    fun  getName(): String {
        return name
    }
    private var login:String=""
    fun getLogin(): String {
        return login
    }
    private var passWorld:String = ""
    fun getPass(): String {
        return passWorld
    }
    private var image: Bitmap?=null//Хз, еcли не рабудеает баг тут <<--# <<--# //переменая картинки


    fun getImage(): Bitmap? {
        return image
    }
    private var poins:Int=0
    fun getPoins():Int {
        return poins
    }
    private var achievs=arrayOfNulls<BooleanArray>(kolvOchivok)


    fun choseFoto(image:Bitmap){//Хз, тут фотку грузим
        this.image=image
    }

    fun rename(name: String){
        this.name=name
    }

    fun repass(passWorld: String){
        this.passWorld=passWorld
    }

    fun relog(log: String){
        login=log
    }

    fun addPoint(data:Int){
        poins+=data
    }

    fun proverUp(){
        if (char.level*100<=poins){
            poins-=char.level*100
            char.level++
        }
    }


}