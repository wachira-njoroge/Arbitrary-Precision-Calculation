package org.example
fun main(){
    println("Welcome to the Arbitrary Precision Calculator\n")
    while(true){
        println("Enter the first value or type 'exit' to Quit")
        val value1 = readLine()?.trim()
        if (value1.equals("exit", ignoreCase = true)) {
            println("Goodbye!")
            break
        }
        //
        println("Enter the second value or type 'exit' to Quit")
        val value2 = readLine()?.trim()
        if (value2.equals("exit", ignoreCase = true)){
            println("Goodbye!")
            break
        }
        println("Enter the arithmetic operation: e.g., sum, divide, multiply, subtract or 'exit' to Quit")
        val operation = readLine()?.trim()
        if (operation.equals("exit", ignoreCase = true)){
            println("Goodbye!")
            break
        }
        if (operation != "sum" && operation != "divide" && operation != "subtract"){
            throw IllegalArgumentException("Invalid operation provided")
        }
        val result = calculate(value1!!, value2!!, operation)
        println("Result is :-> $result")
    }

}
/*
* The function below is intended for arbitrary precision calculation. It takes in the inputs to calculate and emits a
* result. Using inputs of type string, to cater for values that are larger than the ordinary limit for the Int
* datatype.
* */
fun calculate(input1:String, input2:String, operation:String):String {
    //Convert the input strings into working values
    val num1 = input1.map { it.digitToInt() }
    val num2 = input2.map { it.digitToInt() }
    //
    return when(operation){
        "sum" -> sum(num1, num2)
        "subtract" -> subtraction(num1, num2)
        //"divide" -> divide(num1, num2)
        else -> "Invalid operation"
    }
}
fun sum(value1:List<Int>, value2:List<Int>):String{
    //Set the sum result as a list of the calculated digits
    val result = mutableListOf<Int>()
    //
    var carried = 0
    //Get the largest value of the two inputs provided
    val maxSize = maxOf(value1.size, value2.size)
    //set the loop to run for the number of digits in the largest value
    for (i in 0 until maxSize){
        //Get the last digit in the first input since summing in this case is starting from right to left
        //i.e., I get the last digit of first input and sum it with the last digit of second input
        val firstValue = if(i < value1.size) value1[value1.size -1 -i] else 0
        val secondValue = if (i < value2.size) value2[value2.size - 1 - i] else 0
        //Sum the two values and any other value that could have been carried over, in the previous operation
        //i.e., if previous round had 9 + 9 then 1 is the value in the carry variable
        val sum = firstValue + secondValue + carried
        //Get the last digit from the summed value and add it to the result digit list
        result.add(0,sum %10)
        //Get the whole number from the summed value and set it as the carried over figure to be used in calculating the
        // value for the next column, in the succeeding iteration
        carried = sum / 10
    }
    //Once the loop is done and there is a value that was carried over in the last iteration, add it to the result
    // digit list as the last column to the left
    if(carried > 0) result.add(0,carried)
    //Convert the digit list to a readable string of the sum output
    return result.joinToString("")
}
fun subtraction(value1: List<Int>, value2:List<Int>):String{
    val result = mutableListOf<Int>()
    //Get the largest value of the inputs provided to set the working value
    val maxlength = maxOf(value1.size, value2.size)
    //
    var borrow = 0
    //check which of the two inputs is larger, to determine the subtraction order and perform the calculation in
    // reverse and append ("-") on the result if need be
    val negativeResult = value1.size < value2.size || value1.size == value2.size && value1[0] < value2[0]
    //
    val largerValue = if(negativeResult) value2 else value1
    val smallerValue = if(negativeResult) value1 else value2
    //
    for (i in 0 until maxlength){
        val valueOne = if (i < largerValue.size ) largerValue[largerValue.size -1 -i] else 0
        val valueTwo = if (i < smallerValue.size ) smallerValue[smallerValue.size -1 -i] else 0

        var difference = valueOne - valueTwo - borrow
        //If the difference is negative, borrow from the next digit to the right
        if(difference < 0){
            difference += 10
            borrow = 1
        }
        result.add(0, difference)
    }
    //Using digit list to perform the subtraction results in leading zeros that should be cleaned from result
    while (result.size > 1 && result[0] ==0){
        result.removeAt(0)
    }
    return if(negativeResult) "-${result.joinToString("")}" else result.joinToString("")
}
//fun divide(value1: List<Int>, value2: List<Int>):String{
//
//}
//fun multiply(value1: List<Int>, value2: List<Int>):String{
//
//}