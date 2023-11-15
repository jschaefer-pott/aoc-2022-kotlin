fun main() {

    // test if implementation meets criteria from the description, like:
    val input = readInput("01")
    val result = mutableListOf<Int>()
    var current = 0
    input.forEach { calories ->
        if (calories.isEmpty()) {
            result.add(current)
            current = 0
        } else {
            current += calories.toInt()
        }
    }

    println("Biggest single elf calorie count: ${result.max()}")
    result.sortDescending()
    println("Total calorie count of top three elves: ${result.take(3).sum()}")

}
