package pw.cub3d.calculator

import org.junit.Test

import org.junit.Assert.*
import pw.cub3d.calculator.stateMachine.newStateMachine
import pw.cub3d.calculator.stateMachine.whenMatching

class TestStateMachine {
    @Test
    fun testSimple() {
        val sm = newStateMachine {
            node { "NULL" }
                .connect { "NUMBER" whenMatching "\\d" }
                .done()

            node { "NUMBER" }
                .connect { "NUMBER" whenMatching  "\\d+" }
                .connect { "PLUS" whenMatching  "\\+" }
                .connect { "MULTIPLY" whenMatching  "\\*" }
                .connect { "DIVIDE" whenMatching  "\\\\" }
                .done()

            //Might be nice to be able to do
            /**
             * node {
             *  withName { 'asdf' }
             *  connect { 'asdfasdf' whenMatching' '\\d' }
             *  //etc
             * }
             */

            startAt("NULL")
        }

        assertEquals(sm.state(), "NULL")
        assertTrue(sm.willAccept("1"))
        sm.consume("5")
        assertEquals(sm.state(), "NUMBER")
    }
}
