package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeFormatterTest : BehaviorSpec() {

    private val deadSymbol = "."
    private val aliveSymbol = "*"

    init {
        given("a game of life formatter and a dead cell") {
            val formatter = GameOfLifeFormatter()
            val cells = arrayOf(Cell(DEAD))
            `when`("the cell is formatted") {
                val formattedString = formatter.format(cells)
                then("it is represented with the dead symbol") {
                    formattedString shouldBe deadSymbol
                }
            }
        }

        given("a game of life formatter and an alive cell") {
            val formatter = GameOfLifeFormatter()
            val cells = arrayOf(Cell(ALIVE))
            `when`("the cell is formatted") {
                val formattedString = formatter.format(cells)
                then("it is represented with the alive symbol") {
                    formattedString shouldBe aliveSymbol
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val formatter = GameOfLifeFormatter()
            val cells = arrayOf(Cell(ALIVE), Cell(DEAD), Cell(ALIVE), Cell(ALIVE), Cell(DEAD))
            `when`("the cells are formatted") {
                val formattedString = formatter.format(cells)
                then("they are represented with the correct symbols") {
                    formattedString shouldBe aliveSymbol + deadSymbol + aliveSymbol + aliveSymbol + deadSymbol
                }
            }
        }
    }
}