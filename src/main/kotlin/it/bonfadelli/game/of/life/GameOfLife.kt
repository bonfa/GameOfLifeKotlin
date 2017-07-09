package it.bonfadelli.game.of.life

class GameOfLife(private val gameOfLifeStringParser: GameOfLifeStringParser,
                 private val gameOfLifeFormatter: GameOfLifeFormatter,
                 private val gameOfLifeRule: GameOfLifeRule) {

    fun evolve(cellsStr: String): String {
        val elements = gameOfLifeStringParser.parse(cellsStr)
        val evolutionCells = gameOfLifeRule.evolve(elements)
        return gameOfLifeFormatter.format(evolutionCells)
    }
}

