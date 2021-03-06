package it.bonfadelli.game.of.life

import it.bonfadelli.game.of.life.Cell.State.*


class GameOfLifeFormatter(private val alphabet: Map<Cell.State, Char>, private val lineFormatter: Char) {

    init {
        throwExceptionIfAlphabetDoesNotContain(DEAD, "The alphabet does not contain the symbol for the DEAD state")
        throwExceptionIfAlphabetDoesNotContain(ALIVE, "The alphabet does not contain the symbol for the ALIVE state")
    }

    private fun throwExceptionIfAlphabetDoesNotContain(key: Cell.State, s: String) {
        if (!alphabet.containsKey(key)) {
            throw InvalidAlphabetException(s)
        }
    }

    fun format(evolutionCells: List<List<Cell>>): String {
        var evolution: String = ""
        for (line: List<Cell> in evolutionCells) {
            for (cell: Cell in line) {
                evolution = evolution.plus(getSymbol(cell))
            }
            evolution = evolution.plus(lineFormatter)
        }
        return evolution.trim()
    }

    private fun getSymbol(cell: Cell): Char {
        return alphabet[cell.state]!!
    }

    class InvalidAlphabetException(s: String) : Throwable(s)
}