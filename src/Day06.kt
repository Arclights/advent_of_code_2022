fun main() {
    fun part1(buffer: String): Int = buffer
        .windowed(4)
        .mapIndexed { i, chars ->
            if (chars.toSet().size == 4) {
                i + 4
            } else {
                -1
            }
        }
        .first { it != -1 }

    fun part2() {

    }

    val testInput = readInput("Day06_test")
    println(testInput.map(::part1))

    val input = readInput("Day06")[0]
    println(part1(input))
}