package xyz.mengxy.game

import androidx.lifecycle.MutableLiveData

/**
 * Created by Mengxy on 2019-11-26.
 */

object ScorePreference {

    private const val PREF_MAX_SCORE = "pref_max_score"

    private var mMaxScore = 0
    private var mTotalScore = 0

    val mScoreLiveData: MutableLiveData<Score> by lazy {
        MutableLiveData()
    }

    init {
        mMaxScore = getIntegerPreference(GameApplication.instance, PREF_MAX_SCORE)
    }

    fun addScore(score: Int) {
        mTotalScore += score
        if (mTotalScore > mMaxScore) {
            mMaxScore = mTotalScore
            updateMaxScore()
        }
        mScoreLiveData.postValue(Score(mTotalScore, mMaxScore, score))
    }

    fun resetTotalScore() {
        mTotalScore = 0
        mScoreLiveData.postValue(Score(mTotalScore, mMaxScore, 0))
    }

    private fun updateMaxScore() {
        setIntegerPreference(GameApplication.instance, PREF_MAX_SCORE, mMaxScore)
    }
}