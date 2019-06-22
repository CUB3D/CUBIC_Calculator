package pw.cub3d.calculator.calculate

import java.util.*

class RPNConverter {
    fun convertEquationToRPN(equation: Equation): Stack<CalculationToken> {
        return convertTokensToRPN(equation.tokens.reversed())
    }

    fun convertTokensToRPN(tokens: List<CalculationToken>): Stack<CalculationToken> {
        val input = Stack<CalculationToken>()
        input.addAll(tokens)

        val output = Stack<CalculationToken>()
        val operators = Stack<CalculationToken>()

        while(input.isNotEmpty()) {
            val token = input.pop()
            val type = token.tokenType

            if(type == TokenType.NUMBER) {
                output.push(token)
            }
            if(type.isFunction) {
                operators.push(token)
            }
            if(type.isOperator) {
                if(operators.isNotEmpty()) {
                    var topOfOperators = operators.peek()
                    while (
                        (
                                topOfOperators.tokenType.isFunction ||
                                        topOfOperators.tokenType.precedence > type.precedence ||
                                        (topOfOperators.tokenType.precedence == type.precedence && topOfOperators.tokenType.leftAssociative)
                                ) &&
                        topOfOperators.tokenType != TokenType.LEFT_PAREN
                    ) {
                        output.push(operators.pop())

                        topOfOperators = operators.peek()
                    }
                }
                operators.push(token)
            }
            if(type == TokenType.LEFT_PAREN) {
                operators.push(token)
            }
            if(type == TokenType.RIGHT_PAREN) {
                var topOfOperators = operators.peek()
                while(topOfOperators.tokenType != TokenType.LEFT_PAREN) {
                    output.push(operators.pop())

                    if(operators.isEmpty()) {
                        throw MismatchedParenthesisException()
                    }

                    topOfOperators = operators.peek()
                }

                if(topOfOperators.tokenType == TokenType.LEFT_PAREN) {
                    operators.pop()
                }
            }
        }

        while(operators.isNotEmpty()) {
            val op = operators.pop()

            if(op.tokenType == TokenType.LEFT_PAREN || op.tokenType == TokenType.RIGHT_PAREN) {
                throw MismatchedParenthesisException()
            }

            output.push(op)
        }

        return output
    }
}

class MismatchedParenthesisException : Throwable("Mismatched parenthesis")