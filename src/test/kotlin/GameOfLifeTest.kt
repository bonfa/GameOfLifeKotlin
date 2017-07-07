import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class GameOfLifeTest : BehaviorSpec() {
    init {
        given("a dead cell") {
            val cell = "."
            val game:GameOfLife = GameOfLife(cell)
            `when`("the cell evolves") {
                val evolved = game.evolve()
                then("it is still dead") {
                    evolved shouldBe "."
                }
            }
//            `when`("I throw it away") {
//                then("it should come back") {
//                    // test code
//                }
//            }
        }
    }
}

class GameOfLife(private val cell: String) {
    fun evolve(): String {
        return "."
    }

}
