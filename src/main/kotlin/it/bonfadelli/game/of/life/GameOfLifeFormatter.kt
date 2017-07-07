package it.bonfadelli.game.of.life

class GameOfLifeFormatter {
    fun format(evolutionCells: Array<Cell>):String {
        var evolution: String = ""
        for (cell: Cell in evolutionCells) {
            evolution = evolution.plus(if (cell.isAlive()) "*" else ".")
        }
        return evolution
    }
}