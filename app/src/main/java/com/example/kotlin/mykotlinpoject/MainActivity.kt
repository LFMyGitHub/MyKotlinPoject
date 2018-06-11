package com.example.kotlin.mykotlinpoject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin.mykotlinpoject.utils.KotlinUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/**
 * 扩展函数
 */
fun KotlinUtils.goo(){
    Log.d("TAG", "KZ")
}
