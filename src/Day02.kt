fun main() {
    fun shapeScore(shape: SHAPE) = when (shape) {
        SHAPE.ROCK -> 1
        SHAPE.PAPER -> 2
        SHAPE.SCISSORS -> 3
    }

    fun beatingShape(shape: SHAPE) = when (shape) {
        SHAPE.ROCK -> SHAPE.PAPER
        SHAPE.PAPER -> SHAPE.SCISSORS
        SHAPE.SCISSORS -> SHAPE.ROCK
    }

    fun losingShape(shape: SHAPE) = when (shape) {
        SHAPE.ROCK -> SHAPE.SCISSORS
        SHAPE.PAPER -> SHAPE.ROCK
        SHAPE.SCISSORS -> SHAPE.PAPER
    }

    fun roundScore(opponentShape: SHAPE, yourShape: SHAPE) = when {
        yourShape == beatingShape(opponentShape) -> 6
        opponentShape.ordinal == yourShape.ordinal -> 3
        else -> 0
    }

    fun part1(input: List<String>): Int {
        val strategy = input.map { line ->
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
        return strategy.sumOf { (opponentShape, yourShape) ->
            roundScore(opponentShape, yourShape) + shapeScore(yourShape)
        }
    }

    fun part2(input: List<String>): Int {
        val strategy = input.map { line ->
            val opponentShape = when (val char = line[0]) {
                'A' -> SHAPE.ROCK
                'B' -> SHAPE.PAPER
                'C' -> SHAPE.SCISSORS
                else -> throw IllegalArgumentException("Unknown input '$char'")
            }
            val outcome = when (val char = line[2]) {
                'X' -> OUTCOME.LOSE
                'Y' -> OUTCOME.DRAW
                'Z' -> OUTCOME.WIN
                else -> throw IllegalArgumentException("Unknown input '$char'")
            }

            opponentShape to outcome
        }

        return strategy.sumOf { (opponentShape, outcome) ->
            val yourShape = when (outcome) {
                OUTCOME.LOSE -> losingShape(opponentShape)
                OUTCOME.DRAW -> opponentShape
                OUTCOME.WIN -> beatingShape(opponentShape)
            }
            roundScore(opponentShape, yourShape) + shapeScore(yourShape)
        }
    }


    val testInput = readInput("Day02_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class SHAPE {
    ROCK,
    PAPER,
    SCISSORS
}

enum class OUTCOME {
    LOSE,
    DRAW,
    WIN
}