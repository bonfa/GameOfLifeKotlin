package it.bonfadelli.game.of.life

import it.bonfadelli.game.of.life.Cell.State.*


class GameOfLifeFormatter(private val alphabet: Map<Cell.State, Char>) {
    private val LINE_FORMATTER = "\n"

    init {
        throwExceptionIfAlphabetDoesNotContain(DEAD, "The alphabet does not contain the symbol for the DEAD state")
        throwExceptionIfAlphabetDoesNotContain(ALIVE, "The alphabet does not contain the symbol for the ALIVE state")
    }

    private fun throwExceptionIfAlphabetDoesNotContain(key: Cell.State, s: String) {
        if (!alphabet.containsKey(key)) {
            throw InvalidAlphabetException(s)
        }
    }

    fun format(evolutionCells: Array<Array<Cell>>): String {
        var evolution: String = ""
        for (line: Array<Cell> in evolutionCells) {
            for (cell: Cell in line) {
                evolution = evolution.plus(getSymbol(cell))
            }
            evolution = evolution.plus(LINE_FORMATTER)
        }
        return evolution.trim()
    }

    private fun getSymbol(cell: Cell): Char {
        return alphabet[cell.state]!!
    }

    class InvalidAlphabetException(s: String) : Throwable(s)
}