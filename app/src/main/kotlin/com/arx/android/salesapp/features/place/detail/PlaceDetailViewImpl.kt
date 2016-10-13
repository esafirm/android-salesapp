package com.arx.android.salesapp.features.place.detail

import android.view.View
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.utils.loadImage
import com.arx.android.salesapp.utils.rx.RxUi
import kotlinx.android.synthetic.main.controller_place_detail.view.*
import rx.Observable
import rx.Subscription
import rx.functions.Func1

class PlaceDetailViewImpl(val view: View) : PlaceDetailView {

	override val showPlace: Func1<Observable<Place>, Subscription> = RxUi.ui {
		view.apply {
			placeDetailImgContent.loadImage(it.images?.firstOrNull())
			placeDetailTxtPlaceName.text = it.namaToko
			placeDetailTxtPlaceAddress.text = it.alamat
			placeDetailTxtKecamatan.text = it.kecamatan
			placeDetailtxtKelurahan.text = it.kelurahan
			placeDetailTxtPostalCode.text = it.kodePos

			placeDetailTxtOwnerName.text = it.namaPemilikToko
			placeDetailTxtNoTelp.text = it.noTelp
			placeDetailTxtEmail.text = it.email
		}
	}
}