package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var textpanal: TextView? = null
    private var lastnum : Boolean = true
    private var lastdot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textpanal = findViewById(R.id.textpanal)
    }

    fun ondigit(view: View){
        textpanal?.append((view as Button).text)
        lastnum = true
    }

    fun onclear(view:View){
        textpanal?.text = ""
        lastnum = true
        lastdot = false
    }

    fun decimal(view :View){
        if(lastnum && !lastdot){
            textpanal?.append(".")
            lastnum = false
            lastdot = true
        }
    }
    fun operator(view: View)
    {
        textpanal?.text?.let { if(lastnum && !isoperatoradd(it.toString())){
            textpanal?.append((view as Button).text)
            lastnum=false
            lastdot=false
            }
        }

    }
    private fun isoperatoradd(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            (value.contains("+")
                ||value.contains("-")
                ||value.contains("/")
                ||value.contains("x"))
        }
    }

    fun onequal(view:View){
        if(lastnum){
            var prefix = ""
            var textresult = textpanal?.text.toString()
            try {
                if(textresult.startsWith("-")){
                    prefix="-"
                    textresult=textresult.substring(1)
                }
                if(textresult.contains("-")){
                val splitvalue=  textresult.split("-")
                var one = splitvalue[0]
                var two = splitvalue[1]
                if(prefix.isNotEmpty()){
                    one = prefix + one
                }
                var finalresult =remove0((one.toDouble()-two.toDouble()).toString())
                textpanal?.text=finalresult
                }else if(textresult.contains("/")){
                    val splitvalue=  textresult.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var finalresult =remove0((one.toDouble()/two.toDouble()).toString())
                    textpanal?.text=finalresult
                }else if(textresult.contains("+")){
                    val splitvalue=  textresult.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var finalresult =remove0((one.toDouble()+two.toDouble()).toString())
                    textpanal?.text=finalresult
                }else if(textresult.contains("X")){
                    val splitvalue=  textresult.split("X")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var finalresult =remove0((one.toDouble()*two.toDouble()).toString())
                    textpanal?.text=finalresult
                }

            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun remove0(value:String):String{
        var result = value
        if(value.contains(".0")){
            result = value.substring(0,value.length-2)

        }
        return result
    }

}