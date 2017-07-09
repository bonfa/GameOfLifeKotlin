package it.bonfadelli.game.of.life

class GameOfLifeStringParser(private val alphabet: HashMap<Char, Cell.State>) {

    fun parse(cellsStr: String): List<List<Cell>> {
        val cells: MutableList<MutableList<Cell>> = mutableListOf()

        var line = mutableListOf<Cell>()
        for (cellIndex in 0..cellsStr.count() - 1) {
            if (cellsStr[cellIndex] != '\n') {
                line.add(parseSingleCell(cellsStr[cellIndex]))
            }
            else {
                cells.add(line)
                line = mutableListOf()
            }
        }
        cells.add(line)
        return cells.requireNoNulls()
    }

    private fun parseSingleCell(cellState: Char): Cell {
        if (!alphabet.containsKey(cellState)) {
            throw ParseException("Unknown symbol " + cellState)
        }

        return Cell(alphabet[cellState]!!)
    }

    class ParseException(message: String?) : Throwable(message)
}