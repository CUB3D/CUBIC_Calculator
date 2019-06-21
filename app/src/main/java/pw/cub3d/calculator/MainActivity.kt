package pw.cub3d.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import pw.cub3d.calculator.calculate.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val equationManager = EquationManager()
    private val equationFormatter = EquationFormatter()
    private val calculationManager = CalculationManager()
    private val rpnEvaluator = RPNEvaluator()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCalculationChange(event: EquationChangeEvent) {
        calc_equation.text = equationFormatter.format(event.equation)
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
            val rpn = calculationManager.convertEquationToRPN(
                equationManager.equation
            ).reversed()

            val rpnStack = Stack<CalculationToken>()
            rpnStack.addAll(rpn)

            calc_result.text = rpnEvaluator.evaluateRPN(rpnStack).toString()
        }

    }

    override fun onDestroy() {
        unsubscribe()
        super.onDestroy()
    }
}
