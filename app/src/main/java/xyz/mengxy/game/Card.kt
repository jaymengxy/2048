package xyz.mengxy.game

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * Created by Mengxy on 2019-09-16.
 */

class Card @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mTvNum = TextView(getContext()).apply {
        textSize = 28f
        setTextColor(ContextCompat.getColor(getContext(), R.color.color_number))
        gravity = Gravity.CENTER
    }
    private var mCurrentNum: Int = 0

    init {
        setPadding(10, 10, 0, 0)
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_card_bg))
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(10, 10, 0, 0)
        addView(mTvNum, layoutParams)
        setNumber(0)
    }

    fun getNumber(): Int {
        return mCurrentNum
    }

    fun setNumber(num: Int) {
        this.mCurrentNum = num
        mTvNum.text = if (num > 0) num.toString() else ""
        mTvNum.setBackgroundColor(ContextCompat.getColor(context, when (num) {
            0 -> R.color.color_number_bg_0
            2 -> R.color.color_number_bg_2
            4 -> R.color.color_number_bg_4
            8 -> R.color.color_number_bg_8
            16 -> R.color.color_number_bg_16
            32 -> R.color.color_number_bg_32
            64 -> R.color.color_number_bg_64
            128 -> R.color.color_number_bg_128
            256 -> R.color.color_number_bg_256
            512 -> R.color.color_number_bg_512
            1024 -> R.color.color_number_bg_1024
            2048 -> R.color.color_number_bg_2048
            else -> R.color.color_number_bg
        }))
    }

    override fun equals(other: Any?): Boolean {
        return (other is Card) && this.mCurrentNum == other.getNumber()
    }
}
