package conceptoftheday.modules

import org.scalatest.FlatSpec

/**
  * Created by jhenrie on 11/8/16.
  */
class PyramidSpec extends FlatSpec{
  behavior of "The Pyramid object"

  it should "return an Right with a List[String] when given the parameter 3" in {
    Pyramid.buildPyramid(3) match {
      case Left(result) => fail()
      case Right(result) => {
        assert(result.isInstanceOf[List[String]])
        assert(result.size == 3)
      }
    }
  }

  it should "return a Left with a String when given a parameter 0" in {
    Pyramid.buildPyramid(0) match {
      case Left(result) => assert(result.isInstanceOf[String])
      case Right(result) => fail()
    }
  }

  it should "return a Right List[String] with three rows when given a parameter of 6" in {
    Pyramid.buildPyramid(6) match {
      case Left(result) => fail()
      case Right(result) => {
        assert(result.isInstanceOf[List[String]])
        assert(result.size == 6)
        result.view.zipWithIndex.foreach { case (str, idx) => assert(str.count(_ == ((idx + 1) + 48).toChar) == (idx +1)) }
      }
    }
  }
}
