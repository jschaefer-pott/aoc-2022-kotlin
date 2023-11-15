fun main() {

    // test if implementation meets criteria from the description, like:
    val input = readInput("02")
    // assumption: second column is the player move
    firstPart(input)

    //assumption: second column is the wanted game result:
    // X means player loses, Y means draw, Z means player wins
    secondPart(input)
}

private fun firstPart(input: List<String>) {
    val games = input.map {
        val opponentMove = it.substring(0, 1).parseToOpponentMove()
        val playerMove = it.substring(2, 3).parseToPlayerMove()
        opponentMove to playerMove
    }
    val playerScore = calculateScore(games)

    println("Score for the first assumption is: ${playerScore.sum()}")
}

private fun secondPart(input: List<String>) {
    val games = input.map {
        val opponentMove = it.substring(0, 1).parseToOpponentMove()
        val wantedOutcome = it.substring(2, 3).parseToWantedOutcome()
        val neededPlayerMove = when (opponentMove) {
            OpponentMove.ROCK -> {
                when (wantedOutcome) {
                    OutCome.LOSE -> PlayerMove.SCISSORS
                    OutCome.DRAW -> PlayerMove.ROCK
                    OutCome.WIN -> PlayerMove.PAPER
                }
            }

            OpponentMove.PAPER -> {
                when (wantedOutcome) {
                    OutCome.LOSE -> PlayerMove.ROCK
                    OutCome.DRAW -> PlayerMove.PAPER
                    OutCome.WIN -> PlayerMove.SCISSORS
                }
            }

            OpponentMove.SCISSORS -> {
                when (wantedOutcome) {
                    OutCome.LOSE -> PlayerMove.PAPER
                    OutCome.DRAW -> PlayerMove.SCISSORS
                    OutCome.WIN -> PlayerMove.ROCK
                }
            }
        }
        opponentMove to neededPlayerMove
    }

    val playerScore = calculateScore(games)

    println("Score for the second assumption is: ${playerScore.sum()}")
}

private fun calculateScore(games: List<Pair<OpponentMove, PlayerMove>>): List<Int> {
    val playerScore = games.map {
        val generalScore = when (it.second) {
            PlayerMove.ROCK -> 1
            PlayerMove.PAPER -> 2
            PlayerMove.SCISSORS -> 3
        }
        val draw = it.first.name == it.second.name

        val playerWon = if (draw) {
            false
        } else when (it.second) {
            PlayerMove.PAPER -> it.first == OpponentMove.ROCK
            PlayerMove.ROCK -> it.first == OpponentMove.SCISSORS
            PlayerMove.SCISSORS -> it.first == OpponentMove.PAPER
        }
        generalScore + (if (draw) 3 else if (playerWon) 6 else 0)
    }
    return playerScore
}

private fun String.parseToWantedOutcome(): OutCome {
    return when (this) {
        "X" -> OutCome.LOSE
        "Y" -> OutCome.DRAW
        "Z" -> OutCome.WIN
        else -> throw IllegalArgumentException()
    }
}

enum class OutCome {
    LOSE, DRAW, WIN

}

private fun String.parseToOpponentMove(): OpponentMove {
    return when (this) {
        "A" -> OpponentMove.ROCK
        "B" -> OpponentMove.PAPER
        "C" -> OpponentMove.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

private fun String.parseToPlayerMove(): PlayerMove {
    return when (this) {
        "X" -> PlayerMove.ROCK
        "Y" -> PlayerMove.PAPER
        "Z" -> PlayerMove.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

enum class OpponentMove {
    ROCK, PAPER, SCISSORS
}

enum class PlayerMove {
    ROCK, PAPER, SCISSORS
}
