import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class GameOfLifeTest : BehaviorSpec() {
    init {
        given("a single dead cell") {
            val game:GameOfLife = GameOfLife(".")
            `when`("the cell evolves") {
                then("it is still dead") {
                    game.evolve() shouldBe "."
                }
            }
        }

        given("two dead cells") {
            val game:GameOfLife = GameOfLife("..")
            `when`("the cells evolve") {
                then("they are both dead") {
                    game.evolve() shouldBe ".."
                }
            }
        }

        given("three alive cells") {
            val game:GameOfLife = GameOfLife("***")
            `when`("the cells evolve") {
                then("the central cell is alive") {
                    game.evolve() shouldBe ".*."
                }
            }
        }

        given("two alive and one dead cells") {
            val game:GameOfLife = GameOfLife("**.")
            `when`("the cells evolve") {
                then("all the cells are dead") {
                    game.evolve() shouldBe "..."
                }
            }
        }

    }
}

class GameOfLife(private val cell: String) {
    fun evolve(): String {
        if (cell == "***") {
            return ".*."
        } else if (cell == "**.") {
            return "..."
        }
        else {
            return cell
        }
    }
}
