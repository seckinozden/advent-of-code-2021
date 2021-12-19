fun main() {
    val inputText = readInput("Day09_input")
    val input = parse(inputText)

    day09Part1(input)
    day09Part2(input)
}

fun day09Part2(input: MutableList<MutableList<Int>>) {
    val basinList = mutableListOf<Int>()
    for (row in input.indices) {
        for (col in input[0].indices) {
            val current = input[row][col]
            var isLowest = true
            // check up
            if (row > 0) {
                if (current >= input[row - 1][col]) isLowest = false
            }
            // check down
            if (row < input.size - 1) {
                if (current >= input[row + 1][col]) isLowest = false
            }
            // check left
            if (col > 0) {
                if (current >= input[row][col - 1]) isLowest = false
            }
            // check right
            if (col < input[0].size - 1) {
                if (current >= input[row][col + 1]) isLowest = false
            }
            if (isLowest) {
                val basinVal = calculateBasin(input, row, col)
//                println("value: $current - row: $row - col: $col - basin: $basinVal")
                basinList.add(basinVal)
            }
        }
    }
    val result = basinList.sortedDescending().take(3).reduce { acc, i -> acc * i }
    println("--- Part 2 ---")
    println("Result= $result")
}

fun calculateBasin(input: MutableList<MutableList<Int>>, row: Int, col: Int): Int {
    var basinValue = 1
    input[row][col] = -1

    // add up
    if (row > 0) {
        if (input[row - 1][col] !in listOf(-1, 9)) {
            basinValue += calculateBasin(input, row - 1, col)
        }
    }
    // add down
    if (row < input.size - 1) {
        if (input[row + 1][col] !in listOf(-1, 9)) {
            basinValue += calculateBasin(input, row + 1, col)
        }
    }
    // add left
    if (col > 0) {
        if (input[row][col - 1] !in listOf(-1, 9)) {
            basinValue += calculateBasin(input, row, col - 1)
        }
    }
    // add right
    if (col < input[0].size - 1) {
        if (input[row][col + 1] !in listOf(-1, 9)) {
            basinValue += calculateBasin(input, row, col + 1)
        }
    }

    return basinValue
}

fun day09Part1(input: List<List<Int>>) {
    var result = 0
    for (row in input.indices) {
        for (col in input[0].indices) {
            val current = input[row][col]
            var isLowest = true
            // check up
            if (row > 0) {
                if (current >= input[row - 1][col]) isLowest = false
            }
            // check down
            if (row < input.size - 1) {
                if (current >= input[row + 1][col]) isLowest = false
            }
            // check left
            if (col > 0) {
                if (current >= input[row][col - 1]) isLowest = false
            }
            // check right
            if (col < input[0].size - 1) {
                if (current >= input[row][col + 1]) isLowest = false
            }
            if (isLowest) {
//                println("value: $current - row: $row - col: $col")
                result += current + 1
            }
        }
    }

    println("--- Part 1 ---")
    println("Result= $result")
}

fun parse(input: List<String>): MutableList<MutableList<Int>> {
    val parsed = mutableListOf<MutableList<Int>>()
    input.forEach { line ->
        val numbers = mutableListOf<Int>()
        line.toCharArray().forEach { numbers.add(it.digitToInt()) }
        parsed.add(numbers)
    }
    return parsed
}
