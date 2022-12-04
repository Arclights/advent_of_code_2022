fun main() {
    fun getCalories(input: List<String>) = input
        .fold(mutableListOf<MutableList<Int>>(mutableListOf())) { list, line ->
            if ("" == line) {
                list.add(mutableListOf())
            } else {
                list.last().add(line.toInt())
            }
            list
        }
        .map(MutableList<Int>::sum)

    fun part1(input: List<String>) = getCalories(input).max()

    fun part2(input: List<String>): Int = getCalories(input)
        .sorted()
        .reversed()
        .take(3)
        .sum()

    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
