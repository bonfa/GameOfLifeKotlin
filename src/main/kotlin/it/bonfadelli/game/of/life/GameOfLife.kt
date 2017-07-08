package it.bonfadelli.game.of.life

class GameOfLife(private val gameOfLifeStringParser: GameOfLifeStringParser,
                 private val gameOfLifeFormatter: GameOfLifeFormatter,
                 private val gameOfLifeRule: GameOfLifeRule) {

    fun evolve(cellsStr: String): String {
        return gameOfLifeFormatter.format(
                gameOfLifeRule.evolve(
                        gameOfLifeStringParser.parse(cellsStr)
                )
        )
    }
}

