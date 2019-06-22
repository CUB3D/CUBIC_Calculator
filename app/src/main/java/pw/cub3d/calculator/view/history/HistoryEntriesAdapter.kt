package pw.cub3d.calculator.view.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pw.cub3d.calculator.R
import pw.cub3d.calculator.models.HistoryEntry

class HistoryEntriesAdapter(ctx: Context, val historyEntries: List<HistoryEntry>): RecyclerView.Adapter<HistoryEntryViewHolder>() {
    private val inflater = LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryEntryViewHolder {
        return HistoryEntryViewHolder(
            inflater.inflate(R.layout.history_entry, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return historyEntries.size
    }

    override fun onBindViewHolder(holder: HistoryEntryViewHolder, position: Int) {
        holder.bindModel(historyEntries[position])
    }

}