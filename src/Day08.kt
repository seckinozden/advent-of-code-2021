fun main() {
    val inputText = readInput("Day08_input")

    day08Part1(inputText)
}

fun day08Part1(inputText: List<String>) {
    var counter = 0
    inputText.forEach {
        val parsed = it.parseDay08InputLine()
        parsed.second.forEach { digit ->
            if (digit.length in intArrayOf(2, 3, 4, 7)) counter++
        }
    }

    println("--- Part 1 ---")
    println("Result= $counter")
}

fun String.parseDay08InputLine(): Pair<List<String>, List<String>> {
    val rawStr = this.split("|").map { it.trim() }
    val uniquePatterns = rawStr[0].split(" ")
    val fourDigits = rawStr[1].split(" ")

    return Pair(uniquePatterns, fourDigits)
}
