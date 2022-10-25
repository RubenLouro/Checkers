const val BOARD_DIM:Int = 5


class Board(
    val lastPlayer: Player = Player.Black,
   val moves : List<Move> = emptyList(),
    val winner:Player? = null )
{



}