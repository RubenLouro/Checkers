const val BOARD_DIM:Int = 8
const val TOTAL_PIECES = 24

private fun initialList():List<Move> {
    val blackPositions = Square.values.filter { it.black && it.row.number in (BOARD_DIM - 2..BOARD_DIM) }
    val whitePositions = Square.values.filter { it.black && it.row.number in (BOARD_DIM - BOARD_DIM..BOARD_DIM - 5) }
    val total = blackPositions + whitePositions
    val list = List(TOTAL_PIECES){ move->
                if (move < TOTAL_PIECES /2) Move(Player.Black, total[move]) else Move(Player.White, total[move])
        }
    return list
    }



sealed class Board(
    val moves : List<Move> = emptyList()
) {

    companion object{
        fun deserialize(input: String): Board {
            val lines = input.split("\n")
            val kind = lines[0]
            val moves = lines.drop(1).map { Move.deserialize(it) }
            return when (kind) {
                BoardRun::class.simpleName -> BoardRun(moves, moves.last().player)
                BoardDraw::class.simpleName -> BoardDraw(moves)
                BoardWinner::class.simpleName -> BoardWinner(moves, moves.last().player)
                else -> { throw IllegalArgumentException("There is no board type for input $kind")}
            }
        }
}
    abstract fun play(player: Player, square: Square):Board
    fun get(square: Square): Move? {
        return moves.find { it.square == square }
    }
}


class BoardDraw(moves: List<Move>) : Board(moves) {
    override fun play(player: Player, square: Square): Board {
        throw IllegalStateException("This game has already ended on a draw!")
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
