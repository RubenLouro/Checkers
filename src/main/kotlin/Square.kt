fun String.toSquareOrNull():Square? = if (this.length > 2 || !this[0].isDigit()|| this[0].digitToInt() > BOARD_DIM
    || this[1] !in 'a' until 'a' + BOARD_DIM) null
else Square.invoke(Row(BOARD_DIM - this[0].digitToInt()), Column(this[1]) )

data class Square private constructor(val row:Row,val column:Column) {
    val black:Boolean = ((row.number % 2 == 0 && column.index % 2 != 0) || (row.number % 2 == 1 && column.index % 2 == 0))

    companion object   {
        val values = List(BOARD_DIM * BOARD_DIM){
            Square(Row((it / BOARD_DIM)), Column('a' + (it % BOARD_DIM)))
        }
        operator fun invoke(row: Row, column: Column):Square{
            return values[(row.index * BOARD_DIM) + column.index]
        }
        operator fun invoke(i:Int, j:Int):Square{
            return values[(i * BOARD_DIM) + j]
        }
    }


    override fun toString() = "${row.number}${column.symbol}"
}