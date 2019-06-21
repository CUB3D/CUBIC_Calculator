package pw.cub3d.calculator.calculate

import java.math.BigDecimal
import java.util.*

data class CalculationToken(val tokenType: TokenType, var value: String) {
    val formattedValue: String
        get() = when(tokenType) {
                TokenType.NUMBER -> "%,f".format(Locale.getDefault(), value.toDouble())
                    .replace(Regex("\\.0+"), "")
                else -> value
            }

    val numberValue: BigDecimal
        get() = BigDecimal(value)
}