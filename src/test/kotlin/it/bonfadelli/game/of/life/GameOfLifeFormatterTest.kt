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
    private val lineSeparator = '\n'
    private val formatter = GameOfLifeFormatter(alphabet, lineSeparator)

    init {
        given("a game of life formatter and a dead cell") {
            val cells = listOf(listOf(Cell(DEAD)))
            `when`("the cell is formatted") {
                val formattedString = formatter.format(cells)
                then("it is represented with the dead symbol") {
                    formattedString shouldBe deadSymbol
                }
            }
        }

        given("a game of life formatter and an alive cell") {
            val cells = listOf(listOf(Cell(ALIVE)))
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
                    val exception = shouldThrow<GameOfLifeFormatter.InvalidAlphabetException> {
                        GameOfLifeFormatter(alphabetWithOnlyAliveSymbol, lineSeparator)
                    }
                    exception.message shouldBe "The alphabet does not contain the symbol for the DEAD state"

                    val exception2 = shouldThrow<GameOfLifeFormatter.InvalidAlphabetException> {
                        GameOfLifeFormatter(alphabetWithOnlyDeadSymbol, lineSeparator)
                    }
                    exception2.message shouldBe "The alphabet does not contain the symbol for the ALIVE state"
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val cells = listOf(listOf(Cell(ALIVE), Cell(DEAD), Cell(ALIVE), Cell(ALIVE), Cell(DEAD)))
            `when`("the cells are formatted") {
                val formattedString = formatter.format(cells)
                then("they are represented with the correct symbols") {
                    formattedString shouldBe aliveSymbol + deadSymbol + aliveSymbol + aliveSymbol + deadSymbol
                }
            }
        }

        given("a grid of symbols present in the alphabet") {
            val cells = listOf(
                    listOf(Cell(ALIVE), Cell(DEAD)),
                    listOf(Cell(ALIVE), Cell(ALIVE))
            )
            `when`("the cells are formatted") {
                val formattedString = formatter.format(cells)
                then("they are represented with the correct symbols") {
                    formattedString shouldBe aliveSymbol + deadSymbol + lineSeparator + aliveSymbol + aliveSymbol
                }
            }
        }
    }

}