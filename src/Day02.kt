fun main() {
    val input = readInput("Day02_input")
    day02Part1(input)
    day02Part2(input)
}

fun day02Part1(input: List<String>) {
    var horizontalPos = 0
    var depth = 0

    input.forEach {
        val inputLine = it.split(" ")
        val command = inputLine[0]
        val amount = inputLine[1].toInt()

        when (command) {
            "forward" -> horizontalPos += amount
            "down" -> depth += amount
            "up" -> depth -= amount
        }
    }

    println("--- Part 1 ---")
    println("Horizontal Position: $horizontalPos - Depth: $depth")
    println("Result = ${horizontalPos * depth}")
}

fun day02Part2(input: List<String>) {
    var horizontalPos = 0
    var depth = 0
    var aim = 0

    input.forEach {
        val inputLine = it.split(" ")
        val command = inputLine[0]
        val amount = inputLine[1].toInt()

        when (command) {
            "forward" -> {
                horizontalPos += amount
                depth += aim * amount
            }
            "down" -> {
                aim += amount
            }
            "up" -> {
                aim -= amount
            }
        }
    }

    println("--- Part 2 ---")
    println("Horizontal Position: $horizontalPos - Depth: $depth")
    println("Result = ${horizontalPos * depth}")
}
