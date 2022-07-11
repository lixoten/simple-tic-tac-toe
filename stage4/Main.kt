// Stage Stage 4/5: First move_
package tictactoe

import java.lang.NumberFormatException

fun initializeBoard(board: MutableList<MutableList<Char>>, value: String) {
    var c: Char
    var cnt = 0
        for (row in board.indices){
            for (col in board[row].indices){
                c = value[cnt++].toChar()
                //println("----$row - $col - $c")
                board[row][col] = c
            }
        }
}

fun main() {

    val board = MutableList<MutableList<Char>>(3) {
        MutableList<Char>(3) { '_'}
    }

    print("Enter cells: ")
    var input = readln().toString()
    //    input = "XXXOO__O_" // across
    //    input = "XOXOXOXXO" // diag up
    //    input = "XOXXOOXX_" // up and down
    //    input = "XOOOXOXOX" // diag down
    //    input = "XO_OXXOXO" // NOT over
    //    input = "XOXOXXOX_" // NOT over

    initializeBoard(board, input)
    printRowSeatLayout(board, 9)

    var invalidInput = false
    do {
        //println(board)
        print("Enter the coordinates: ")

        try {
            var (row, col) = readln().split(" ").map { it.toInt() }

            // checkSquareAvailability()
            if (board[row - 1][col - 1] != '_') {
                println("This cell is occupied! Choose another one!")
                invalidInput = true
            } else {
                board[row - 1][col - 1] = 'X'
                invalidInput = false
            }
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            invalidInput = true
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            invalidInput = true
        }

        if (!invalidInput) printRowSeatLayout(board, 9)
    } while (invalidInput)



/*
        var xIsWinner = false
        var oIsWinner = false

        var result: Char
        result = analyzeImpossible(board)
        if (result == 'I'){
            println("Impossible")
        } else {
            var player = 'X'
            result = checkWinRow(board, 'X')
            if (result == 'X'){
                xIsWinner = true
            }

            result = checkWinRow(board, 'O')
            if (result == 'O'){
                oIsWinner = true
            }

            if (xIsWinner && oIsWinner) {
                println("Impossible")
            } else if (xIsWinner){
                println("X wins")
            } else if (oIsWinner){
                println("O wins")
            } else {
                result = analyzeGameNotFinish(board)
                if (result == 'N'){
                    println("Game not finished")
                } else {
                    println("Draw")
                }
            }
        }
//    }

*/

    return
    //analyzeBoard(board)
}



fun checkWinRow(board: MutableList<MutableList<Char>>, player: Char):Char {

    //check for 4 across
    //val player = 'X'

    for (row in board.indices) {

        for (col in 0 until board[0].size - 2) {

            if (board[row][col] == player &&
                board[row][col + 1] == player &&
                board[row][col + 2] == player) {
                //println("x WOW across")
                return player
            }

        }

    }


    //check for 4 up and down

    for (row in 0 until board.size - 2) {

        for (col in 0 until board[0].size) {

            if (board[row + 0][col] == player &&
                board[row + 1][col] == player &&
                board[row + 2][col] == player) {
                //println("x WOW up and down")
                return player
            }

        }

    }

    //check upward diagonal

    for (row in 2 until board.size) {

        for (col in 0 until board[0].size - 2) { // - 3 - 4

            if (board[row][col] == player &&
                board[row - 1][col + 1] == player &&
                board[row - 2][col + 2] == player) {
                //println("x WOW up DIAG")
                return player
            }

        }

    }

    //check downward diagonal

    for (row in 0 until board.size - 2) { // - 4

        for (col in 0 until board[0].size - 2) { // - 4

            if (board[row][col] == player &&
                board[row + 1][col + 1] == player &&
                board[row + 2][col + 2] == player) {
                //println("x WOW down DIAG")
                return player
            }

        }

    }

    return '?'

}
fun analyzeGameNotFinish(board: MutableList<MutableList<Char>>) :Char { //: MutableList<Int>

    for (i in board){

        if (i.contains('_')){
            return 'N'
        }

    }

    return '?'
}

fun analyzeImpossible(board: MutableList<MutableList<Char>>) :Char { //: MutableList<Int>

    var oCnt = 0
    var xCnt = 0
    for (row in board.indices) {
        for (col in board[row].indices) {
            if (board[row][col] == 'X') {
                ++xCnt
            } else if (board[row][col] == 'O') {
                ++oCnt
            }
        }
    }


    if (oCnt - xCnt >= 2 || xCnt - oCnt >= 2 ){
        return 'I'
    }
    return '?'
}


fun printRowSeatLayout(cinema: MutableList<MutableList<Char>>, totalSeats: Int) {
    //println(" ")
    println("---------")

    var cnt = 0
    for (rowIdx in cinema.indices){
        print("|")
        for (seat in cinema[rowIdx]){
            print(" $seat")
        }
        print(" |")
        println()
    }
    println("---------")
}