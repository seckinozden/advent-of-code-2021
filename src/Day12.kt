var day12Part1count = 0
var day12Part2count = 0

fun main() {
    val input = readInput("Day12_input")

    day12Part1(input)
    day12Part2(input)
}

fun day12Part2(input: List<String>) {
    val map = buildDay12Graph(input)
    traversePart2("start", map, listOf("start"))
    println("--- Part 2 ---")
    println("Result $day12Part2count")
}

private fun traversePart2(name: String, dataMap: Map<String, List<String>>, prevList: List<String>) {
    val list = dataMap[name]
    list!!.forEach { str ->
        if (str == "end") {
            day12Part2count++
            println("Count: $day12Part2count - list: $prevList")
        } else if (str != "start") {
            if (str != str.uppercase() && prevList.contains(str) && prevList.containsDuplicateSmallCave()) {
                return@forEach
            } else {
                val newList = prevList.toMutableList().also { it.add(str) }
                traversePart2(str, dataMap, newList)
            }
        }
    }
}

fun List<String>.containsDuplicateSmallCave() = this
    .filter { it != it.uppercase() }
    .run { this.size != this.distinct().size }

fun day12Part1(input: List<String>) {
    val map = buildDay12Graph(input)
    traversePart1("start", map, emptyList())
    println("--- Part 1 ---")
    println("Result $day12Part1count")
}

private fun traversePart1(name: String, dataMap: Map<String, List<String>>, prevList: List<String>) {
    val list = dataMap[name]
    list!!.forEach { str ->
        if (str == "end") {
            day12Part1count++
        } else if (str != "start") {
            if (str != str.uppercase() && prevList.contains(str)) {
                return@forEach
            }
            val newList = prevList.toMutableList().also { it.add(str) }
            traversePart1(str, dataMap, newList)
        }
    }
}

private fun buildDay12Graph(input: List<String>): Map<String, List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    input.forEach {
        val (first, second) = it.split("-")

        val firstList = map.getOrDefault(first, mutableListOf())
        firstList.add(second)
        map[first] = firstList

        val secondList = map.getOrDefault(second, mutableListOf())
        secondList.add(first)
        map[second] = secondList
    }
    return map
}
