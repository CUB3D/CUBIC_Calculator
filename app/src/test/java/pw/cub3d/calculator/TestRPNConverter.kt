package pw.cub3d.calculator

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.inject
import pw.cub3d.calculator.calculate.*

class TestRPNConverter : AutoCloseKoinTest() {
    private val rpnConverter: RPNConverter by inject()

    @Before
    fun before() {
        startKoin {
            modules(module {
                single { RPNConverter() }
            })
        }
    }

    @Test
    fun testSimple() {

        val rpn = rpnConverter.convertTokensToRPN(listOf(
            CalculationToken(TokenType.NUMBER, "100"),
            CalculationToken(TokenType.ADD, "+"),
            CalculationToken(TokenType.NUMBER, "50")
        ).reversed())

        assertEquals(rpn.joinToString(separator = " ") { it.formattedValue }, "100 50 +")
    }

    @Test
    fun testSimple2() {
        val rpn = rpnConverter.convertTokensToRPN(listOf(
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
