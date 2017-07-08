package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.*

class GameOfLifeRuleTest : BehaviorSpec() {
    init {
        given("a single dead cell") {
            val game: GameOfLifeRule = GameOfLifeRule()
            `when`("the cell evolves") {
                val evolution = game.evolve(arrayOf(Cell(DEAD)))
                then("it is still dead") {
                    evolution shouldBe arrayOf(Cell(DEAD))
                }
            }
        }

        given("two dead cells") {
            val game: GameOfLifeRule = GameOfLifeRule()
            `when`("the cells evolve") {
                val evolution = game.evolve(arrayOf(Cell(DEAD), Cell(DEAD)))
                then("they are both dead") {
                    evolution shouldBe arrayOf(Cell(DEAD), Cell(DEAD))
                }
            }
        }

        given("three alive cells") {
            val game: GameOfLifeRule = GameOfLifeRule()
            `when`("the cells evolve") {
                val evolution = game.evolve(arrayOf(Cell(ALIVE), Cell(ALIVE), Cell(ALIVE)))
                then("the central cell is alive") {
                    evolution shouldBe arrayOf(Cell(DEAD), Cell(ALIVE), Cell(DEAD))
                }
            }
        }

        given("two alive and one dead cells") {
            val game: GameOfLifeRule = GameOfLifeRule()
            val anotherGame: GameOfLifeRule = GameOfLifeRule()
            val yetAnotherGame: GameOfLifeRule = GameOfLifeRule()
            `when`("the cells evolve") {
                val evolution = game.evolve(arrayOf(Cell(ALIVE), Cell(ALIVE), Cell(DEAD)))
                val anotherEvolution = anotherGame.evolve(arrayOf(Cell(DEAD), Cell(ALIVE), Cell(ALIVE)))
                val yetAnotherEvolution = yetAnotherGame.evolve(arrayOf(Cell(ALIVE), Cell(DEAD), Cell(ALIVE)))
                then("all the cells are dead") {
                    evolution shouldBe arrayOf(Cell(DEAD), Cell(DEAD), Cell(DEAD))
                    anotherEvolution shouldBe arrayOf(Cell(DEAD), Cell(DEAD), Cell(DEAD))
                    yetAnotherEvolution shouldBe arrayOf(Cell(DEAD), Cell(DEAD), Cell(DEAD))
                }
            }
        }
    }
}