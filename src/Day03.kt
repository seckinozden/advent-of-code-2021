fun main() {
    val input = readInput("Day03_input")
    day03Part1(input)
    day03Part2(input)
}

fun day03Part1(input: List<String>) {
    val map = mutableMapOf<Int, Int>()
    input.forEach {
        it.toCharArray().mapIndexed { index, c ->
            var currentVal = map.getOrDefault(index, 0)

            when (c) {
                '0' -> currentVal--
                '1' -> currentVal++
                else -> println("Invalid input")
            }

            map[index] = currentVal
        }
    }

    val gammaBuilder = StringBuilder()
    map.values.forEach {
        if (it > 0) gammaBuilder.append("1")
        else gammaBuilder.append("0")
    }

    val epsilonBuilder = StringBuilder()
    gammaBuilder.toString().toCharArray().forEach {
        when (it) {
            '0' -> epsilonBuilder.append('1')
            '1' -> epsilonBuilder.append('0')
            else -> println("Invalid input")
        }
    }

    val gamma = Integer.parseInt(gammaBuilder.toString(), 2)
    val epsilon = Integer.parseInt(epsilonBuilder.toString(), 2)

    println("--- Part 1 ---")
    println("Gamma = $gamma - epsilon: $epsilon")
    println("Result = ${gamma * epsilon}")
}

fun day03Part2(input: List<String>) {
    val o2GenerationRatingStr = o2GeneratorRating(input)
    val co2ScrubberRatingStr = co2ScrubberRating(input)

    val o2GenerationRatingDecimal = Integer.parseInt(o2GenerationRatingStr.toString(), 2)
    val co2ScrubberRatingDecimal = Integer.parseInt(co2ScrubberRatingStr.toString(), 2)

    println("--- Part 2 ---")
    println("O2 Generation Rating = $o2GenerationRatingStr - $o2GenerationRatingDecimal")
    println("C02 Scrubber Rating = $co2ScrubberRatingStr - $co2ScrubberRatingDecimal")
    println("Result = ${o2GenerationRatingDecimal * co2ScrubberRatingDecimal}")
}

fun o2GeneratorRating(input: List<String>, charIdx: Int = 0): String {
    if (input.size == 1) return input[0]
    if (charIdx >= input[0].length) return "Something went wrong! charIdx is bigger than the input element length!"

    val (onesList, zerosList) = splitLists(input, charIdx)

    return if (onesList.size >= zerosList.size) {
        o2GeneratorRating(onesList, charIdx + 1)
    } else {
        o2GeneratorRating(zerosList, charIdx + 1)
    }
}

fun co2ScrubberRating(input: List<String>, charIdx: Int = 0): String {
    if (input.size == 1) return input[0]
    if (charIdx >= input[0].length) return "Something went wrong! charIdx is bigger than the input element length!"

    val (onesList, zerosList) = splitLists(input, charIdx)

    return if (zerosList.size <= onesList.size) {
        co2ScrubberRating(zerosList, charIdx + 1)
    } else {
        co2ScrubberRating(onesList, charIdx + 1)
    }
}

fun splitLists(input: List<String>, charIdx: Int): Pair<List<String>, List<String>> {
    val onesList = mutableListOf<String>()
    val zerosList = mutableListOf<String>()

    input.forEach {
        if (it[charIdx] == '1') {
            onesList.add(it)
        } else {
            zerosList.add(it)
        }
    }

    return Pair(onesList, zerosList)
}
