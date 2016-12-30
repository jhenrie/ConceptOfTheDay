package conceptoftheday.modules.String

import org.scalatest.FlatSpec

class ReverseStringSpec extends FlatSpec {
  behavior of "The ReverseString object"

  it should "Reverse Joe to eoJ" in {
    assert("eoJ".equals(ReverseString.reverse("Joe")))
  }

  it should "Reverse Joe to eoJ using recursion" in {
    assert("eoJ".equals(ReverseString.recursiveMethod("Joe")))
  }

  it should "Reverse long string" in {
    val longString = "This is a test of string tabulation."
    val longCheck = ".noitalubat gnirts fo tset a si sihT"
    assert(longCheck.equals(ReverseString.reverse(longString)))
  }

  it should "Reverse long string using recursion" in {
    val longString = "This is a test of string tabulation."
    val longCheck = ".noitalubat gnirts fo tset a si sihT"
    assert(longCheck.equals(ReverseString.recursiveMethod(longString)))
  }
}
