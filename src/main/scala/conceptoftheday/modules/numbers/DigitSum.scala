package conceptoftheday.modules.numbers

/**
  * Created by jhenrie on 10/1/16.
  */
object DigitSum {
  def sum(num: Int): Int = {
    val result = num % 10
    if (result == 0) return 0
    else result + sum(num/10)
  }
}
