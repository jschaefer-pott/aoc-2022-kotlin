fun main() {

//    val input = """
//        1000
//        2000
//        3000
//
//        4000
//
//        5000
//        6000
//
//        7000
//        8000
//        9000
//
//        10000
//    """.trimIndent().lines()
//    Day01().calculate(input, 24000, 41000)

    Day01().calculate(68787, 198041)

}

class Day01 : Challenge {
    override fun firstPart(input: List<String>) = calculateCaloriesPerElfAndApplyOperation(input) { caloriesPerElf ->
        caloriesPerElf.max()

    }

    private fun calculateCaloriesPerElfAndApplyOperation(input: List<String>, caloriesPerElf: (List<Int>) -> Int): Int {
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
        return caloriesPerElf(result)
    }

    override fun secondPart(input: List<String>) = calculateCaloriesPerElfAndApplyOperation(input) { caloriesPerElf ->
        caloriesPerElf.sortedDescending().take(3).sum()

    }
}
