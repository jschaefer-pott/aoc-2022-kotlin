import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("inputs", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

interface Challenge {

    fun calculate(): Pair<Int, Int> {
        val input = readInput(this.javaClass.name.takeLast(2))

        return firstPart(input) to secondPart(input)
    }

    fun calculate(firstExpected: Int, secondExpected: Int): Pair<Int, Int> {
        val input = readInput(this.javaClass.name.takeLast(2))

        val firstPart = firstPart(input)
        assertEquals(firstExpected, firstPart)
        println("First result is $firstExpected")

        val secondPart = secondPart(input)
        assertEquals(secondExpected, secondPart)
        println("Second result is $secondExpected")

        return firstPart(input) to secondPart(input)
    }

    fun calculate(input: List<String>, firstExpected: Int, secondExpected: Int): Pair<Int, Int> {

        val firstPart = firstPart(input)
        assertEquals(firstExpected, firstPart)
        println("First result is $firstExpected")

        val secondPart = secondPart(input)
        assertEquals(secondExpected, secondPart)
        println("Second result is $secondExpected")

        return firstPart to secondPart
    }

    fun firstPart(input: List<String>): Int
    fun secondPart(input: List<String>): Int
}
