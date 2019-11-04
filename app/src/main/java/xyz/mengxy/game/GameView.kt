package xyz.mengxy.game

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import kotlin.math.absoluteValue
import kotlin.math.min

/**
 * Created by Mengxy on 2019-09-16.
 */

class GameView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val mCardsMap = Array(4) { Array(4) { Card(context) } }

    init {
        orientation = VERTICAL
        setOnTouchListener(object : OnTouchListener {
            var startX = 0f
            var startY = 0f
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        startY = event.y
                    }
                    MotionEvent.ACTION_UP -> {
                        val offsetX = event.x - startX
                        val offsetY = event.y - startY
                        if (offsetX.absoluteValue > offsetY.absoluteValue) {
                            if (offsetX < -5) {
                                swipeLeft()
                            } else if (offsetX > 5) {
                                swipeRight()
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp()
                            } else if (offsetY > 5) {
                                swipeDown()
                            }
                        }
                    }
                }
                return true
            }

        })
    }

    private fun swipeUp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun swipeDown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun swipeLeft() {

    }

    private fun swipeRight() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val cardWidth = min(w, h).minus(10).div(4)
        addCards(cardWidth)
        startGame()
    }

    private fun addCards(side: Int) {
        for (y in 0..4) {
            addView(LinearLayout(context).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, side)
                for (x in 0..4) {
                    val card = Card(context).apply {
                        setNumber(0)
                    }
                    addView(card, side, side)
                    mCardsMap[x][y] = card
                }
            })
        }
    }

    private fun startGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
