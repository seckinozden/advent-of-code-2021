import java.util.Stack

fun main() {
    val input = readInput("Day10_input")

    day10Part1(input)
    day10Part2(input)
}

fun day10Part2(input: List<String>) {
    val pairMap = mutableMapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<',
    )
    val incompleteLineScores = mutableListOf<Long>()
    input.forEach lineLoop@{ line ->
        val stack = Stack<Char>()
        line.toCharArray().forEach {
            when (it) {
                in listOf('(', '[', '{', '<') -> {
                    stack.push(it)
                }
                in listOf(']', '}', ')', '>') -> {
                    if (stack.isEmpty() || !stack.pop().equals(pairMap[it])) {
                        // it is corrupted line
                        // we are interested in incomplete lines
                        return@lineLoop
                    }
                }
            }
        }
        if (stack.isNotEmpty()) {
            var score = 0L
            while (stack.isNotEmpty()) {
                score *= 5
                when (stack.pop()) {
                    '(' -> { score += 1 }
                    '[' -> { score += 2 }
                    '{' -> { score += 3 }
                    '<' -> { score += 4 }
                }
            }
            incompleteLineScores.add(score)
        }
    }
    val result = incompleteLineScores.sorted()[incompleteLineScores.size / 2]

    println("--- Part 2 ---")
    println("Result= $result")
}

fun day10Part1(input: List<String>) {
    val countMap = mutableMapOf(
        ')' to 0,
        ']' to 0,
        '}' to 0,
        '>' to 0,
    )
    val pairMap = mutableMapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<',
    )
    input.forEach lineLoop@{ line ->
        val stack = Stack<Char>()
        line.toCharArray().forEach {
            when (it) {
                in listOf('(', '[', '{', '<') -> {
                    stack.push(it)
                }
                in listOf(']', '}', ')', '>') -> {
                    if (!stack.pop().equals(pairMap[it])) {
                        val count = countMap[it]!!
                        countMap[it] = count + 1
                        return@lineLoop
                    }
                }
            }
        }
    }
    var result = 0
    result += countMap[')']!! * 3
    result += countMap[']']!! * 57
    result += countMap['}']!! * 1197
    result += countMap['>']!! * 25137

    println("--- Part 1 ---")
    println("Result= $result")
}
