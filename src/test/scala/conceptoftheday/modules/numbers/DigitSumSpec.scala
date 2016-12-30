package conceptoftheday.modules.numbers

import org.scalatest.FlatSpec

class DigitSumSpec extends FlatSpec {
  behavior of "The DigitSum object"

  it should "sum int 0 to 0" in {
    assert(0 == DigitSum.sum(0))
  }

  it should "sum int 1234 to 10" in {
    assert(10 == DigitSum.sum(1234))
  }

  it should "sum int -1234 to -10" in {
    assert(-10 == DigitSum.sum(-1234))
  }

  it should "sum in 837267 to 33" in {
    assert(33 == DigitSum.sum(837267))
  }
}
