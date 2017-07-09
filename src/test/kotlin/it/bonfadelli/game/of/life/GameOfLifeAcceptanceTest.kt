package it.bonfadelli.game.of.life

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

class GameOfLifeAcceptanceTest : FunSpec() {
    private val gameOfLifeFormatter = GameOfLifeFormatter(hashMapOf(Cell.State.ALIVE to '*', Cell.State.DEAD to '.'), '\n')
    private val gameOfLifeStringParser = GameOfLifeStringParser(hashMapOf('*' to Cell.State.ALIVE, '.' to Cell.State.DEAD), '\n')

    init {
        test("A configuration of cells") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            val evolution = game.evolve("........\n....*...\n...**...\n........")
            evolution shouldBe "........\n...**...\n...**...\n........"
        }

        test("Another configuration of cells") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            val evolution = game.evolve(".....\n.***.\n.*...\n.....")
            evolution shouldBe "..*..\n.**..\n.*...\n....."
        }

        test("An invariant configuration") {
            val game: GameOfLife = GameOfLife(gameOfLifeStringParser, gameOfLifeFormatter, GameOfLifeRule())
            val evolution = game.evolve("....\n.**.\n.**.\n....")
            evolution shouldBe "....\n.**.\n.**.\n...."
        }
    }
}
