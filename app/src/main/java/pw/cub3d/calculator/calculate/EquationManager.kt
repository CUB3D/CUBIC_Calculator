package pw.cub3d.calculator.calculate

import pw.cub3d.calculator.post

data class EquationChangeEvent(val equation: Equation)

data class Equation(val tokens: List<CalculationToken>)

class EquationManager {
    private val tokens = mutableListOf<CalculationToken>()

    val equation: Equation
        get() = Equation(tokens)

    fun addSymbol(type: TokenType, value: String = type.placeHolderValue) {
        if(tokens.isNotEmpty()) {
            val last = tokens.last()
            if(last.tokenType == type) {
                if(type.canBeRepeated) {
                    last.value += value
                    alertEquationChanged()
                }

                return
            }
        }

        tokens.add(CalculationToken(type, value))

        alertEquationChanged()
    }

    fun removeLastSymbol() {
        if(tokens.isEmpty())
            return

        val lastToken = tokens.last()
        if(lastToken.tokenType.canBeRepeated && lastToken.value.length > 1) {
            lastToken.value = lastToken.value.dropLast(1)
        } else {
            tokens.remove(tokens.last())
        }

        alertEquationChanged()
    }

    fun resetCalculation() {
        tokens.clear()
        alertEquationChanged()
    }

    fun addPeriod() {
        if(tokens.isEmpty())
            return

        val lastToken = tokens.last()

        if(lastToken.tokenType == TokenType.NUMBER && !lastToken.value.contains(".")) {
            lastToken.value += "."
        }

        alertEquationChanged()
    }

    private fun alertEquationChanged() {
        EquationChangeEvent(Equation(tokens)).post()
    }

}