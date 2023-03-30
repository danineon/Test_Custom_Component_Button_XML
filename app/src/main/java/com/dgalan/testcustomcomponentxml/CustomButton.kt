package com.dgalan.testcustomcomponentxml

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dgalan.testcustomcomponentxml.databinding.CustomButtonBinding

class CustomButton @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: CustomButtonBinding =
        CustomButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        extractAttrs()
    }

    private fun setProperties(typedArray: TypedArray) {
        setEndIconIfExists(typedArray)
    }

    private fun setEndIconIfExists(typedArray: TypedArray) {
        val endIcon = typedArray.getDrawable(R.styleable.CustomButton_endIcon)
        setIcon(endIcon)
    }

    private fun setIcon(endIcon: Drawable?) {
        if (endIcon != null) {
            binding.ivEndIcon.setImageDrawable(endIcon)
            binding.ivEndIcon.visibility = VISIBLE
        }
    }

    private fun extractAttrs() {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                it,
                R.styleable.CustomButton,
                defStyleAttr, 0
            )
            try {
                setProperties(typedArray)
            } finally {
                typedArray.recycle()
            }
        }
    }
}