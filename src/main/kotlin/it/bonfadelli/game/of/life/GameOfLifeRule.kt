package it.bonfadelli.game.of.life

class GameOfLifeRule {

    fun evolve(matrix: List<List<Cell>>): List<List<Cell>> {
        val evolutionMatrix: MutableList<MutableList<Cell>> = ArrayList()
        for (y in 0..matrix.size - 1) {
            val row = matrix[y]
            val minY = maxOf(0, y - 1)
            val maxY = minOf(matrix.count() - 1, y + 1)
            evolutionMatrix.add(ArrayList<Cell>())
            for (x in 0..row.count() - 1) {
                val minX = maxOf(0, x - 1)
                val maxX = minOf(row.count() - 1, x + 1)
                appendEvolvedStatus(x, y, matrix, evolutionMatrix, minX, minY, maxX, maxY)
            }
        }
        return evolutionMatrix
    }

    private fun appendEvolvedStatus(xCell: Int, yCell: Int, original: List<List<Cell>>, evolved: MutableList<MutableList<Cell>>, minX: Int, minY: Int, maxX: Int, maxY: Int) {
        var numberOfAliveSiblings = 0
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                if (x != xCell || y != yCell) {
                    if (original[y][x].isAlive()) {
                        numberOfAliveSiblings++
                    }
                }
            }
        }

        val newCell = getNewCellState(numberOfAliveSiblings, original[yCell][xCell])
        evolved[yCell].add(newCell)
    }

    private fun getNewCellState(numberOfAliveSiblings: Int, cell: Cell): Cell {
        var newCell = Cell(Cell.State.DEAD)
        if (cell.isAlive() && numberOfAliveSiblings == 2) {
            newCell = Cell(Cell.State.ALIVE)
        }
        return newCell
    }

}