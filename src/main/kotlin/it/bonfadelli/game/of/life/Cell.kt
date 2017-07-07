package it.bonfadelli.game.of.life

class Cell(private val state: State) {
    enum class State { ALIVE, DEAD }

    fun isAlive(): Boolean {
        return state == State.ALIVE
    }
}