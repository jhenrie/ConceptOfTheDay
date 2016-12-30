package conceptoftheday.modules.String

import org.scalatest.FlatSpec

/**
  * Created by jhenrie on 11/8/16.
  */
class StringTabulationSpec extends FlatSpec{
  behavior of "The StringTabulation object"

  it should "produce a single row map with a key of e and a value of 5 when given 'eeeee'" in {
    val test = "eeeee"
    val result = StringTabulation.tabulate(test)

    assert(result.contains('e'))
    assert(result('e') == 5)
    assert(result.size == 1)
  }

  it should "produce a multi row map with the keys of e and a when given 'aeeae'" in {
    val test = "aeeae"
    val result = StringTabulation.tabulate(test)

    assert(result.size == 2)

    assert(result.contains('e'))
    assert(result.contains('a'))

    assert(result('e') == 3)
    assert(result('a') == 2)
  }
}
