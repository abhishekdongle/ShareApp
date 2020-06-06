package com.shareapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.shareapp.R
import com.shareapp.base.BaseActivity
import com.shareapp.di.component.ActivityComponent
import com.shareapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_login

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.loggingIn.observe(this, Observer {
            if (it)
                startActivity(Intent(this, MainActivity::class.java))
        })

        bt_login.setOnClickListener { viewModel.onLogin() }
    }

    override fun getDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}
