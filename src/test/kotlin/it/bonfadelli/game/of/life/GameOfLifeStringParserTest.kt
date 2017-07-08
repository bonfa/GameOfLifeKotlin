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

    init {
        given("a parser and a cell represented with the dead symbol") {
            val symbol = deadSymbol
            val parser = GameOfLifeStringParser(alphabet)
            `when`("the cell is parsed") {
                val cells = parser.parse(symbol);
                then("its state is dead") {
                    cells shouldBe arrayOf(Cell(DEAD))
                }
            }
        }

        given("a parser and a cell represented with the alive symbol") {
            val alive = aliveSymbol
            val parser = GameOfLifeStringParser(alphabet)
            `when`("the cell is parsed") {
                val cells = parser.parse(alive);
                then("its state is alive") {
                    cells shouldBe arrayOf(Cell(ALIVE))
                }
            }
        }

        given("a parser and a symbol not present in the alphabet") {
            val symbol = aSymbolNotPresentInTheAlphabet
            val parser = GameOfLifeStringParser(alphabet)
            `when`("the cell is parsed") {
                then("the parser throws an exception") {
                    shouldThrow<GameOfLifeStringParser.ParseException> {
                        parser.parse(symbol);
                    }
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val deadSymbol = deadSymbol + aliveSymbol + aliveSymbol + aliveSymbol + deadSymbol + deadSymbol
            val parser = GameOfLifeStringParser(alphabet)
            `when`("the cells are parsed") {
                val cells = parser.parse(deadSymbol);
                then("they have the correct status") {
                    cells shouldBe arrayOf(Cell(DEAD), Cell(ALIVE), Cell(ALIVE), Cell(ALIVE), Cell(DEAD), Cell(DEAD))
                }
            }
        }
    }

}
