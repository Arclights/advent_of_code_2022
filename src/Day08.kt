fun main() {
    fun <T> List<List<T>>.transpose(): List<List<T>> = this
        .drop(1)
        .fold(this.first().map { listOf(it) }) { transposed, row ->
            transposed.zip(row) { l, r -> l.plus(r) }
        }

    fun <T> List<List<T>>.asMatrixString() = this.joinToString("\n") { row -> row.joinToString("") }

    fun findVisible(trees: List<Tree>, comparer: (Int, Int) -> Boolean) = trees
        .fold(listOf<Tree>()) { previousTrees, tree ->
            if (previousTrees.isEmpty() || comparer(tree.height, previousTrees.last().height)) {
                previousTrees.plus(tree)
            } else {
                previousTrees
            }
        }

    fun findVisibleFromEdge(trees: List<Tree>) = findVisible(trees) { a, b -> a > b }

    fun findVisibleFromTree(trees: List<Tree>, currentTreeHeight: Int) =
        findVisible(trees) { _, b -> b < currentTreeHeight }

    fun findVisible(input: List<List<Tree>>): Set<Tree> {
        return input.indices
            .flatMap { row ->
                listOf(
                    findVisibleFromEdge(input[row]),
                    findVisibleFromEdge(input[row].reversed())
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

    fun scenicScore(input: List<List<Tree>>, row: Int, column: Int): Int {
        val transposed = input.transpose()
        val currentTreeHeight = input[row][column].height
        return listOf(
            input[row].drop(column + 1),
            input[row].reversed().drop(input[row].size - column),
            transposed[column].drop(row + 1),
            transposed[column].reversed().drop(transposed[column].size - row)
        )
            .map { findVisibleFromTree(it, currentTreeHeight) }
            .map { it.size }
            .reduce(Int::times)
    }

    fun part2(input: List<List<Tree>>): Int {
        return input.indices
            .flatMap { row ->
                input.first().indices.map { column -> scenicScore(input, row, column) }
            }
            .max()
    }

    fun parseInput(input: List<String>) = input.mapIndexed { row, line ->
        line.mapIndexed { column, treeHeight ->
            Tree(treeHeight.digitToInt(), row, column)
        }
    }

    val testInput = readInput("Day08_test")
    val parsedTestInput = parseInput(testInput)
    println(part1(parsedTestInput))
    println(part2(parsedTestInput))

    println()

    val input = readInput("Day08")
    val parsedInput = parseInput(input)
    println(part1(parsedInput))
    println(part2(parsedInput))
}

data class Tree(val height: Int, val row: Int, val column: Int) {
    override fun toString(): String {
        return "($height, $row, $column)"
    }
}