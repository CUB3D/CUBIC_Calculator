package pw.cub3d.calculator.calculate

import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
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

            // Check the the remainder is zero, if it is print no zeros
            if(result
                    .remainder(BigDecimal.ONE)
                    .movePointRight(result.scale())
                    .abs()
                    .toBigInteger()
                    .compareTo(BigInteger.ZERO) == 0
            ) {
                result.toPlainString().replace(Regex("\\.0+"), "")
            } else {
                result.toPlainString()
            }
        } catch (e: Exception) {
            e.printStackTrace()

            ""
        }
    }
}