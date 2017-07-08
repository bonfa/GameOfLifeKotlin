package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.*

class GameOfLifeRuleTest : BehaviorSpec() {

    private val ALIVE_CELL = Cell(ALIVE)
    private val DEAD_CELL = Cell(DEAD)
    private val game: GameOfLifeRule = GameOfLifeRule()

    init {
        given("a single dead cell") {
            `when`("the cell evolves") {
                then("it is still dead") {
                    game.evolve(arrayOf(DEAD_CELL)) shouldBe arrayOf(DEAD_CELL)
                }
            }
        }

        given("two dead cells") {
            `when`("the cells evolve") {
                then("they are both dead") {
                    game.evolve(arrayOf(DEAD_CELL, DEAD_CELL)) shouldBe arrayOf(DEAD_CELL, DEAD_CELL)
                }
            }
        }

        given("three alive cells") {
            `when`("the cells evolve") {
                then("the central cell is alive") {
                    game.evolve(arrayOf(ALIVE_CELL, ALIVE_CELL, ALIVE_CELL)) shouldBe arrayOf(DEAD_CELL, ALIVE_CELL, DEAD_CELL)
                }
            }
        }

        given("two alive and one dead cells") {
            `when`("the cells evolve") {
                then("all the cells are dead") {
                    game.evolve(arrayOf(ALIVE_CELL, ALIVE_CELL, DEAD_CELL)) shouldBe arrayOf(DEAD_CELL, DEAD_CELL, DEAD_CELL)
                    game.evolve(arrayOf(DEAD_CELL, ALIVE_CELL, ALIVE_CELL)) shouldBe arrayOf(DEAD_CELL, DEAD_CELL, DEAD_CELL)
                    game.evolve(arrayOf(ALIVE_CELL, DEAD_CELL, ALIVE_CELL)) shouldBe arrayOf(DEAD_CELL, DEAD_CELL, DEAD_CELL)
                }
            }
        }
    }


}