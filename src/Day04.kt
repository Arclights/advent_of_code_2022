fun main() {
    fun parseInput(input: List<String>) = input
        .map { line ->
            line.split(",")
                .map { elf ->
                    elf.split("-")
                        .let { ids -> ids[0].toInt()..ids[1].toInt() }
                        .toSet()
                }
                .let { it[0] to it[1] }
        }

    fun part1(input: List<String>) = parseInput(input)
        .count { elves ->
            val combined = elves.first.intersect(elves.second)
            combined.size == elves.first.size || combined.size == elves.second.size
        }

    fun part2(input: List<String>) = parseInput(input)
        .count { elves -> elves.first.intersect(elves.second).isNotEmpty() }

    val testInput = readInput("Day04_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}