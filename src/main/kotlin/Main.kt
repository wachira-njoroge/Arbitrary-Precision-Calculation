package org.example
fun main(){
    println("Enter the first value:")
    val input1 = readLine()?.trim() ?: return println("Invalid Input")
    println("Enter the second value:")
    val input2 = readLine()?.trim() ?: return println("Invalid Input")
    println("Enter the operation e.g., sum, subtract, divide")
    val op = readLine()?.trim() ?: return println("Invalid Input")
    val result = calculate(input1, input2, op)
    println("Result is :: $result")
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
        "divide" -> TODO()
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
//subtraction
fun subtraction(value1: List<Int>, value2:List<Int>):String{
    val result = mutableListOf<Int>()
    //Get the largest value of the inputs provided to set the working value
    val maxlength = maxOf(value1.size, value2.size)
    //
    for (i in 0 until maxlength){
        val valueOne = if (i < value1.size ) value1[value1.size -1 -i] else 0
        val valueTwo = if (i < value2.size ) value2[value2.size -1 -i] else 0
        //
        val difference = valueOne - valueTwo
    }
    return result.joinToString("")
}