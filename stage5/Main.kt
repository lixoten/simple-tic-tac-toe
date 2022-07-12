// Stage Stage 5/5: Fight!
package tictactoe

import java.lang.NumberFormatException

fun initializeBoard(board: MutableList<MutableList<Char>>, value: String) {
    var c: Char
    var cnt = 0
    for (row in board.indices){
        for (col in board[row].indices){
            c = value[cnt++]
            board[row][col] = c
        }
    }
}


fun main() {
    val board = MutableList(3) {
        MutableList(3) { '_'}
    }

    initializeBoard(board, "_________")
    printRowSeatLayout(board)

    var outcomeStatus = 'D'

    var player = 'X'
    var gameOver = false
    while (!gameOver) {

        var invalidInput: Boolean
        do {
            print("Enter the coordinates: ")

            try {
                val (row, col) = readln().split(" ").map { it.toInt() }

                if (board[row - 1][col - 1] != '_') {
                    println("This cell is occupied! Choose another one!")
                    invalidInput = true
                } else {
                    board[row - 1][col - 1] = player
                    invalidInput = false
                }
            } catch (e: IndexOutOfBoundsException) {
                println("Coordinates should be from 1 to 3!")
                invalidInput = true
            } catch (e: NumberFormatException) {
                println("You should enter numbers!")
                invalidInput = true
            }

            if (!invalidInput) {
                player = if (player == 'X') 'O' else 'X'
            }


            if (!board[0].contains('_') &&
                !board[1].contains('_') &&
                !board[2].contains('_')) {
                outcomeStatus = 'D'
                gameOver = true
            }

            var result: Char = checkWinRow(board, 'X')
            if (result == 'X'){
                outcomeStatus = 'X'
                gameOver = true
                break
            }

            result = checkWinRow(board, 'O')
            if (result == 'O'){
                outcomeStatus = 'O'
                gameOver = true
                break
            }

            if (!invalidInput) printRowSeatLayout(board)
        } while (invalidInput)
    }

    printRowSeatLayout(board)
    when (outcomeStatus) {
        'X' -> println("X wins")
        'O' -> println("O wins")
        'D' -> println("Draw")
    }

    return
}

fun checkWinRow(board: MutableList<MutableList<Char>>, player: Char):Char {
    // ---check across
    for (row in board.indices) {
        for (col in 0 until board[0].size - 2) {
            if (board[row][col] == player &&
                board[row][col + 1] == player &&
                board[row][col + 2] == player) {
                return player
            }
        }
    }

    // ---check up and down
    for (row in 0 until board.size - 2) {
        for (col in 0 until board[0].size) {
            if (board[row + 0][col] == player &&
                board[row + 1][col] == player &&
                board[row + 2][col] == player) {
                return player
            }
        }
    }

    // ---check upward diagonal
    for (row in 2 until board.size) {
        for (col in 0 until board[0].size - 2) { // - 3 - 4
            if (board[row][col] == player &&
                board[row - 1][col + 1] == player &&
                board[row - 2][col + 2] == player) {
                return player
            }
        }
    }

    // ---check downward diagonal
    for (row in 0 until board.size - 2) { // - 4
        for (col in 0 until board[0].size - 2) { // - 4
            if (board[row][col] == player &&
                board[row + 1][col + 1] == player &&
                board[row + 2][col + 2] == player) {
                return player
            }
        }
    }
    return '?'
}

fun printRowSeatLayout(board: MutableList<MutableList<Char>>) {
    println("---------")
    for (rowIdx in board.indices){
        print("|")
        for (cell in board[rowIdx]){
            print(" $cell")
        }
        print(" |")
        println()
    }
    println("---------")
}
// 153 185 194