fun main() {
//    val input = """
//        2-4,6-8
//        2-3,4-5
//        5-7,7-9
//        2-8,3-7
//        6-6,4-6
//        2-6,4-8
//    """.trimIndent().lines()
//    Day04().calculate(input, 2, 4)
    val (firstPart, secondPart) = Day04().calculate()
    firstPart.println()
    secondPart.println()
}

class Day04 : Challenge {

    override fun firstPart(input: List<String>): Int {
        return calculateResultWithOperation(input) { firstNumbers: List<Int>, secondNumbers: List<Int> ->
            if (firstNumbers.toList().containsAll(secondNumbers.toList()) || secondNumbers.toList()
                    .containsAll(firstNumbers.toList())
            ) {
                1
            } else {
                0
            }
        }
    }

    override fun secondPart(input: List<String>): Int {
        return calculateResultWithOperation(input) { firstNumbers: List<Int>, secondNumbers: List<Int> ->
            if (firstNumbers.toList().any { section -> secondNumbers.contains(section) } || secondNumbers.toList()
                    .any { section -> firstNumbers.contains(section) }
            ) {
                1
            } else {
                0
            }
        }
    }

    private fun calculateResultWithOperation(input: List<String>, operation:  (firstNumbers: List<Int>, secondNumbers: List<Int>) -> Int): Int {
        val result = input.map {
            val (first, second) = it.split(",")
            val firstNumbers =
                first.substring(0, first.indexOf("-")).toInt()..first.substring(first.indexOf("-") + 1).toInt()
            val secondNumbers =
                second.substring(0, second.indexOf("-")).toInt()..second.substring(second.indexOf("-") + 1).toInt()

            operation(firstNumbers.toList(), secondNumbers.toList())
        }
        return result.sum()
    }

}
