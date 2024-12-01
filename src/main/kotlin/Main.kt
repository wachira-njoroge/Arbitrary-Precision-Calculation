package org.example
fun main(){
    val result = calculate("789","10",Operation.ADDITION)
    println("Result is :: $result")
}
//The enum class below is used to map the possible calculation options
 enum class Operation(op:String){
     MULTIPLY("multiply"),
     ADDITION("addition"),
     DIVIDE("division"),
     SUBTRACTION("substration")
 }
/*
* The function below is intended for arbitrary precision calculation. It takes in the inputs to calculate and emits a
* result. Using inputs of type string, to cater for values that are larger than the ordinary limit for the Int
* datatype.
* */
fun calculate(input1:String, input2:String, operation:Operation):String {
    //Convert the input strings into working values
    val num1 = input1.map { it.digitToInt() }
    val num2 = input2.map { it.digitToInt() }
    //
    return when(operation){
        Operation.MULTIPLY -> TODO()
        Operation.ADDITION -> sum(num1, num2)

        Operation.DIVIDE -> TODO()
        Operation.SUBTRACTION -> TODO()
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
        //Add the sum to the result list
        result.add(0,sum %10)
        carried = sum / 10
    }
    if(carried > 0) result.add(0,carried)
    //Convert the int list to readable string of the sum output
    return result.joinToString("")
}