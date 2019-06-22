package pw.cub3d.calculator.view.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.ext.android.inject
import pw.cub3d.calculator.R
import pw.cub3d.calculator.models.repos.HistoryEntries

class HistoryActivity : AppCompatActivity() {

    private val history: HistoryEntries by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        history_recycler.layoutManager = LinearLayoutManager(this)
        history_recycler.adapter = HistoryEntriesAdapter(this, history.all())
    }
}
