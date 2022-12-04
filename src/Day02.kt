fun main() {
    fun shapeScore(shape: SHAPE) = when (shape) {
        SHAPE.ROCK -> 1
        SHAPE.PAPER -> 2
        SHAPE.SCISSORS -> 3
    }

    fun roundScore(opponentShape: SHAPE, yourShape: SHAPE) = when {
        (opponentShape == SHAPE.ROCK && yourShape == SHAPE.PAPER) ||
                (opponentShape == SHAPE.PAPER && yourShape == SHAPE.SCISSORS) ||
                (opponentShape == SHAPE.SCISSORS && yourShape == SHAPE.ROCK) -> 6

        opponentShape.ordinal == yourShape.ordinal -> 3
        else -> 0
    }

    fun part1(strategy: List<Pair<SHAPE, SHAPE>>) = strategy.sumOf { (opponentShape, yourShape) ->
        roundScore(opponentShape, yourShape) + shapeScore(yourShape)
    }

    fun part2(strategy: List<Pair<SHAPE, SHAPE>>) {

    }

    fun parseStrategy(input: List<String>): List<Pair<SHAPE, SHAPE>> {
        return input.map { line ->
            val opponentShape = when (val char = line[0]) {
                'A' -> SHAPE.ROCK
                'B' -> SHAPE.PAPER
                'C' -> SHAPE.SCISSORS
                else -> throw IllegalArgumentException("Unknown input '$char'")
            }
            val yourShape = when (val char = line[2]) {
                'X' -> SHAPE.ROCK
                'Y' -> SHAPE.PAPER
                'Z' -> SHAPE.SCISSORS
                else -> throw IllegalArgumentException("Unknown input '$char'")
            }

            opponentShape to yourShape
        }
    }

    val testInput = readInput("Day02_test")
    val testStrategy = parseStrategy(testInput)
    println(part1(testStrategy))

    val input = readInput("Day02")
    val strategy = parseStrategy(input)
    println(part1(strategy))
}

enum class SHAPE {
    ROCK,
    PAPER,
    SCISSORS
}