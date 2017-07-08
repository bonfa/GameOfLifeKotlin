package it.bonfadelli.game.of.life

class GameOfLife(private val gameOfLifeStringParser: GameOfLifeStringParser,
                 private val gameOfLifeFormatter: GameOfLifeFormatter,
                 private val gameOfLifeRule: GameOfLifeRule) {

    fun evolve(cellsStr: String): String {
        //TODO fix parser and formatter for matrix
        //val elements = gameOfLifeStringParser.parse(cellsStr)
        //val evolutionCells = gameOfLifeRule.evolve(listOf(elements))[0]
        //return gameOfLifeFormatter.format(evolutionCells)
        return ""
    }
}

