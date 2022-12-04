fun main() {
    fun part1(input: List<String>): Int {
        return input
            .fold(mutableListOf<MutableList<Int>>(mutableListOf())) { list, line ->
                if ("" == line) {
                    list.add(mutableListOf())
                } else {
                    list.last().add(line.toInt())
                }
                list
            }
            .maxOf(MutableList<Int>::sum)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)
    println(part1(testInput))

    val input = readInput("Day01")
    println(part1(input))
//    println(part2(input))
}
