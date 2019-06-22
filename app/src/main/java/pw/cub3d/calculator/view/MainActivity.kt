package pw.cub3d.calculator.view

import android.content.Intent
import android.icu.text.SymbolTable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.requery.Persistable
import io.requery.sql.KotlinEntityDataStore
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import pw.cub3d.calculator.R
import pw.cub3d.calculator.calculate.*
import pw.cub3d.calculator.models.HistoryEntry
import pw.cub3d.calculator.models.HistoryEntryEntity
import pw.cub3d.calculator.subscribe
import pw.cub3d.calculator.unsubscribe
import pw.cub3d.calculator.view.history.HistoryActivity

class MainActivity : AppCompatActivity() {

    private val equationManager: EquationManager by inject()
    private val equationFormatter: EquationFormatter by inject()
    private val calculationManager: CalculationManager by inject()

    private val db: KotlinEntityDataStore<Persistable> by inject()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCalculationChange(event: EquationChangeEvent) {
        calc_equation.text = equationFormatter.format(event.equation)
        calc_result.text = calculationManager.solveEquation(equationManager.equation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()

        arrayOf(calc_zero, calc_one, calc_two, calc_three, calc_four, calc_five, calc_six, calc_seven, calc_eight, calc_nine)
            .forEach { button ->
                button.setOnClickListener {
                    equationManager.addSymbol(TokenType.NUMBER, button.text.toString())
                }
            }

        calc_backspace.setOnClickListener { equationManager.removeLastSymbol() }
        calc_backspace.setOnLongClickListener { equationManager.resetCalculation(); true }

        calc_divide.setOnClickListener { equationManager.addSymbol(TokenType.DIVIDE) }
        calc_multiply.setOnClickListener { equationManager.addSymbol(TokenType.MULTIPLY) }
        calc_subtract.setOnClickListener { equationManager.addSymbol(TokenType.SUBTRACT) }
        calc_add.setOnClickListener { equationManager.addSymbol(TokenType.ADD) }

        calc_equals.setOnClickListener {
            calc_result.text = calculationManager.solveEquation(equationManager.equation)

            val historyEntry = HistoryEntryEntity()
            historyEntry.query = equationFormatter.format(equationManager.equation)
            historyEntry.answer = calc_result.text.toString()

            db.insert(historyEntry)

            val count = db.count(HistoryEntry::class).get()

            println("Have ${count.value()} entries")
        }

        calc_openParen.setOnClickListener { equationManager.addSymbol(TokenType.LEFT_PAREN) }
        calc_closeParen.setOnClickListener { equationManager.addSymbol(TokenType.RIGHT_PAREN) }
        calc_exp.setOnClickListener { equationManager.addSymbol(TokenType.POWER) }

    }

    override fun onDestroy() {
        unsubscribe()
        super.onDestroy()
    }
}
