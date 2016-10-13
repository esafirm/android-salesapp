package com.arx.android.salesapp.views

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.FrameLayout
import com.arx.android.salesapp.R
import kotlinx.android.synthetic.main.view_material_edittext.view.*

/**
 * Created by esafirm on 6/23/16.
 */
class MaterialEditText @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
: FrameLayout(context, attrs, defStyle) {

	init {
		init(attrs)
	}

	private fun init(attrs: AttributeSet?) {
		inflate(context, R.layout.view_material_edittext, this)
		attrs?.let { applyAttributes(it) }
	}

	private fun applyAttributes(attrs: AttributeSet?) {
		var a = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
		val hint = a.getString(R.styleable.MaterialEditText_hint)
		val textField = material_edittext_textfield;

		material_edittext_inputlayout.hint = hint
		a.recycle()

		a = context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.inputType))
		a.run {
			textField.inputType = a.getInt(0, InputType.TYPE_CLASS_TEXT)
			recycle()
		}
	}

	var text: String
		set(value) = material_edittext_textfield.setText(value)
		get() = material_edittext_textfield.text.toString()
}
