package pw.cub3d.calculator.stateMachine

data class StateLink(val startID: String, val endID: String, val pattern: Regex)

class StateMachine {
    private var currentState = ""

    val states = mutableListOf<String>()
    val connections = mutableListOf<StateLink>()

    private fun findNextStateFor(input: String): String? {
        return connections.filter { it.startID == currentState }
            .find { it.pattern.matches(input) }?.endID
    }

    fun willAccept(input: String): Boolean = findNextStateFor(input) != null

    fun consume(input: String) {
        findNextStateFor(input)?.let {
            currentState = it
        }
    }

    fun state(): String = currentState

    inline fun node(f: ()->String): NodeBuilder {
        states.add(f())
        return NodeBuilder(this, f())
    }

    fun startAt(f: String) {
        currentState = f
    }
}

class NodeBuilder(private val sm: StateMachine, private val startID: String) {
    val connections = mutableListOf<Pair<String, Regex>>()

    inline fun connect(f: ()->Pair<String, Regex>): NodeBuilder {
        connections.add(f())
        return this
    }

    fun done() {
        connections.forEach {
            sm.connections.add(StateLink(startID, it.first, it.second))
        }
    }
}

typealias StateMachineDecleration = StateMachine.() -> Unit

fun newStateMachine(stateMachineDecl: StateMachineDecleration): StateMachine {
    val sm = StateMachine()
    stateMachineDecl(sm)
    return sm
}

infix fun String.whenMatching(other: String): Pair<String, Regex> = Pair(this, Regex(other))