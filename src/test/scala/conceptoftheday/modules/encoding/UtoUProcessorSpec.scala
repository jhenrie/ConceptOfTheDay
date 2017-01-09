package conceptoftheday.modules.encoding

import org.scalatest.FlatSpec
import java.nio.file.{Files, Paths}
import java.nio.charset.Charset

/**
  * Created by jhenrie on 11/18/16.
  */
class UtoUProcessorSpec extends FlatSpec {
  behavior of "The UtoUProcessor Object"

  it should "generate a file called test.txt" in {
    val path = "/Volumes/Expander1/code/IDE/ConceptOfTheDay/test.txt"
    val text = "TEST"

    val results = UtoUProcessor.write(text, "test.txt")
    results match {
      case Right(result) => {
        assert(Files.exists(Paths.get(path)))
        Files.delete(Paths.get(path))
      }
      case Left(error) => fail()
    }
  }

  it should "generate a file with 4 lines" in {
    val path = "/Volumes/Expander1/code/IDE/ConceptOfTheDay/test.txt"
    val text = "TEST"

    val results = UtoUProcessor.write(text, "test.txt")
    results match {
      case Right(result) => {
        assert(Files.exists(Paths.get(path)))
        val lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset() );
        assert(lines.size() == 4)

        Files.delete(Paths.get(path))
      }
      case Left(error) => fail()
    }
  }

  it should "generate a file with 4 lines with proper encoding" in {
    val path = "/Volumes/Expander1/code/IDE/ConceptOfTheDay/test.txt"
    val text = "TEST"
    val encode = "$5$535   "

    val results = UtoUProcessor.write(text, "test.txt")
    results match {
      case Right(result) => {
        assert(Files.exists(Paths.get(path)))
        val lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset() );
        assert(lines.size() == 4)
        assert(lines.get(0).equals("begin 644 test.txt"))
        assert(lines.get(1).equals(encode))
        assert(lines.get(2).equals("`"))
        assert(lines.get(3).equals("end"))

        Files.delete(Paths.get(path))
      }
      case Left(error) => fail()
    }
  }

  it should "generate a file with encoded text then decode the text back to original message" in {
    val path = "/Volumes/Expander1/code/IDE/ConceptOfTheDay/test.txt"
    val text = "TEST"

    val results = UtoUProcessor.write(text, "test.txt")
    results match {
      case Right(result) => {
        assert(Files.exists(Paths.get(path)))
        val result = UtoUProcessor.read(path)
        assert(result.isRight)
        result match {
          case Right(message) => {
            assert(message.equals(text))
          }
          case Left(error) => fail()
        }

        Files.delete(Paths.get(path))
      }
      case Left(error) => fail()
    }
  }

  it should "generate a file with a long encoded text then decode the text back to original message" in {
    val path = "/Volumes/Expander1/code/IDE/ConceptOfTheDay/test.txt"
    val text = "This is a test of the script. This is only a test. If this was real well it would still not matter."

    val results = UtoUProcessor.write(text, "test.txt")
    results match {
      case Right(result) => {
        assert(Files.exists(Paths.get(path)))
        val result = UtoUProcessor.read(path)
        assert(result.isRight)
        result match {
          case Right(message) => {
            assert(message.equals(text))
          }
          case Left(error) => fail()
        }

        Files.delete(Paths.get(path))
      }
      case Left(error) => fail()
    }
  }
}
