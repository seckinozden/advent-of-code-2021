import kotlin.math.abs

fun main() {
    val input = parseInput(readInput("Day05_input"))

    day05Part1(input)
    day05Part2(input)
}

fun day05Part2(input: List<Pair<Point, Point>>) {
    val oceanFloor = mutableMapOf<Point, Int>()
    input.forEach { pair ->
        if (pair.first.x == pair.second.x) {
            val from = if (pair.first.y >= pair.second.y) pair.second.y else pair.first.y
            val to = if (pair.first.y >= pair.second.y) pair.first.y else pair.second.y
            for (i in from..to) {
                val key = Point(pair.first.x, i)
                val value = oceanFloor.getOrDefault(key, 0)
                oceanFloor[key] = value + 1
            }
        } else if (pair.first.y == pair.second.y) {
            val from = if (pair.first.x >= pair.second.x) pair.second.x else pair.first.x
            val to = if (pair.first.x >= pair.second.x) pair.first.x else pair.second.x
            for (i in from..to) {
                val key = Point(i, pair.first.y)
                val value = oceanFloor.getOrDefault(key, 0)
                oceanFloor[key] = value + 1
            }
        } else if (abs(pair.first.x - pair.second.x) == abs(pair.first.y - pair.second.y)) {
            val from = if (pair.first.x >= pair.second.x) pair.second else pair.first
            val to = if (from == pair.first) pair.second else pair.first
            for (i in 0..to.x - from.x) {
                val x = from.x + i
                val y = if (from.y > to.y) from.y - i else from.y + i
                val key = Point(x, y)
                val value = oceanFloor.getOrDefault(key, 0)
                oceanFloor[key] = value + 1
            }
        }
    }

    var dangerCounter = 0
    oceanFloor.values.forEach { if (it >= 2) dangerCounter++ }

    println("--- Part 2 ---")
    println("Result= $dangerCounter")
}

fun day05Part1(input: List<Pair<Point, Point>>) {
    val oceanFloor = mutableMapOf<Point, Int>()
    input.forEach { pair ->
        if (pair.first.x == pair.second.x) {
            val from = if (pair.first.y >= pair.second.y) pair.second.y else pair.first.y
            val to = if (pair.first.y >= pair.second.y) pair.first.y else pair.second.y
            for (i in from..to) {
                val key = Point(pair.first.x, i)
                val value = oceanFloor.getOrDefault(key, 0)
                oceanFloor[key] = value + 1
            }
        } else if (pair.first.y == pair.second.y) {
            val from = if (pair.first.x >= pair.second.x) pair.second.x else pair.first.x
            val to = if (pair.first.x >= pair.second.x) pair.first.x else pair.second.x
            for (i in from..to) {
                val key = Point(i, pair.first.y)
                val value = oceanFloor.getOrDefault(key, 0)
                oceanFloor[key] = value + 1
            }
        }
    }

    var dangerCounter = 0
    oceanFloor.values.forEach { if (it >= 2) dangerCounter++ }

    println("--- Part 1 ---")
    println("Result= $dangerCounter")
}

fun parseInput(input: List<String>): List<Pair<Point, Point>> {
    val parsedInput = mutableListOf<Pair<Point, Point>>()
    input.forEach { line ->
        val rawSourceAndTarget = line.split("->")
        val rawSource = rawSourceAndTarget[0].trim().split(",")
        val rawTarget = rawSourceAndTarget[1].trim().split(",")
        val source = Point(rawSource[0].toInt(), rawSource[1].toInt())
        val target = Point(rawTarget[0].toInt(), rawTarget[1].toInt())
        parsedInput.add(Pair(source, target))
    }
    return parsedInput
}

data class Point(val x: Int, val y: Int)
