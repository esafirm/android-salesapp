package com.arx.android.salesapp.features.login

import android.app.ProgressDialog
import android.graphics.Color
import android.view.View
import android.widget.EditText
import com.arx.android.salesapp.BuildConfig
import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.common.isSuccess
import com.arx.android.salesapp.ui.navigator.Navigator
import com.arx.android.salesapp.utils.rx.RxUi.ui
import com.incendiary.androidcommon.android.Toasts
import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.widget.textChanges
import com.pawegio.kandroid.runOnUiThread
import kotlinx.android.synthetic.main.controller_login.view.*
import kotlinx.android.synthetic.main.view_material_edittext.view.*
import org.jetbrains.anko.textColor
import rx.functions.Action1

/**
 * Created by esafirm on 6/28/16.
 */
class LoginViewImpl(private val content: View, private val navigator: Navigator) : LoginView {

	private val inpUsername: EditText = content.loginInpUsername.material_edittext_textfield;
	private val inpPassword: EditText = content.loginInpPassword.material_edittext_textfield;

	private val progressDialog: ProgressDialog by lazy {
		ProgressDialog(content.context).apply {
			setCancelable(false)
			setMessage("Please wait ...")
		}
	}

	override val login = inpUsername.textChanges().map { it.toString() }
	override val password = inpPassword.textChanges().map { it.toString() }
	override val signInClick = content.loginBtnSubmit.clicks().doOnNext { showLoading(true) }

	override val signInEnable = ui<Boolean> {
		content.loginBtnSubmit.apply {
			isEnabled = it
			alpha = if (it) 1f else 0.5f
			textColor = if (it) Color.WHITE else Color.GRAY
		}
	}

	override val signInResult = ui<BaseResponse> {
		showLoading(false)
		Toasts.show(it.message)

		if (it.isSuccess()) {
			navigator.showPlacesList()
		}
	}

	override val onError = Action1<Throwable> {
		showLoading(false)
		Toasts.show(it.toString())
	}

	private fun showLoading(isLoading: Boolean) {
		runOnUiThread {
			when {
				isLoading -> progressDialog.show()
				else -> progressDialog.dismiss()
			}
		}
	}
}