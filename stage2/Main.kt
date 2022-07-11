// Stage 2/5: The user is the gamemaster
package tictactoe

fun main() {
    print("Enter cells: ")
    val input = readln()

    println("---------")
    print("| ")
    print(input[0]+ " ")
    print(input[1]+ " ")
    print(input[2]+ " ")
    println("|")

    print("| ")
    print(input[3]+ " ")
    print(input[4]+ " ")
    print(input[5]+ " ")
    println("|")

    print("| ")
    print(input[6]+ " ")
    print(input[7]+ " ")
    print(input[8]+ " ")
    println("|")
    println("---------")
}