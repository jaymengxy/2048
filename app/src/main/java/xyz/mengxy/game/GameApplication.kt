package xyz.mengxy.game

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by Mengxy on 2019-11-26.
 */

class GameApplication : Application() {

    companion object {
        var instance: GameApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
