package it.bonfadelli.game.of.life

class GameOfLifeParser(private val alphabet: HashMap<Char, Cell.State>) {

    fun parse(cellsStr: String): Array<Cell> {
        val cells: Array<Cell?> = arrayOfNulls(cellsStr.count())
        for (cellIndex in 0..cellsStr.count() - 1) {
            cells[cellIndex] = parseSingleCell(cellsStr[cellIndex])
        }
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