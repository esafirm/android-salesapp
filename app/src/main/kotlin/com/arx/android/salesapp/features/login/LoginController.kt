package com.arx.android.salesapp.features.login

import android.view.View
import com.arx.android.salesapp.BuildConfig
import com.arx.android.salesapp.R
import com.arx.android.salesapp.dagger.component
import com.arx.android.salesapp.ui.base.BaseController
import com.arx.android.salesapp.utils.controller.appComponent
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorDelegateCallback
import kotlinx.android.synthetic.main.controller_login.view.*
import kotlinx.android.synthetic.main.view_material_edittext.view.*

/**
 * Created by esafirm on 6/22/16.
 */

class LoginController : BaseController<LoginView, LoginPresenter>(),
		MvpConductorDelegateCallback<LoginView, LoginPresenter> {

	val loginPresenter: LoginPresenter by lazy { LoginPresenter(appComponent().apiService()) }
	val loginView: LoginView by lazy { LoginViewImpl(view, component().navigator()) }

	override fun createPresenter() = loginPresenter
	override fun getMvpView() = loginView
	override fun getLayoutResId() = R.layout.controller_login;

	override fun onViewCreated(view: View) {
		presenter.bind(this)

		if (BuildConfig.DEBUG) {
			view.loginInpUsername.material_edittext_textfield.append("ahmad")
			view.loginInpPassword.material_edittext_textfield.editableText.append("123")
		}
	}
}