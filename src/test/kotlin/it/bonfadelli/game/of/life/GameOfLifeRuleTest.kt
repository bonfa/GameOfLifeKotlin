package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec
import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeRuleTest : BehaviorSpec() {

    private val ALIVE_CELL = Cell(ALIVE)
    private val DEAD_CELL = Cell(DEAD)
    private val game: GameOfLifeRule = GameOfLifeRule()

    init {
        given("a single dead cell") {
            `when`("the cell evolves") {
                then("it is still dead") {
                    val evolve = game.evolve(listOf(listOf(DEAD_CELL)))
                    evolve shouldBe listOf(listOf(DEAD_CELL))
                }
            }
        }

        given("two dead cells in a row") {
            `when`("the cells evolve") {
                then("they are both dead") {
                    game.evolve(listOf(listOf(DEAD_CELL, DEAD_CELL))) shouldBe listOf(listOf(DEAD_CELL, DEAD_CELL))
                }
            }
        }

        given("three alive cells in a row") {
            `when`("the cells evolve") {
                then("the central cell is alive") {
                    game.evolve(listOf(listOf(ALIVE_CELL, ALIVE_CELL, ALIVE_CELL))) shouldBe listOf(listOf(DEAD_CELL, ALIVE_CELL, DEAD_CELL))
                }
            }
        }

        given("two alive and one dead cells in a row") {
            `when`("the cells evolve") {
                then("all the cells are dead") {
                    game.evolve(listOf(listOf(ALIVE_CELL, ALIVE_CELL, DEAD_CELL))) shouldBe listOf(listOf(DEAD_CELL, DEAD_CELL, DEAD_CELL))
                    game.evolve(listOf(listOf(DEAD_CELL, ALIVE_CELL, ALIVE_CELL))) shouldBe listOf(listOf(DEAD_CELL, DEAD_CELL, DEAD_CELL))
                    game.evolve(listOf(listOf(ALIVE_CELL, DEAD_CELL, ALIVE_CELL))) shouldBe listOf(listOf(DEAD_CELL, DEAD_CELL, DEAD_CELL))
                }
            }
        }

        given("two alive cells in column") {
            `when`("the cells evolve") {
                then("they are both dead") {
                    game.evolve(listOf(listOf(ALIVE_CELL), listOf(ALIVE_CELL))) shouldBe listOf(listOf(DEAD_CELL), listOf(DEAD_CELL))

                }
            }
        }

        given("three alive cells in column") {
            `when`("the cells evolve") {
                then("they are both dead") {
                    game.evolve(listOf(listOf(ALIVE_CELL), listOf(ALIVE_CELL), listOf(ALIVE_CELL))) shouldBe listOf(listOf(DEAD_CELL), listOf(ALIVE_CELL), listOf(DEAD_CELL))

                }
            }
        }
    }

}