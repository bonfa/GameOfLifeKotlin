package it.bonfadelli.game.of.life

class GameOfLifeRule {

    fun evolve(matrix: List<List<Cell>>): List<List<Cell>> {
        val evolutionMatrix: MutableList<List<Cell>> = ArrayList(matrix.size)

        for (y in 0..matrix.count() - 1) {
            val row = matrix[y]
            var evolutionCells: List<Cell> = ArrayList(row.size)
            for (x in 0..row.count() - 1) {
                evolutionCells = appendEvolvedStatus(x, row, evolutionCells)
            }
            evolutionMatrix.add(evolutionCells)
        }
        return evolutionMatrix
    }

    private fun appendEvolvedStatus(cellIndex: Int, startingCells: List<Cell>, evolutionCells: List<Cell>): List<Cell> {
        val numberOfAliveSiblings = getNumberOfAliveSiblings(cellIndex, startingCells)
        val newCell = getNewCellState(cellIndex, numberOfAliveSiblings, startingCells)
        return appendNewCellStateTo(newCell, evolutionCells)
    }

    private fun getNumberOfAliveSiblings(i: Int, cells: List<Cell>): Int {
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

    private fun getNewCellState(i: Int, numberOfAliveSiblings: Int, cells: List<Cell>): Cell {
        var newCell = Cell(Cell.State.DEAD)
        if (cells[i].isAlive() && numberOfAliveSiblings == 2) {
            newCell = Cell(Cell.State.ALIVE)
        }
        return newCell
    }

    private fun appendNewCellStateTo(newCell: Cell, evolutionCells: List<Cell>): List<Cell> {
        val mutableList = evolutionCells.toMutableList()
        mutableList.add(newCell)
        return mutableList
    }

}