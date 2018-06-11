package com.example.kotlin.mykotlinpoject.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.kotlin.mykotlinpoject.MainActivity
import com.example.kotlin.mykotlinpoject.R
import com.example.kotlin.mykotlinpoject.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(), View.OnClickListener, LoginContract.View {
    //? 操作符表示安全的类型转换
    private var loginPresenter: LoginContract.Presenter? = null
    var name: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        loginPresenter = LoginPresenter(this)
    }

    private fun initView() {
        //!! 操作符表示前面的变量为非空
        btnLogin!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin -> login()
            else -> {}
        }
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        loginPresenter = presenter
    }

    private fun login() {
        name = etName.text.toString().trim()
        password = etPassword.text.toString().trim()
        Log.d("TAG", "$name + $password")
        loginPresenter!!.login(name!!, password!!)
    }

    override fun loginSuccess() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}