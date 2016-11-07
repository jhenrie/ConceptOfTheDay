package conceptoftheday

import conceptoftheday.modules.Pyramid
import conceptoftheday.modules.String.{ReverseString, StringTabulation}
import conceptoftheday.modules.numbers.DigitSum
import conceptoftheday.modules.uuencode.Uuencode

/**
  * Created by jhenrie on 9/27/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val string1 = "Hello World"
    val string2 = "This is a test of string tabulation."
    println(string1)
    println(ReverseString.reverse(string1))
    println(string2)
    println(ReverseString.recursiveMethod(string2))
    println(string1)
    (StringTabulation.tabulate(string1)).map(row => println(s"Row: ${row._1} - ${row._2}"))
    println(string2)
    (StringTabulation.tabulate(string2)).map(row => println(s"Row: ${row._1} - ${row._2}"))

    //Handling Either()'s with match.
    //Bulky lots of boiler plate
    Pyramid.buildPyramid(6) match {
      case Left(msg) => println(msg)
      case Right(pyramid) => pyramid.map(row => println(row))
    }
    Pyramid.buildPyramid(0) match {
      case Left(msg) => println(msg)
      case Right(pyramid) => pyramid.map(row => println(row))
    }

    //Handling Either()'s with fold
    //Much cleaner.
    Pyramid.buildPyramid(9).fold(println(_), _.map(row => println(row)))
    Pyramid.buildPyramid(10).fold(println(_), _.map(row => println(row)))

    val number1 = 1234
    val number2 = 837267
    println(s"Number: ${number1} - Sum: ${DigitSum.sum(number1)}")
    println(s"Number: ${number2} - Sum: ${DigitSum.sum(number2)}")

    println("****************************************")
    val text = Some("This is a test of the script. This is only a test. If this was real well it would still not matter.")
    val file = Some("test.joe")
    val results = Uuencode.encode(text,file)
    println(s"${results.getOrElse("**ERROR**")}")
    println("******")
    val text2 = Some("Cat")
    val file2 = None
    val results2 = Uuencode.encode(text2,file2)
    println(s"${results2.getOrElse("**ERROR**")}")
    println("******")
    val text3 = None
    val file3 = None
    val results3 = Uuencode.encode(text3,file3)
    println(s"${results3.getOrElse("**ERROR**")}")
    println("******")
  }
}
