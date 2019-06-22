package pw.cub3d.calculator.models

import android.os.Parcelable
import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable

//@Entity
//data class HistoryEntry constructor(
//    @get:Key
//    @get:Generated
//    var id: Int,
//    var date: String,
//    var query: String,
//    var answer: String
//)

@Entity
interface HistoryEntry : Persistable {
    @get:Key
    @get:Generated
    val id: Int

    var date: String

    var query: String

    var answer: String
}