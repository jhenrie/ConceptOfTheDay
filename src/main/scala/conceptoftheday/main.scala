package conceptoftheday

import conceptoftheday.modules.encoding.{UtoUCodex, UtoUProcessor}

/**
  * Created by jhenrie on 9/27/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("****************************************")
    val text = "This is a test of the script. This is only a test. If this was real well it would still not matter."
    //val text = "Cat"
    //val text = "This is a test only a test."
    val file = "test.txt"
    val results = UtoUCodex.encode(text)
    println(s"${results.fold(println(_), println(_))}")
    println("****************************************")
  }
}
