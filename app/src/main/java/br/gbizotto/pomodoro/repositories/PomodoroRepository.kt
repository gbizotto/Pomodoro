package br.gbizotto.pomodoro.repositories

import br.gbizotto.pomodoro.model.Pomodoro
import io.realm.Realm
import io.realm.Sort

/**
 * Created by Gabriela on 10/12/2017.
 */
object PomodoroRepository  {

    fun insert(pomodoro: Pomodoro) {
        pomodoro.id = getNextId(pomodoro)
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm -> realm.insertOrUpdate(realm.copyToRealmOrUpdate(pomodoro)) }
        realm.close()
    }

    fun list() : List<Pomodoro> {
        val realm = Realm.getDefaultInstance()
        val query = realm.where(Pomodoro::class.java).sort("dateAdded", Sort.DESCENDING)
        return query.findAll()
    }

    private fun getNextId(pomodoro: Pomodoro): Int {
        return getLastId(pomodoro) + 1
    }

    private fun getLastId(pomodoro: Pomodoro): Int {
        val realm = Realm.getDefaultInstance()
        val number = realm.where(pomodoro.javaClass).max("id")
        return number?.toInt() ?: 0
    }
}