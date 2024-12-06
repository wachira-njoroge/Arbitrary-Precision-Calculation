import org.example.Calc
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArbitraryCalculationTests: Calc() {

    @Test
    fun sum() {
        val expected = "797552011225589898876229034"
        val input1 = "7896554566589998998774544"
        val input2 = "789655456658999899877454490"
        assertEquals(expected, calculate(input1, input2,"sum"))
    }

    @Test
    fun divide() {
        val expected = "101.8252740167"
        val input1 = "789655"
        val input2 = "7755"
        assertEquals(expected, calculate(input1, input2,"divide"))
    }

    @Test
    fun multiply() {
        val expected = "6235557402313336693176521358098988230341284130502560"
        val input1 = "7896554566589998998774544"
        val input2 = "789655456658999899877454490"
        assertEquals(expected, calculate(input1, input2,"multiply"))
    }

    @Test
    fun subtract() {
        val expected = "-671758891081309890878679846"
        val input1 = "7896554566589998998774544"
        val input2 = "789655456658999899877454490"
        assertEquals(expected, calculate(input1, input2,"subtract"))
    }
}