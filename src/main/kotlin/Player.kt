enum class Player(val symbol:Char) {
    White('w'), Black('b')
}

fun String.toPlayer():Player {
    require(this.length == 1) { "Symbol must be only 1 character"}
    return  Player.values().find { it.symbol == this.lowercase()[0]}
        ?: throw IllegalArgumentException("There is no such player for symbol $this")
}