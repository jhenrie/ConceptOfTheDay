package conceptoftheday.modules.mirrorencrypt

import org.scalatest.FlatSpec

/**
  * Created by jhenrie on 3/10/17.
  */
class GridPointSpec extends FlatSpec{
  behavior of "The GridPoint object"

  it should "return a GridPoint object with values 1,1" in {
    val test = GridPoint(1,1)
    assert(test.x == 1)
    assert(test.y == 1)
  }

  it should "return true if isEdge is called with values 0,1" in {
    val test = GridPoint(0,1)
    assert(test.isEdge())
  }

  it should "return true if isEdge is called with values 1,0" in {
    val test = GridPoint(1,0)
    assert(test.isEdge())
  }

  it should "return false if isEdge is called with values 1,1" in {
    val test = GridPoint(1,1)
    assert(!test.isEdge())
  }

  it should "return the following string \"1,1\" when mkString is called with values 1,1" in {
    val test = GridPoint(1,1)
    assert("1,1".equals(test.mkString()))
  }

  it should "return the following string \"1|1\" when mkString is called with values 1,1 and sep |" in {
    val test = GridPoint(1,1)
    assert("1|1".equals(test.mkString("|")))
  }
}
