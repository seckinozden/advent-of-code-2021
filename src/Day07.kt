import java.lang.Integer.MAX_VALUE
import kotlin.math.abs

fun main() {
    val inputText = readInput("Day07_input")
    val inputNums = inputText[0].split(",").map { it.toInt() }

    day07Part1(inputNums)
    day07Part2(inputNums)
}

// time O(n^2)
// space O(1)
fun day07Part2(input: List<Int>) {
    var lowestCost = MAX_VALUE

    for (i in input.indices) {
        var cost = 0
        input.sorted().forEach {
            cost += abs(i - it).calculateCost()
        }
        if (cost < lowestCost) {
            lowestCost = cost
        }
    }

    println("--- Part 2 ---")
    println("Result = $lowestCost")
}

private fun Int.calculateCost() = (this * (this + 1)) / 2

// time O(n)
// space O(n)
fun day07Part1(input: List<Int>) {
    var minDiff = MAX_VALUE
    var minKey = MAX_VALUE
    val inputMap = mutableMapOf<Int, Int>()
    val leftCount = mutableMapOf<Int, Int>()
    val rightCount = mutableMapOf<Int, Int>()

    input.forEach { num ->
        inputMap[num] = inputMap.getOrDefault(num, 0) + 1
    }

    var lCounter = 0
    inputMap.entries
        .sortedBy { it.key }
        .forEach { (key, value) ->
            if (key == 0) {
                leftCount[key] = 0
                rightCount[key] = input.size - value
                lCounter = value
            } else {
                leftCount[key] = lCounter
                rightCount[key] = input.size - lCounter - value
                lCounter += value
            }

            if (abs(rightCount[key]!! - leftCount[key]!!) <= minDiff) {
                minKey = key
                minDiff = abs(rightCount[key]!! - leftCount[key]!!)
            }
        }

    var totalFuel = 0
    inputMap.entries.forEach { (key, value) ->
        totalFuel += abs(minKey - key) * value
    }

    println("--- Part 1 ---")
    println("Result= $totalFuel")
}
