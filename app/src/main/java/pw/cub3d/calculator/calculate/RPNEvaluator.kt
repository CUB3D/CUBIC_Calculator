package pw.cub3d.calculator.calculate

import java.math.BigDecimal
import java.util.*

class RPNEvaluator {
    fun evaluateRPN(tokens: Stack<CalculationToken>): BigDecimal {

        val working = Stack<BigDecimal>()

        while(tokens.isNotEmpty()) {
            val token = tokens.pop()

            if(token.tokenType.isOperator) {
                val arg2 = working.pop()
                val arg1 = working.pop()

                if(token.tokenType == TokenType.ADD) {
                    working.push(arg2.add(arg1))
                }
                if(token.tokenType == TokenType.MULTIPLY) {
                    working.push(arg2.multiply(arg1))
                }
                if(token.tokenType == TokenType.SUBTRACT) {
                    working.push(arg1.subtract(arg2))
                }
                if(token.tokenType == TokenType.DIVIDE) {
                    working.push(arg1.divide(arg2))
                }
            } else {
                working.push(token.numberValue)
            }
        }

        return working.pop()
    }
}