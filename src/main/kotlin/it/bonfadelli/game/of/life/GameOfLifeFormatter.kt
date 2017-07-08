package it.bonfadelli.game.of.life

import it.bonfadelli.game.of.life.Cell.State.*

class GameOfLifeFormatter(private val alphabet: Map<Cell.State, Char>) {

    init {
        throwExceptionIfAlphabetDoesNotContain(DEAD, "The alphabet does not contain the symbol for the DEAD state")
        throwExceptionIfAlphabetDoesNotContain(ALIVE, "The alphabet does not contain the symbol for the ALIVE state")
    }

    private fun throwExceptionIfAlphabetDoesNotContain(key: Cell.State, s: String) {
        if (!alphabet.containsKey(key)) {
            throw InvalidAlphabetException(s)
        }
    }

    fun format(evolutionCells: Array<Cell>): String {
        var evolution: String = ""
        for (cell: Cell in evolutionCells) {
            evolution = evolution.plus(getSymbol(cell))
        }
        return evolution
    }

    private fun getSymbol(cell: Cell): Char {
        return alphabet[cell.state]!!
    }

    class InvalidAlphabetException(s: String) : Throwable(s)
}