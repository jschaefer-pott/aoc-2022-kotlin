fun main() {
    val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent().lines()
//    Results should be 157 and 70 for above input
    Day03().calculate(input, 157, 70)

    val (firstPart, secondPart) = Day03().calculate()
    firstPart.println()
    secondPart.println()
}

class Day03 : Challenge {

    companion object {
        @JvmStatic
        private val LOWERCASE_LETTERS_OFFSET = 96

        @JvmStatic
        private val UPPERCASE_LETTERS_OFFSET = 38
    }

    override fun firstPart(input: List<String>): Int {
        val result = input.flatMap { rucksack ->
            val firstCompartment = rucksack.take(rucksack.length / 2)
            val secondCompartment = rucksack.takeLast(rucksack.length / 2)

            val duplicateItems: List<Char> = mutableListOf()

            firstCompartment.forEach { item ->
                if (secondCompartment.contains(item)) {
                    if (!duplicateItems.contains(item)) {
                        duplicateItems.addLast(item)
                    }
                }
            }

            val duplicatePriorities = duplicateItems.map { item ->
                calculatePriorityFromCharacterCode(item.code)
            }

            duplicatePriorities.filterNot { it == 0 }
        }

        return result.sum()
    }

    override fun secondPart(input: List<String>): Int {
        val grouped = input.chunked(3)
        val result = grouped.flatMap {
            val firstRucksack = it[0]
            val secondRucksack = it[1]
            val thirdRucksack = it[2]
            val commonItems = mutableListOf<Char>()
            firstRucksack.forEach { item ->
                if (secondRucksack.contains(item) && thirdRucksack.contains(item)) {
                    if (!commonItems.contains(item)) {
                        commonItems.addLast(item)
                    }
                }
            }
            commonItems.map { item -> calculatePriorityFromCharacterCode(item.code) }
        }

        return result.sum()
    }


    private fun calculatePriorityFromCharacterCode(itemCode: Int) = when (itemCode) {
        in 65..90 -> {
            itemCode - UPPERCASE_LETTERS_OFFSET
        }

        in 97..122 -> {
            itemCode - LOWERCASE_LETTERS_OFFSET
        }

        else -> {
            0
        }
    }
}
