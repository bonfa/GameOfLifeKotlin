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
        var evolution: String = ""
        for (cellIndex in 0..cells.count() - 1) {
            evolution = appendEvolvedStatus(cellIndex, evolution)
        }
        return evolution
    }

    private fun appendEvolvedStatus(cellIndex: Int, evolution: String): String {
        val numberOfAliveSiblings = getNumberOfAliveSiblings(cellIndex)
        val newCellState = getNewCellState(cellIndex, numberOfAliveSiblings)
        return appendNewCellStateTo(evolution, newCellState)
    }

    private fun getNumberOfAliveSiblings(i: Int): Int {
        var numberOfAliveSiblings = 0

        val indexOfPreviousCell = i - 1
        if (indexOfPreviousCell >= 0 && cells[indexOfPreviousCell] == '*') {
            numberOfAliveSiblings++
        }

        val indexOfFollowingCell = i + 1
        if (indexOfFollowingCell < cells.length && cells[indexOfFollowingCell] == '*') {
            numberOfAliveSiblings++
        }

        return numberOfAliveSiblings
    }

    private fun getNewCellState(i: Int, numberOfAliveSiblings: Int): Char {
        var newCellState = '.'
        if (cells[i] == '*' && numberOfAliveSiblings == 2) {
            newCellState = '*'
        }
        return newCellState
    }

    private fun appendNewCellStateTo(evolution: String, newCellState: Char): String {
        return evolution + newCellState
    }
}
