package pw.cub3d.calculator.calculate

import java.lang.Exception
import java.util.*
import java.util.concurrent.ExecutionException

class CalculationManager(
    val rpnConverter: RPNConverter,
    val rpnEvaluator: RPNEvaluator
) {

    fun solveEquation(equation: Equation): String {
        return try {
            val tmpStack = Stack<CalculationToken>()
            val rpnEquation = rpnConverter.convertEquationToRPN(equation).reversed()
            tmpStack.addAll(rpnEquation)

            val result = rpnEvaluator.evaluateRPN(tmpStack)

            result.toPlainString()
        } catch (e: Exception) {
            e.printStackTrace()

            ""
        }
    }
}