

fun Char.toColumnOrNull():Column? = if (this - 'a' > BOARD_DIM - 1
    || BOARD_DIM > 'z' - 'a'||  this !in ('a' .. 'z')) null
else Column.invoke(this)

fun Int.indexToColumn():Column = if (this !in 0 until BOARD_DIM) throw IndexOutOfBoundsException("No such column")
else Column.invoke('a' + this)


data class Column private constructor(private val col: Char) {

    val symbol = col

    val index = col - 'a'

    companion object{
        val values  = List(BOARD_DIM){Column('a' + it)}
        operator fun invoke(c:Char) : Column{
            require(c in 'a' .. 'z' ) {"Illegal Column must be between 'a' and 'z'"}
            return values[c - 'a']
        }
    }


}