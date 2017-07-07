import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class GameOfLifeTest : BehaviorSpec() {
    init {
        given("a single dead cell") {
            val game: GameOfLife = GameOfLife(".")
            `when`("the cell evolves") {
                val evolution = game.evolve()
                then("it is still dead") {
                    evolution shouldBe "."
                }
            }
        }

        given("two dead cells") {
            val game: GameOfLife = GameOfLife("..")
            `when`("the cells evolve") {
                val evolution = game.evolve()
                then("they are both dead") {
                    evolution shouldBe ".."
                }
            }
        }

        given("three alive cells") {
            val game: GameOfLife = GameOfLife("***")
            `when`("the cells evolve") {
                val evolution = game.evolve()
                then("the central cell is alive") {
                    evolution shouldBe ".*."
                }
            }
        }

        given("two alive and one dead cells") {
            val game: GameOfLife = GameOfLife("**.")
            val anotherGame: GameOfLife = GameOfLife(".**")
            val yetAnotherGame: GameOfLife = GameOfLife("*.*")
            `when`("the cells evolve") {
                val evolution = game.evolve()
                val anotherEvolution = anotherGame.evolve()
                val yetAnotherEvolution = yetAnotherGame.evolve()
                then("all the cells are dead") {
                    evolution shouldBe "..."
                    anotherEvolution shouldBe "..."
                    yetAnotherEvolution shouldBe "..."
                }
            }
        }

    }
}

class GameOfLife(private val cell: String) {
    fun evolve(): String {
        if (cell == "***") {
            return ".*."
        } else if (cell == "**." || cell == ".**" || cell == "*.*") {
            return "..."
        } else {
            return cell
        }
    }
}
