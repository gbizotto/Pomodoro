package br.gbizotto.pomodoro.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Gabriela on 10/12/2017.
 */
open class Pomodoro: RealmObject() {

    @PrimaryKey
    var id: Int ? = null
    var duration: Long ? = null
    var finished: Boolean = true
    var dateAdded: Date = Date()

    override fun toString(): String {
        return "Pomodoro(id=$id, duration=$duration, finished=$finished, dateAdded=$dateAdded)"
    }
}