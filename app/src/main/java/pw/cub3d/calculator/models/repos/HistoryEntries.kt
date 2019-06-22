package pw.cub3d.calculator.models.repos

import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import pw.cub3d.calculator.models.HistoryEntryEntity

class HistoryEntries(private val db: KotlinEntityDataStore<Persistable>) {
    fun all(): List<HistoryEntryEntity> {
        val results = db.select(HistoryEntryEntity::class).get()

        return results.toList()
    }
}