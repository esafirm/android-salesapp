package com.arx.android.salesapp.features.submit

import android.app.AlertDialog
import android.app.ProgressDialog
import android.view.View
import com.arx.android.salesapp.R
import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.model.place.PlacePostParameter
import com.arx.android.salesapp.utils.children
import com.arx.android.salesapp.utils.inflater
import com.arx.android.salesapp.utils.isVisible
import com.arx.android.salesapp.utils.loadImage
import com.arx.android.salesapp.utils.rx.RxUi
import com.incendiary.androidcommon.android.Toasts
import com.jakewharton.rxbinding.view.clicks
import com.pawegio.kandroid.i
import kotlinx.android.synthetic.main.controller_submit.view.*
import kotlinx.android.synthetic.main.list_item_submit_photo.view.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.onClick
import retrofit2.Response
import rx.Observable
import rx.Subscription
import rx.android.MainThreadSubscription
import rx.functions.Action1
import rx.functions.Func1

class SubmitViewImpl(val view: View) : SubmitView {

	val progressDialog: ProgressDialog by lazy {
		ProgressDialog(view.context).apply {
			setMessage("Submitting ...")
		}
	}

	override val submitClick: Observable<PlacePostParameter> = view.btnSubmit.clicks()
			.map {
				PlacePostParameter().apply {
					namaToko = view.submitInpPlaceName.text
					alamat = view.submitInpPlaceAddress.text
					kecamatan = view.submitInpKecamatan.text
					kelurahan = view.submitInpKelurahan.text
					kodePos = view.submitInpPostalCode.text

					namaPemilikToko = view.submitInpOwnerName.text
					noTelp = view.submitInpNoTelp.text
					email = view.submitInpEmail.text

					rx.Observable.from(view.imgContainer.children())
							.filter { it.tag != null }
							.map { it.tag.toString() }
							.toList()
							.subscribe { foto = it}
				}
			}

	override val addPhotoClick: Observable<Boolean> by lazy {
		rx.Observable.create<Boolean> {
			val sub = it
			view.btnAddPhoto.onClick {
				AlertDialog.Builder(view.context)
						.setTitle("Pick Image")
						.setItems(arrayOf("Camera", "Gallery"),
								{ dialogInterface, i -> sub.onNext(i == 0) })
						.show()
			}
			sub.add(object : MainThreadSubscription() {
				override fun onUnsubscribe() {
					view.btnAddPhoto.setOnClickListener(null)
				}
			})
		}
	}

	override val showSubmitted: Func1<Observable<Response<BaseResponse>>, Subscription>
			= RxUi.ui<Response<BaseResponse>> { i { it.toString() } }

	override val showPhotoAdded: Func1<Observable<String>, Subscription> = RxUi.ui<String> {

		val inflater = view.inflater()
		view.apply {
			val child = inflater.inflate(R.layout.list_item_submit_photo, view.imgContainer, false)
			child.layoutParams.apply {
				val size = (view.width - dip(32)) / 3
				width = size
				height = size
			}

			child.tag = it
			child.imgContent.loadImage(it)
			child.btnClose.onClick {
				imgContainer.removeView(child)
				invalidateAddPhotoButton()
			}

			imgContainer.addView(child, 0)
			invalidateAddPhotoButton()
		}
	}

	fun invalidateAddPhotoButton() {
		view.btnAddPhoto.isVisible(view.imgContainer.childCount <= 3)
	}

	override val showError = Action1<Throwable> {
		Toasts.show(it.toString())
	}

	override fun showLoading(isShow: Boolean) {
		when {
			isShow -> progressDialog.show()
			else -> progressDialog.dismiss()
		}
	}
}