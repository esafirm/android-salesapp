package com.arx.android.salesapp.data.repository

import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.data.model.place.PlacePostParameter
import retrofit2.Response
import rx.Observable

/**
 * Created by esafirm on 6/24/16.
 */
interface PlaceRepository {
  fun getPlaces(): Observable<List<Place>>
  fun getPlace(placeId: String): Observable<Place>
  fun postPlace(parameter: PlacePostParameter): Observable<Response<BaseResponse>>
}