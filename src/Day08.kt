fun main() {
    fun <T> List<List<T>>.transpose(): List<List<T>> = this
        .drop(1)
        .fold(this.first().map { listOf(it) }) { transposed, row ->
            transposed.zip(row) { l, r -> l.plus(r) }
        }

    fun <T> List<List<T>>.asMatrixString() = this.joinToString("\n") { row -> row.joinToString("") }

    fun findVisible(trees: List<Tree>) = trees
        .fold(listOf<Tree>()) { previousTrees, tree ->
            if (previousTrees.isEmpty() || tree.height > previousTrees.last().height) {
                previousTrees.plus(tree)
            } else {
                previousTrees
            }
        }

    fun findVisible(input: List<List<Tree>>): Set<Tree> {
        return input.indices
            .flatMap { row ->
                listOf(
                    findVisible(input[row]),
                    findVisible(input[row].reversed())
                ).flatten()
            }
            .toSet()
    }

    fun part1(input: List<List<Tree>>): Int = listOf(
        findVisible(input),
        findVisible(input.transpose())
    )
        .flatten()
        .toSet()
        .size

    fun parseInput(input: List<String>) = input.mapIndexed { row, line ->
        line.mapIndexed { column, treeHeight ->
            Tree(treeHeight.digitToInt(), row, column)
        }
    }

    val testInput = readInput("Day08_test")
    val parsedTestInput = parseInput(testInput)
    println(part1(parsedTestInput))

    println()

    val input = readInput("Day08")
    val parsedInput = parseInput(input)
    println(part1(parsedInput))
}

data class Tree(val height: Int, val row: Int, val column: Int)