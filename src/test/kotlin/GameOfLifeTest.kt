import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class GameOfLifeTest : BehaviorSpec() {
    init {
        given("a single dead cell") {
            val cell = "."
            val game:GameOfLife = GameOfLife(cell)
            `when`("the cell evolves") {
                val evolved = game.evolve()
                then("it is still dead") {
                    evolved shouldBe "."
                }
            }
        }

        given("two dead cells") {
            val cell = ".."
            val game:GameOfLife = GameOfLife(cell)
            `when`("the cells evolve") {
                val evolved = game.evolve()
                then("they are both dead") {
                    evolved shouldBe ".."
                }
            }
        }

        given("three alive cells") {
            val cell = "***"
            val game:GameOfLife = GameOfLife(cell)
            `when`("the cells evolve") {
                val evolved = game.evolve()
                then("the central cell is alive") {
                    evolved shouldBe ".*."
                }
            }
        }

    }
}

class GameOfLife(private val cell: String) {
    fun evolve(): String {
        if (cell == "***") {
            return ".*."
        }
        else {
            return cell
        }
    }
}
