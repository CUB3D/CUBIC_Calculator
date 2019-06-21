package pw.cub3d.calculator.calculate

class EquationFormatter {
    fun format(equation: Equation): String {
        return equation.tokens.joinToString(separator = "") {
            it.formattedValue
        }
    }
}