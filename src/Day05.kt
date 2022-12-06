fun main() {
    fun part1(input: Input): String = input
        .instructions
        .fold(input.stacks) { stacks, (move, from, to) ->
            stacks[to - 1] = stacks[to - 1].plus(stacks[from - 1].takeLast(move).reversed())
            stacks[from - 1] = stacks[from - 1].dropLast(move)
            stacks
        }
        .joinToString("") { it.last().toString() }

    fun part2(input: Input) = input
        .instructions
        .fold(input.stacks) { stacks, (move, from, to) ->
            stacks[to - 1] = stacks[to - 1].plus(stacks[from - 1].takeLast(move))
            stacks[from - 1] = stacks[from - 1].dropLast(move)
            stacks
        }
        .joinToString("") { it.lastOrNull()?.toString()?:"" }

    fun parseInput(rawInput: List<String>): Input {
        fun parseStacks(rawStacks: List<String>): MutableList<String> {
            return rawStacks
                .map { line -> line.drop(1).windowed(1, 4) }
                .reversed()
                .reduce { acc, list ->
                    val paddedList = list.plus(List(acc.size - list.size) { " " })
                    acc.zip(paddedList) { a, b -> a.plus(b) }
                }
                .map { it.drop(1) }
                .map { it.filter { item -> item != ' ' } }
                .toMutableList()
        }

        fun parseInstructions(rawInstructions: List<String>): List<Triple<Int, Int, Int>> {
            return rawInstructions
                .map { it.split(" ") }
                .map { Triple(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
        }

        return Input(
            parseStacks(rawInput.takeWhile { it != "" }),
            parseInstructions(rawInput.dropWhile { it != "" }.drop(1))
        )
    }

    val testInput = readInput("Day05_test")
    println(part1(parseInput(testInput)))
    println(part2(parseInput(testInput)))

    val input = readInput("Day05")
    println(part1(parseInput(input)))
    println(part2(parseInput(input)))
}

data class Input(val stacks: MutableList<String>, val instructions: List<Triple<Int, Int, Int>>)