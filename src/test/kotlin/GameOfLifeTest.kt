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

class GameOfLife(private val cells: String) {
    fun evolve(): String {
        var evolution:String = ""
        for (i in 0..cells.count()-1) {
            var numberOfAliveSiblings = 0
            val indexOfPreviousCell = i-1
            if (indexOfPreviousCell >= 0 && cells[indexOfPreviousCell]=='*') {
                numberOfAliveSiblings++
            }
            val indexOfFollowingCell = i+1
            if (indexOfFollowingCell < cells.length && cells[indexOfFollowingCell]=='*') {
                numberOfAliveSiblings++
            }

            if (cells[i]=='*') {
                if (numberOfAliveSiblings == 2) {
                    evolution += '*'
                }
                else {
                    evolution += "."
                }
            }
            else {
                evolution += '.'
            }
        }
        return evolution
    }
}
