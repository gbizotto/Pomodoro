package br.gbizotto.pomodoro.history.viewModel

import android.databinding.BaseObservable
import java.util.*

/**
 * Created by Gabriela on 10/12/2017.
 */
class HistoryItemViewModel : BaseObservable() {

    var dateAddedLabel : String? = null

    var dateAdded : Date? = null
    var finished : Boolean = true
    var duration : Long? = null
}