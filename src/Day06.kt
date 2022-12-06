fun main() {

    fun findFirstXUnique(buffer: String, x: Int): Int = buffer
        .windowed(x)
        .mapIndexed { i, chars -> if (chars.toSet().size == x) i + x else -1 }
        .first { it != -1 }

    fun part1(buffer: String): Int = findFirstXUnique(buffer, 4)

    fun part2(buffer: String): Int = findFirstXUnique(buffer, 14)

    val testInput = readInput("Day06_test")
    println(testInput.map(::part1))
    println(testInput.map(::part2))

    val input = readInput("Day06")[0]
    println(part1(input))
    println(part2(input))
}