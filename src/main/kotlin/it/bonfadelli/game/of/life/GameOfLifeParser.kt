package it.bonfadelli.game.of.life

class GameOfLifeParser {
    fun parse(cellsStr: String): Array<Cell> {
        val cells: Array<Cell?> = arrayOfNulls(cellsStr.count())
        for (cellIndex in 0..cellsStr.count() - 1) {
            cells[cellIndex] = parseSingleCell(cellsStr[cellIndex])
        }
        return cells.requireNoNulls()
    }

    private fun parseSingleCell(cellState: Char): Cell {
        val cell: Cell
        if (cellState == '*') {
            cell = Cell(Cell.State.ALIVE)
        } else {
            cell = Cell(Cell.State.DEAD)
        }
        return cell
    }
}