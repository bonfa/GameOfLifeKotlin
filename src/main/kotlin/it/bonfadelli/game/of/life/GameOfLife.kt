package it.bonfadelli.game.of.life

class GameOfLife(private val cellsStr: String) {

    fun evolve(): String {
        val cells: Array<Cell> = GameOfLifeStringParser(hashMapOf('*' to Cell.State.ALIVE, '.' to Cell.State.DEAD)).parse(cellsStr)
        var evolutionCells: Array<Cell> = emptyArray()
        for (cellIndex in 0..cellsStr.count() - 1) {
            evolutionCells = appendEvolvedStatus(cellIndex, cells, evolutionCells)
        }
        return GameOfLifeFormatter(hashMapOf(Cell.State.ALIVE to '*', Cell.State.DEAD to '.')).format(evolutionCells)
    }

    private fun appendEvolvedStatus(cellIndex: Int, startingCells: Array<Cell>, evolutionCells: Array<Cell>): Array<Cell> {
        val numberOfAliveSiblings = getNumberOfAliveSiblings(cellIndex, startingCells)
        val newCell = getNewCellState(cellIndex, numberOfAliveSiblings, startingCells)
        return appendNewCellStateTo(newCell, evolutionCells)
    }

    private fun getNumberOfAliveSiblings(i: Int, cells: Array<Cell>): Int {
        var numberOfAliveSiblings = 0

        val indexOfPreviousCell = i - 1
        if (indexOfPreviousCell >= 0 && cells[indexOfPreviousCell].isAlive()) {
            numberOfAliveSiblings++
        }

        val indexOfFollowingCell = i + 1
        if (indexOfFollowingCell < cells.size && cells[indexOfFollowingCell].isAlive()) {
            numberOfAliveSiblings++
        }

        return numberOfAliveSiblings
    }

    private fun getNewCellState(i: Int, numberOfAliveSiblings: Int, cells: Array<Cell>): Cell {
        var newCell = Cell(Cell.State.DEAD)
        if (cells[i].isAlive() && numberOfAliveSiblings == 2) {
            newCell = Cell(Cell.State.ALIVE)
        }
        return newCell
    }

    private fun appendNewCellStateTo(newCell: Cell, evolutionCells: Array<Cell>): Array<Cell> {
        return evolutionCells.plus(newCell)
    }
}

