package com.arx.android.salesapp.features.place.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arx.android.salesapp.R
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.ui.base.BaseListRecyclerAdapter
import com.arx.android.salesapp.utils.loadImage
import kotlinx.android.synthetic.main.list_item_place.view.*

/**
 * Created by esafirm on 6/29/16.
 */
class PlaceListAdapter(context: Context) : BaseListRecyclerAdapter<Place, PlaceListAdapter.PlaceViewHolder>(context) {

	override fun getLayoutResId(viewType: Int): Int = R.layout.list_item_place

	override fun onCreate(view: View, viewType: Int): PlaceViewHolder = PlaceViewHolder(view)

	override fun onBind(viewHolder: PlaceViewHolder, place: Place, position: Int) {
		viewHolder.itemView.txtName.text = place.namaToko
		viewHolder.itemView.txtAddress.text = place.alamat
		viewHolder.itemView.imgContent.loadImage(place.images?.firstOrNull())
	}

	inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
	}
}