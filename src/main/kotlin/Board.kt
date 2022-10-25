const val BOARD_DIM:Int = 5


sealed class Board(val moves : List<Move> = emptyList()) {

    companion object{
}
    abstract fun play(player: Player, square: Square):Board
}


class BoardDraw(moves: List<Move>) : Board(moves) {
    override fun play(player: Player, square: Square): Board {
        throw IllegalStateException("This game has already ended on a draw")
    }
}

class BoardWinner(moves:List<Move>, val winner: Player) : Board(moves){
    override fun play(player: Player, square: Square): Board {
        throw IllegalStateException("The player $winner has already won this match!")
    }
}

class BoardRun(moves: List<Move> = emptyList(), val lastPlayer: Player = Player.Black) : Board(moves) {
    override fun play(player: Player, square: Square): Board {
        require(lastPlayer != player) { "Player $player cannot play twice!" }
        require(!moves.any { m -> m.square == square }) { "Position $square occupied! Please play on an empty position." }
        val m = Move(player, square)
        return when {
            checkWinner(m) -> BoardWinner(moves + m, player)
            // moves.size == MAX_MOVES - 1 -> BoardDraw(moves + m)
            else -> BoardRun(moves + m, player)
        }
    }
    private fun checkWinner(m: Move): Boolean{
        TODO()
    }
}
