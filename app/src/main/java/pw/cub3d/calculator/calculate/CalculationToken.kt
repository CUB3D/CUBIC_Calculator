package pw.cub3d.calculator.calculate

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

val decimalFormat = DecimalFormat("")

data class CalculationToken(val tokenType: TokenType, var value: String) {
    val formattedValue: String
        get() = when(tokenType) {
                TokenType.NUMBER -> {
                    val hasPointAtEnd = value.last() == '.'
                    val formattedNumber = NumberFormat.getNumberInstance(Locale.getDefault()).format(numberValue)

                    if(hasPointAtEnd) {
                        "$formattedNumber."
                    } else {
                        formattedNumber
                    }
                }
                else -> value
            }

    val numberValue: BigDecimal
        get() = BigDecimal(value)
}