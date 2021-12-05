fun main() {
    val input = readInput("Day01_input")
    val nums = input.map { it.toInt() }

    day01Part1(nums)
    day01Part2(nums)
}

fun day01Part1(nums: List<Int>) {
    var increased = 0
    for (i in 1 until nums.size) {
        val cur = nums[i]
        val prev = nums[i - 1]

        if (cur > prev) increased++
    }

    println("--- Part 1 ---")
    println("Result of Part 1 = $increased")
}

fun day01Part2(nums: List<Int>) {
    var increased = 0
    for (i in 1 until nums.size - 2) {
        val cur = nums[i] + nums[i + 1] + nums[i + 2]
        val prev = nums[i - 1] + nums[i] + nums[i + 1]

        if (cur > prev) increased++
    }

    println("--- Part 2 ---")
    println("Result of Part 2= $increased")
}
