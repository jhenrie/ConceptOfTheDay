package conceptoftheday

import conceptoftheday.modules.ReverseString

/**
  * Created by jhenrie on 9/27/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val string1 = "Hello World"
    println(string1)
    println(ReverseString.reverse(string1))
    println(ReverseString.recursiveMethod(string1))
  }
}
