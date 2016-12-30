package conceptoftheday.modules.encoding

import org.scalatest.FlatSpec

/**
  * Created by jhenrie on 11/18/16.
  */
class UtoUCodexSpec extends FlatSpec {
  behavior of "The UtoUCodex Object"

  it should "encode the string Cat to #0V%T\\n`\\n and return the result in a Right" in {
    val text = "Cat"
    val encode = "#0V%T\n`\n"

    val result = UtoUCodex.encode(text)
    assert(result.isRight)

    result match {
      case Right(msg) => assert(encode.equals(msg))
      case Left(error) => fail()
    }
  }

  it should "return an error in a Left when being asked to encode an empty input" in {
    val text = ""

    val result = UtoUCodex.encode(text)
    assert(result.isLeft)
  }

  it should "encode a longer string that will cross the 45 byte line split with the result being Right" in {
    val text = "This is a test. Only a test so bobs your uncle"
    val encode = "M5&AI<R!I<R!A('1E<W0N($]N;'D@82!T97-T('-O(&)O8G,@>6]U<B!U;F-L\n!90  \n`\n"

    val result = UtoUCodex.encode(text)

    result match {
      case Right(msg) => assert(encode.equals(msg))
      case Left(error) => fail()
    }
  }

  it should "decode the data List[#0V%T,`] and return the result in a Right" in {
    val encode = "#0V%T"
    val input = encode :: "`" :: Nil
    val text = "Cat"

    val result = UtoUCodex.decode(input)
    assert(result.isRight)

    result match {
      case Right(msg) => assert(text.equals(msg))
      case Left(error) => fail()
    }
  }

  it should "return an error in a Left when being asked to decode an empty List" in {
    val input = List[String]()

    val result = UtoUCodex.decode(input)
    assert(result.isLeft)
  }

  it should "return an error in a Left when being asked to decode only the terminator" in {
    val input = "`" :: Nil

    val result = UtoUCodex.decode(input)
    assert(result.isLeft)
  }

  it should "decode a body list that holds multiple 45 byte rows and return a result being Right" in {
    val encode1 = "M5&AI<R!I<R!A('1E<W0@;V8@=&AE('-C<FEP=\"X@5&AI<R!I<R!O;FQY(&$@"
    val encode2 = "M=&5S=\"X@268@=&AI<R!W87,@<F5A;\"!W96QL(&ET('=O=6QD('-T:6QL(&YO"
    val encode3 = ")=\"!M871T97(N"
    val input = encode1 :: encode2 :: encode3 :: "`" :: Nil

    val text = "This is a test of the script. This is only a test. If this was real well it would still not matter."

    val result = UtoUCodex.decode(input)
    assert(result.isRight)

    result match {
      case Right(msg) => assert(text.equals(msg))
      case Left(error) => fail()
    }
  }
}
