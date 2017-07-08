package it.bonfadelli.game.of.life

import it.bonfadelli.game.of.life.Cell.State.ALIVE
import it.bonfadelli.game.of.life.Cell.State.DEAD

class GameOfLifeRule {

    fun evolve(matrix: List<List<Cell>>): List<List<Cell>> {
        val evolutionMatrix: MutableList<MutableList<Cell>> = ArrayList()
        for (y in 0..matrix.size - 1) {
            evolutionMatrix.add(ArrayList<Cell>())
            val row = matrix[y]
            val minY = maxOf(0, y - 1)
            val maxY = minOf(matrix.size - 1, y + 1)
            for (x in 0..row.size - 1) {
                val minX = maxOf(0, x - 1)
                val maxX = minOf(row.size - 1, x + 1)
                val evolvedCell = getEvolvedCell(matrix, x, y, minX, minY, maxX, maxY)
                evolutionMatrix[y].add(evolvedCell)
            }
        }
        return evolutionMatrix
    }

    private fun getEvolvedCell(startingCells: List<List<Cell>>, xCell: Int, yCell: Int, minX: Int, minY: Int, maxX: Int, maxY: Int): Cell {
        val numberOfAliveSiblings = getNumberOfAliveSiblings(startingCells, xCell, yCell, minX, minY, maxX, maxY)
        return getEvolvedCell(numberOfAliveSiblings, startingCells[yCell][xCell])
    }

    private fun getNumberOfAliveSiblings(original: List<List<Cell>>, xCell: Int, yCell: Int, minX: Int, minY: Int, maxX: Int, maxY: Int): Int {
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
        return numberOfAliveSiblings
    }

    private fun getEvolvedCell(numberOfAliveSiblings: Int, oldCell: Cell): Cell {
        var newCell = Cell(DEAD)
        if (oldCell.isAlive() && numberOfAliveSiblings == 2) {
            newCell = Cell(ALIVE)
        }
        return newCell
    }

}