const val DAYS = 100
var result = 0
fun main() {
    val input = parseDay11(readInput("Day11_input"))

    day11Part1(input)
}

fun day11Part1(input: List<List<Cell>>) {

    for (i in 1..DAYS) {
        resetGlowStatus(input)
        for (row in input.indices) {
            for (col in input[0].indices) {
                processCell(input, row, col)
            }
        }
    }

    println("--- Part 1 ---")
    println("Result= $result")
}

private fun processCell(input: List<List<Cell>>, row: Int, col: Int) {
    if (input[row][col].data == 9) {
        handleGlow(input, row, col)
    } else {
        input[row][col].increaseVal()
    }
}

private fun handleGlow(input: List<List<Cell>>, row: Int, col: Int) {
    val currentCell = input[row][col]
    if (!currentCell.isGlowed) {
        currentCell.apply {
            data = 0
            isGlowed = true
            result++
        }
    }

    if (row > 0 && col > 0 && !input[row - 1][col - 1].isGlowed) { // up left
        processCell(input, row - 1, col - 1)
    }
    if (row > 0 && !input[row - 1][col].isGlowed) { // up
        processCell(input, row - 1, col)
    }
    if (row > 0 && col < input[0].size - 1 && !input[row - 1][col + 1].isGlowed) { // up right
        processCell(input, row - 1, col + 1)
    }
    if (col < input[0].size - 1 && !input[row][col + 1].isGlowed) { // right
        processCell(input, row, col + 1)
    }
    if (row < input.size - 1 && col < input[0].size - 1 && !input[row + 1][col + 1].isGlowed) { // bottom right
        processCell(input, row + 1, col + 1)
    }
    if (row < input.size - 1 && !input[row + 1][col].isGlowed) { // down
        processCell(input, row + 1, col)
    }
    if (row < input.size - 1 && col > 0 && !input[row + 1][col - 1].isGlowed) { // bottom left
        processCell(input, row + 1, col - 1)
    }
    if (col > 0 && !input[row][col - 1].isGlowed) { // left
        processCell(input, row, col - 1)
    }
}

private fun resetGlowStatus(input: List<List<Cell>>) {
    for (row in input.indices) {
        for (col in input[0].indices) {
            input[row][col].isGlowed = false
        }
    }
}

fun parseDay11(input: List<String>): List<List<Cell>> {
    val list = mutableListOf<List<Cell>>()
    input.forEach { line ->
        val lineList = mutableListOf<Cell>()
        for (char in line.toCharArray()) {
            lineList.add(Cell(char.digitToInt()))
        }
        list.add(lineList)
    }

    return list
}

data class Cell(var data: Int, var isGlowed: Boolean = false) {
    fun increaseVal() {
        this.data = this.data + 1
    }
}