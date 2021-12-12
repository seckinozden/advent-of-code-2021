fun main() {
    val inputText = readInput("Day06_input")
    val inputNums = inputText[0].split(",").map { it.toInt() }

    day06Part1(inputNums)
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
