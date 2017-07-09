package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeStringParserTest : BehaviorSpec() {

    private val aliveSymbol = "*"
    private val deadSymbol = "."
    private val aSymbolNotPresentInTheAlphabet = "c"
    private val alphabet = hashMapOf('*' to ALIVE, '.' to DEAD)
    private val separator = '\n'
    private val parser = GameOfLifeStringParser(alphabet, separator)

    init {
        given("a parser and a cell represented with the dead symbol") {
            `when`("the cell is parsed") {
                val cells = parser.parse(deadSymbol);
                then("its state is dead") {
                    cells shouldBe listOf(listOf(Cell(DEAD)))
                }
            }
        }

        given("a parser and a cell represented with the alive symbol") {
            `when`("the cell is parsed") {
                val cells = parser.parse(aliveSymbol);
                then("its state is alive") {
                    cells shouldBe listOf(listOf(Cell(ALIVE)))
                }
            }
        }

        given("a parser and a symbol not present in the alphabet") {
            `when`("the cell is parsed") {
                then("the parser throws an exception") {
                    shouldThrow<GameOfLifeStringParser.ParseException> {
                        parser.parse(aSymbolNotPresentInTheAlphabet);
                    }
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val lineOfSymbols = deadSymbol + aliveSymbol + aliveSymbol + aliveSymbol + deadSymbol + deadSymbol
            `when`("the cells are parsed") {
                val cells = parser.parse(lineOfSymbols)
                then("they have the correct status") {
                    cells shouldBe listOf(listOf(Cell(DEAD), Cell(ALIVE), Cell(ALIVE), Cell(ALIVE), Cell(DEAD), Cell(DEAD)))
                }
            }
        }

        given("a matrix of symbols present in the alphabet") {
            val matrixOfSymbols = deadSymbol + aliveSymbol + separator + deadSymbol + deadSymbol
            `when`("the cells are parsed") {
                val cells = parser.parse(matrixOfSymbols)
                then("they have the correct status") {
                    cells shouldBe listOf(
                            listOf(Cell(DEAD), Cell(ALIVE)),
                            listOf(Cell(DEAD), Cell(DEAD))
                    )
                }
            }
        }
    }

}
