package conceptoftheday

import conceptoftheday.modules.{Pyramid, ReverseString}

/**
  * Created by jhenrie on 9/27/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val string1 = "Hello World"
    println(string1)
    println(ReverseString.reverse(string1))
    println(ReverseString.recursiveMethod(string1))

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
  }
}
