fun Int.toRowOrNull():Row? = if (this !in 1 .. BOARD_DIM) null else Row(BOARD_DIM - this)

fun Int.indexToRow():Row = if (this !in 0 until BOARD_DIM) throw IndexOutOfBoundsException("Invalid Index")
else Row(this)


class Row(val index:Int) {

    val number = BOARD_DIM - index

    companion object{
        val values = List(BOARD_DIM){Row(it)}
    }

}