package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeFormatterTest : BehaviorSpec() {

    private val deadSymbol = "."
    private val aliveSymbol = "*"
    private val alphabet = hashMapOf(ALIVE to '*', DEAD to '.')

    init {
        given("a game of life formatter and a dead cell") {
            val formatter = GameOfLifeFormatter(alphabet)
            val cells = arrayOf(Cell(DEAD))
            `when`("the cell is formatted") {
                val formattedString = formatter.format(cells)
                then("it is represented with the dead symbol") {
                    formattedString shouldBe deadSymbol
                }
            }
        }

        given("a game of life formatter and an alive cell") {
            val formatter = GameOfLifeFormatter(alphabet)
            val cells = arrayOf(Cell(ALIVE))
            `when`("the cell is formatted") {
                val formattedString = formatter.format(cells)
                then("it is represented with the alive symbol") {
                    formattedString shouldBe aliveSymbol
                }
            }
        }


        given("an alphabet not containing both the symbols for the dead or the alive state") {
            val alphabetWithOnlyAliveSymbol = hashMapOf(ALIVE to '*')
            val alphabetWithOnlyDeadSymbol = hashMapOf(DEAD to '.')
            `when`("the formatter is created") {
                then("the formatter throws an exception") {
                    shouldThrow<GameOfLifeFormatter.InvalidAlphabetException> {
                        GameOfLifeFormatter(alphabetWithOnlyAliveSymbol)
                    }
                    shouldThrow<GameOfLifeFormatter.InvalidAlphabetException> {
                        GameOfLifeFormatter(alphabetWithOnlyDeadSymbol)
                    }
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val formatter = GameOfLifeFormatter(alphabet)
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