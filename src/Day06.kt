fun main() {
    val inputText = readInput("Day06_input")
    val inputNums = inputText[0].split(",").map { it.toInt() }

    day06Part1(inputNums)
    day06Part2(inputNums)
}

fun day06Part2(input: List<Int>) {
    val fishMap = mutableMapOf<Int, Long>() // <age, count>
    input.forEach {
        fishMap[it] = fishMap.getOrDefault(it, 0).plus(1)
    }

    for (day in 1..256) {
        val mapToAdd = mutableMapOf<Int, Long>()
        val mapToRemove = mutableMapOf<Int, Long>()
        for (age in 0..8) {
            val fishCount = fishMap.getOrDefault(age, 0)
            if (age == 0) {
                mapToAdd[6] = mapToAdd.getOrDefault(6, 0).plus(fishCount)
                mapToAdd[8] = mapToAdd.getOrDefault(8, 0).plus(fishCount)
                mapToRemove[0] = mapToRemove.getOrDefault(0, 0).plus(fishCount)
            } else {
                mapToAdd[age - 1] = mapToAdd.getOrDefault(age - 1, 0).plus(fishCount)
                mapToRemove[age] = mapToRemove.getOrDefault(age, 0).plus(fishCount)
            }
        }
        for (i in 0..8) {
            fishMap[i] = fishMap.getOrDefault(i, 0)
                .plus(mapToAdd.getOrDefault(i, 0))
                .minus(mapToRemove.getOrDefault(i, 0))
        }
    }

    println("--- Part 2 ---")
    println("Result= ${fishMap.values.sum()}")
}

fun day06Part1(input: List<Int>) {

    val mutableList = mutableListOf<Int>()
    mutableList.addAll(input)

    for (i in 1..80) {
        val iterator = mutableList.listIterator()
        var newBorn = 0
        while (iterator.hasNext()) {
            val current = iterator.next()
            val next = if (current == 0) {
                newBorn++
                6
            } else {
                current - 1
            }
            iterator.set(next)
        }
        for (counter in 1..newBorn) {
            mutableList.add(8)
        }

        // println("Day $i - $mutableList")
    }

    println("--- Part 1 ---")
    println("Result = ${mutableList.size}")
}
