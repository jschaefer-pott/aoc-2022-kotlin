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

    fun calculate() {
        val input = readInput(this.javaClass.name.takeLast(2))

        firstPart(input)

        secondPart(input)
    }

    fun calculate(input: List<String>) {

        firstPart(input)

        secondPart(input)
    }

    fun firstPart(input: List<String>)
    fun secondPart(input: List<String>)
}
