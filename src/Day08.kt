fun main() {
    val inputText = readInput("Day08_input")

    day08Part1(inputText)
    day08Part2(inputText)
}

fun day08Part2(inputText: List<String>) {
    var total = 0
    inputText.forEach { line ->
        val parsed = line.parseDay08InputLine()
        val input = parsed.first
        val fourDigit = parsed.second

        val map = calculateCorrectMapping(input)
        val stringBuilder = StringBuilder()
        fourDigit.forEach {
            val numStr = map[it.sort()]
            stringBuilder.append(numStr)
        }
        val num = stringBuilder.toString().toInt()
        total += num
        println("Number = $num")
    }

    println("--- Part 2 ---")
    println("Result= $total")
}

fun calculateCorrectMapping(mixedData: List<String>): Map<String, String> {
    val correctMapping = mutableMapOf<String, String>()
    val fiveLenList = mutableListOf<String>()
    val sixLenList = mutableListOf<String>()

    // clarify certain ones
    mixedData.forEach { data ->
        when (data.length) {
            2 -> correctMapping["1"] = data.sort()
            3 -> correctMapping["7"] = data.sort()
            4 -> correctMapping["4"] = data.sort()
            7 -> correctMapping["8"] = data.sort()
            5 -> fiveLenList.add(data.sort())
            6 -> sixLenList.add(data.sort())
        }
    }

    fiveLenList.forEach { fiveLenWord ->
        if (fiveLenWord.containsList(correctMapping["7"]!!)) {
            correctMapping["3"] = fiveLenWord
        } else if (fiveLenWord.containsSet(
                correctMapping["4"]!!.toSet().minus(correctMapping["1"]!!.toList().toSet())
            )
        ) {
            correctMapping["5"] = fiveLenWord
        } else {
            correctMapping["2"] = fiveLenWord
        }
    }

    sixLenList.forEach { sixLenWord ->
        if (!sixLenWord.containsList(correctMapping["7"]!!)) {
            correctMapping["6"] = sixLenWord
        } else if (sixLenWord.containsList(correctMapping["4"]!!)) {
            correctMapping["9"] = sixLenWord
        } else {
            correctMapping["0"] = sixLenWord
        }
    }

    return correctMapping.entries.associate { (key, value) -> value to key }
}

fun String.containsList(str: String) = this.toCharArray().asList().containsAll(str.toCharArray().toSet())
fun String.containsSet(set: Set<Char>) = this.toCharArray().asList().containsAll(set)
fun String.sort() = this.toCharArray().sorted().joinToString("")

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
