fun main() {

    fun itemValue(item: Char): Int = when {
        item >= 'a' -> item.code - 96
        else -> item.code - 38
    }

    fun part1(input: List<String>) = input
        .map { line -> line.take(line.length / 2) to line.drop(line.length / 2) }
        .flatMap { (compartment1, compartment2) -> compartment1.toSet().intersect(compartment2.toSet()) }
        .map(::itemValue)
        .sum()


    fun part2() {

    }

    val testInput = readInput("Day03_test")
    println(part1(testInput))

    val input = readInput("Day03")
    println(part1(input))
}