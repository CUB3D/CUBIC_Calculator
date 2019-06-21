package pw.cub3d.calculator

import org.junit.Test

import org.junit.Assert.*
import pw.cub3d.calculator.calculate.*

class TestCalculationManager {
    val calculationManager = CalculationManager()

    @Test
    fun testSimple() {
        val rpn = calculationManager.convertTokensToRPN(listOf(
            CalculationToken(TokenType.NUMBER, "100"),
            CalculationToken(TokenType.ADD, "+"),
            CalculationToken(TokenType.NUMBER, "50")
        ).reversed())

        assertEquals(rpn.joinToString(separator = " ") { it.formattedValue }, "100 50 +")
    }

    @Test
    fun testSimple2() {
        val rpn = calculationManager.convertTokensToRPN(listOf(
            CalculationToken(TokenType.NUMBER, "3"),
            CalculationToken(TokenType.ADD, "+"),
            CalculationToken(TokenType.NUMBER, "4"),
            CalculationToken(TokenType.MULTIPLY, "x"),
            CalculationToken(TokenType.NUMBER, "2"),
            CalculationToken(TokenType.DIVIDE, "/"),
            CalculationToken(TokenType.LEFT_PAREN, "("),
            CalculationToken(TokenType.NUMBER, "1"),
            CalculationToken(TokenType.SUBTRACT, "-"),
            CalculationToken(TokenType.NUMBER, "5"),
            CalculationToken(TokenType.RIGHT_PAREN, ")"),
            CalculationToken(TokenType.POWER, "^"),
            CalculationToken(TokenType.NUMBER, "2"),
            CalculationToken(TokenType.POWER, "^"),
            CalculationToken(TokenType.NUMBER, "3")
            ).reversed())

        assertEquals(rpn.joinToString(separator = " ") { it.formattedValue }, "3 4 2 x 1 5 - 2 3 ^ ^ / +")
    }
}
