package xyz.mengxy.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {


    private val mScoreLiveDataObserver: Observer<Score> = Observer {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScorePreference.mScoreLiveData.observe(this, mScoreLiveDataObserver)
    }

}
