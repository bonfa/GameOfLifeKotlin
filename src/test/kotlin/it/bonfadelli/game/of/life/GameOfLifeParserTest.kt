package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeParserTest : BehaviorSpec() {
    init {
        given("a parser and a cell represented with the dead symbol") {
            val deadSymbol = "."
            val parser = GameOfLifeParser(hashMapOf('*' to ALIVE, '.' to DEAD))
            `when`("the cell is parsed") {
                val evolution = parser.parse(deadSymbol);
                then("its state is still dead") {
                    evolution shouldBe arrayOf(Cell(DEAD))
                }
            }
        }

        given("a parser and a cell represented with the alive symbol") {
            val deadSymbol = "*"
            val parser = GameOfLifeParser(hashMapOf('*' to ALIVE, '.' to DEAD))
            `when`("the cell is parsed") {
                val evolution = parser.parse(deadSymbol);
                then("its state is still dead") {
                    evolution shouldBe arrayOf(Cell(ALIVE))
                }
            }
        }

        given("a parser and a symbol not present in the alphabet") {
            val deadSymbol = "c"
            val parser = GameOfLifeParser(hashMapOf('*' to ALIVE, '.' to DEAD))
            `when`("the cell is parsed") {
                then("its state is still dead") {
                    shouldThrow<GameOfLifeParser.ParseException> {
                        parser.parse(deadSymbol);
                    }
                }
            }
        }

        given("a line of symbols") {
            val deadSymbol = ".*"
            val parser = GameOfLifeParser(hashMapOf('*' to ALIVE, '.' to DEAD))
            `when`("the cell is parsed") {
                val evolution = parser.parse(deadSymbol);
                then("its state is still dead") {
                    evolution shouldBe arrayOf(Cell(DEAD), Cell(ALIVE))
                }
            }
        }
    }

}
