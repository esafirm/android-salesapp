package com.arx.android.salesapp.data.repository

import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.data.model.place.PlacePostParameter
import com.arx.android.salesapp.network.ApiService
import retrofit2.Response
import rx.Observable

/**
 * Created by esafirm on 6/24/16.
 */
class PlaceRemoteRepository(val api: ApiService) : PlaceRepository {

	override fun getPlaces(): Observable<List<Place>> = api.getPlaces().map { it.data }

	/* TODO  */
	override fun getPlace(placeId: String): Observable<Place> = Observable.just(Place())

	override fun postPlace(parameter: PlacePostParameter): Observable<Response<BaseResponse>> {
		return api.postPlace(parameter.toMultipartBody())
	}
}