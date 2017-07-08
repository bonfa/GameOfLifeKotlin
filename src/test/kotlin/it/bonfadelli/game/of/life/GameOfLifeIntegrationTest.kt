package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class GameOfLifeIntegrationTest : BehaviorSpec() {
    private val gameOfLifeFormatter = GameOfLifeFormatter(hashMapOf(Cell.State.ALIVE to '*', Cell.State.DEAD to '.'))
    private val gameOfLifeStringParser = GameOfLifeStringParser(hashMapOf('*' to Cell.State.ALIVE, '.' to Cell.State.DEAD))

    init {
        given("a single dead cell") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            `when`("the cell evolves") {
                val evolution = game.evolve(".")
                then("it is still dead") {
                    evolution shouldBe "."
                }
            }
        }

        given("two dead cells") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            `when`("the cells evolve") {
                val evolution = game.evolve("..")
                then("they are both dead") {
                    evolution shouldBe ".."
                }
            }
        }

        given("three alive cells") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            `when`("the cells evolve") {
                val evolution = game.evolve("***")
                then("the central cell is alive") {
                    evolution shouldBe ".*."
                }
            }
        }

        given("two alive and one dead cells") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            val anotherGame: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            val yetAnotherGame: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            `when`("the cells evolve") {
                val evolution = game.evolve("**.")
                val anotherEvolution = anotherGame.evolve(".**")
                val yetAnotherEvolution = yetAnotherGame.evolve("*.*")
                then("all the cells are dead") {
                    evolution shouldBe "..."
                    anotherEvolution shouldBe "..."
                    yetAnotherEvolution shouldBe "..."
                }
            }
        }
    }
}

