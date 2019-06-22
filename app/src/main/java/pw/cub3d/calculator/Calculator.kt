package pw.cub3d.calculator

import android.app.Application
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.sql.KotlinConfiguration
import io.requery.sql.KotlinEntityDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pw.cub3d.calculator.calculate.*
import pw.cub3d.calculator.models.Models
import pw.cub3d.calculator.models.repos.HistoryEntries

class Calculator: Application() {

    private val modules = module {
        single { CalculationManager(get(), get()) }
        single { EquationFormatter() }
        factory { EquationManager() }
        single { RPNEvaluator() }
        single { RPNConverter() }

        single { DatabaseSource(get(), Models.DEFAULT, 1) }
        single { KotlinEntityDataStore<Persistable>(get<DatabaseSource>().configuration) }

        single { HistoryEntries(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Calculator)

            modules(modules)
        }
    }
}