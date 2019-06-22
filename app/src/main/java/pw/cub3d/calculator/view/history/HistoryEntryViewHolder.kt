package pw.cub3d.calculator.view.history

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pw.cub3d.calculator.R
import pw.cub3d.calculator.models.HistoryEntry

class HistoryEntryViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    private val equation = view.findViewById<TextView>(R.id.historyEntry_equation)!!
    private val result = view.findViewById<TextView>(R.id.historyEntry_result)!!

    fun bindModel(historyEntry: HistoryEntry) {
        equation.text = historyEntry.query
        result.text = historyEntry.answer
    }
}