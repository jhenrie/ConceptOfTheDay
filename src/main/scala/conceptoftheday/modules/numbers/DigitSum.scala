package conceptoftheday.modules.numbers

/**
  * Recursive function to sum all the digits of a given
  * number.
  */
object DigitSum {
  def sum(num: Int): Int = {
    val result = num % 10
    if (result == 0) return 0
    else result + sum(num/10)
  }
}
