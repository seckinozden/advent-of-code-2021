fun main() {
    val input = readInput("Day04_input")

    val inputNums = parseInputNumbers(input[0])
    val boards = buildBoard(input)

    day04Part1(inputNums, boards)
    day04Part2(inputNums, boards)
}

fun day04Part2(inputNums: List<Int>, boards: List<Board>) {
    val winnerBoards = mutableListOf<Board>()
    var lastWinnerNum = 0
    inputNums.forEach { num ->
        boards.forEach {
            if (!it.isWon) {
                it.mark(num)
                if (it.checkWin()) {
                    winnerBoards.add(it)
                    it.isWon = true
                    lastWinnerNum = num
                }
            }
        }
    }
    val lastWinner = winnerBoards[winnerBoards.size - 1]
    var sum = 0
    lastWinner.gridMatrix.forEach { row -> row.forEach { grid -> if (!grid.isMarked) sum += grid.value } }
    println("--- Part 2 ---")
    println("This is the last winner board!")
    lastWinner.printBoard()
    println("Sum= $sum - LastWinnerNum= $lastWinnerNum")
    print("Result = ${sum * lastWinnerNum}")
}

fun day04Part1(inputNums: List<Int>, boards: List<Board>) {
    inputNums.forEach { num ->
        boards.forEach {
            it.mark(num)
            if (it.checkWin()) {
                var sum = 0
                it.gridMatrix.forEach { row -> row.forEach { grid -> if (!grid.isMarked) sum += grid.value } }
                println("--- Part 1 ---")
                println("This is the first winner board!")
                it.printBoard()
                println("Result = ${sum * num}")
                return
            }
        }
    }
}

data class Grid(val value: Int, var isMarked: Boolean = false)

data class Board(val gridMatrix: List<List<Grid>>, var isWon: Boolean = false) {

    fun checkWin(): Boolean {
        // check horizontal
        gridMatrix.forEach { grids ->
            var isHorizontalWin = true
            grids.forEach {
                if (!it.isMarked) isHorizontalWin = false
            }
            if (isHorizontalWin) return true
        }

        // check vertical
        for (col in gridMatrix[0].indices) {
            var isVerticalWin = true
            for (row in 0 until gridMatrix.size) {
                if (!gridMatrix[row][col].isMarked) isVerticalWin = false
            }
            if (isVerticalWin) return true
        }

        return false
    }

    fun mark(num: Int) {
        gridMatrix.forEach { grids ->
            grids.forEach { grid ->
                if (grid.value == num) grid.isMarked = true
            }
        }
    }

    fun printBoard() {
        gridMatrix.forEach {
            println(it)
        }
    }
}

fun parseInputNumbers(inputLine: String) = inputLine
    .split(",")
    .filter { it.isNotEmpty() }
    .map { it.toInt() }

fun buildBoard(input: List<String>): List<Board> {
    val boards = mutableListOf<Board>()
    for (i in 1 until input.size step 6) {
        val line1 = input[i + 1].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }.map { Grid(it) }
        val line2 = input[i + 2].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }.map { Grid(it) }
        val line3 = input[i + 3].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }.map { Grid(it) }
        val line4 = input[i + 4].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }.map { Grid(it) }
        val line5 = input[i + 5].split(" ").filter { it.isNotEmpty() }.map { it.trim().toInt() }.map { Grid(it) }

        boards.add(Board(listOf(line1, line2, line3, line4, line5)))
    }
    return boards
}
