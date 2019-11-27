package xyz.mengxy.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
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
    private val mEmptyPoints = mutableListOf<Point>()

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
        var mergeTag = false
        var score = 0
        for (x in mCardsMap.indices) {
            var y = 3
            while (y >= 0) {
                for (y1 in y - 1 downTo 0) {
                    if (mCardsMap[x][y1].getNumber() > 0) {
                        if (mCardsMap[x][y].getNumber() <= 0) {
                            mCardsMap[x][y].setNumber(mCardsMap[x][y1].getNumber())
                            mCardsMap[x][y1].setNumber(0)
                            y++
                            mergeTag = true
                        } else if (mCardsMap[x][y] == mCardsMap[x][y1]) {
                            val num = mCardsMap[x][y].getNumber().times(2)
                            mCardsMap[x][y].setNumber(num)
                            mCardsMap[x][y1].setNumber(0)
                            score += num
                            mergeTag = true
                        }
                        break
                    }
                }
                y--
            }
        }
        if (score > 0) {
            ScorePreference.addScore(score)
        }
        if (mergeTag) {
            addRandomNumber()
            checkComplete()
        }
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
        for (y in 0..3) {
            addView(LinearLayout(context).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, side)
                for (x in 0..3) {
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
        ScorePreference.resetTotalScore()
        for (line in mCardsMap) {
            for (card in line) {
                card.setNumber(0)
            }
        }
        addRandomNumber()
        addRandomNumber()
    }

    private fun addRandomNumber() {
        mEmptyPoints.clear()
        for (y in mCardsMap.indices) {
            for (x in mCardsMap[y].indices) {
                if (mCardsMap[x][y].getNumber() <= 0) {
                    mEmptyPoints.add(Point(x, y))
                }
            }
        }
        val point = mEmptyPoints.removeAt((Math.random() * mEmptyPoints.size).toInt())
        mCardsMap[point.x][point.y].setNumber(if (Math.random() > 0.1) 2 else 4)
    }

    private fun checkComplete() {
        // ?? 二维数组/矩阵方程 相邻两数相等
        for (y in mCardsMap.indices) {
            for (x in mCardsMap[y].indices) {
                if (mCardsMap[x][y].getNumber() == 0 ||
                        (x > 0 && mCardsMap[x][y] == mCardsMap[x - 1][y]) ||
                        (x < 3 && mCardsMap[x][y] == mCardsMap[x + 1][y]) ||
                        (y > 0 && mCardsMap[x][y] == mCardsMap[x][y - 1]) ||
                        (y < 3 && mCardsMap[x][y] == mCardsMap[x][y + 1])) {
                    return
                }
            }
        }
        //todo show finish dialog
    }
}
