package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeParserTest : BehaviorSpec() {

    private val alphabet = hashMapOf('*' to ALIVE, '.' to DEAD)
    private val deadSymbol = "."
    private val alive = "*"
    private val aSymbolUnknownInTheAlphabet = "c"

    init {
        given("a parser and a cell represented with the dead symbol") {
            val symbol = deadSymbol
            val parser = GameOfLifeParser(alphabet)
            `when`("the cell is parsed") {
                val cells = parser.parse(symbol);
                then("its state is still dead") {
                    cells shouldBe arrayOf(Cell(DEAD))
                }
            }
        }

        given("a parser and a cell represented with the alive symbol") {
            val alive = alive
            val parser = GameOfLifeParser(alphabet)
            `when`("the cell is parsed") {
                val cells = parser.parse(alive);
                then("its state is still alive") {
                    cells shouldBe arrayOf(Cell(ALIVE))
                }
            }
        }

        given("a parser and a symbol not present in the alphabet") {
            val symbol = aSymbolUnknownInTheAlphabet
            val parser = GameOfLifeParser(alphabet)
            `when`("the cell is parsed") {
                then("the parser throws an exception") {
                    shouldThrow<GameOfLifeParser.ParseException> {
                        parser.parse(symbol);
                    }
                }
            }
        }

        given("a line of symbols present in the alphabet") {
            val deadSymbol = ".***.."
            val parser = GameOfLifeParser(hashMapOf('*' to ALIVE, '.' to DEAD))
            `when`("the cells are parsed") {
                val cells = parser.parse(deadSymbol);
                then("the translation is correct ") {
                    cells shouldBe arrayOf(Cell(DEAD), Cell(ALIVE), Cell(ALIVE), Cell(ALIVE), Cell(DEAD), Cell(DEAD))
                }
            }
        }
    }

}
