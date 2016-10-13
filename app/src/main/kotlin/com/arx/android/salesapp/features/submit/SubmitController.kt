package com.arx.android.salesapp.features.submit

import android.view.View
import com.arx.android.salesapp.BuildConfig
import com.arx.android.salesapp.R
import com.arx.android.salesapp.features.submit.dagger.daggerComponent
import com.arx.android.salesapp.ui.base.BaseController
import com.pawegio.kandroid.runDelayedOnUiThread
import kotlinx.android.synthetic.main.controller_submit.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

/**
 * Created by esafirm on 6/23/16.
 */
class SubmitController : BaseController<SubmitView, SubmitPresenter>() {

	@Inject lateinit var submitPresenter: SubmitPresenter
	@Inject lateinit var submitView: SubmitView

	override fun getLayoutResId(): Int = R.layout.controller_submit
	override fun getMvpView(): SubmitView = submitView
	override fun createPresenter(): SubmitPresenter = submitPresenter

	override fun onInject() {
		daggerComponent().inject(this)
	}

	override fun onViewCreated(view: View) {
		view.toolbar.title = "Add Toko"
		setSupportActionBar(view.toolbar)

		runDelayedOnUiThread(500, {
			presenter.bind(this)
		})

		if (BuildConfig.DEBUG) {
			view.submitInpPlaceName.text = "Warung Roko"
			view.submitInpPlaceAddress.text = "Bumi Cipaganti No.666"
			view.submitInpPostalCode.text = "123123"
			view.submitInpNoTelp.text = "0123123123"
			view.submitInpKecamatan.text = "Ngamprah"
			view.submitInpKelurahan.text = "Tanimulya"
			view.submitInpOwnerName.text = "The Owner"
			view.submitInpEmail.text = "email@mail.com"
		}
	}
}
