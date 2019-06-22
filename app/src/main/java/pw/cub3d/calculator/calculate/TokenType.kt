package pw.cub3d.calculator.calculate

import androidx.constraintlayout.widget.Placeholder

enum class TokenType(
    val canBeRepeated: Boolean,
    val placeHolderValue: String,
    val isFunction: Boolean,
    val isOperator: Boolean,
    val precedence: Int,
    val leftAssociative: Boolean
) {
    NUMBER(true, "", false, false, 1, false),
    MULTIPLY(false, "x", false, true, 3, true),
    DIVIDE(false, "÷", false, true, 3, true),
    SUBTRACT(false, "-", false, true, 2, true),
    ADD(false, "+", false, true, 2, true),
    POWER(false, "^", false, true, 4, false),

    LEFT_PAREN(false, "(", false, false, 5, false),
    RIGHT_PAREN(false, ")", false, false, 5, false)

}