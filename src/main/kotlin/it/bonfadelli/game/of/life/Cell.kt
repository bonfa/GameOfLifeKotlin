package it.bonfadelli.game.of.life

data class Cell(val state: State) {
    enum class State { ALIVE, DEAD }

    fun isAlive(): Boolean {
        return state == State.ALIVE
    }
}