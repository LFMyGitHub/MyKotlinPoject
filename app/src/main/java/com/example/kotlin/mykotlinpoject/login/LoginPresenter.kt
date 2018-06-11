package com.example.kotlin.mykotlinpoject.login

class LoginPresenter(loginView: LoginContract.View): LoginContract.Presenter {
    var mLoginView: LoginContract.View = loginView

    init {
        mLoginView.setPresenter(this)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(name: String, password: String) {
        if(name == "jack" && password == "123456"){
            mLoginView.loginSuccess()
        }
    }
}
