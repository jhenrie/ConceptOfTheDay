package conceptoftheday.modules.mirrorencrypt

import org.scalatest.FlatSpec

/**
  * Created by jhenrie on 3/9/17.
  */
class CypherMachineSpec extends FlatSpec {
  val goodKey: Array[Array[Char]] = Array(
    Array(' ' ,' ',' ','\\','\\',' ' ,' ' ,'/','\\',' ',' ' ,' ',' '),
    Array(' ' ,' ',' ',' ' ,' ' ,' ' ,' ' ,' ',' ' ,' ',' ' ,' ','\\'),
    Array(' ' ,' ',' ','/' ,' ' ,' ' ,' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array(' ' ,' ',' ',' ' ,' ' ,' ' ,'\\',' ',' ' ,' ',' ' ,' ','\\'),
    Array(' ' ,' ',' ',' ' ,'\\',' ' ,' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array(' ' ,' ','/',' ' ,' ' ,' ' ,' ' ,' ',' ' ,'/',' ' ,' ',' '),
    Array('\\',' ',' ','/' ,' ' ,' ' ,' ' ,' ',' ' ,' ','\\',' ',' '),
    Array(' ' ,' ',' ',' ' ,' ' ,'\\',' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array('\\','/',' ',' ' ,' ' ,' ' ,' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array('/' ,' ',' ',' ' ,' ' ,' ' ,' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array(' ' ,' ',' ',' ' ,' ' ,' ' ,' ' ,' ',' ' ,' ','\\',' ',' '),
    Array(' ' ,' ',' ',' ' ,'\\','/' ,' ' ,' ',' ' ,' ',' ' ,' ',' '),
    Array(' ' ,' ',' ','/' ,' ' ,' ' ,' ' ,' ',' ' ,' ',' ' ,' ','/'))

  val keyResult = EncryptionKey.makeKey(goodKey)

  behavior of "The CypherMachine object"

  it should "return the string 'Ehidnh' when given the string 'Henrie'" in {
    val testString = "Henrie"
    val testResult = "Ehidnh"
    keyResult match {
      case Right(key) => {
        val result = CypherMachine.processText(testString, key)
        println(s"Plain Text: ${testString} Encoded Text: ${result}")
        assert(result.equals(testResult))
      }
      case Left(error) => {
        println(s"Error: ${error}")
        assert(false)
      }
    }
  }

  it should "return the string 'Henrie' when given the string 'Ehidnh'" in {
    val testString = "Ehidnh"
    val testResult = "Henrie"
    keyResult match {
      case Right(key) => {
        val result = CypherMachine.processText(testString, key)
        println(s"Plain Text: ${testString} Encoded Text: ${result}")
        assert(result.equals(testResult))
      }
      case Left(error) => {
        println(s"Error: ${error}")
        assert(false)
      }
    }
  }

  it should "return the string 'DenW nW C DHyD' when given the string 'This is A TEST'" in {
    val testString = "This is A TEST"
    val testResult = "DenW nW C DHyD"
    keyResult match {
      case Right(key) => {
        val result = CypherMachine.processText(testString, key)
        println(s"Plain Text: ${testString} Encoded Text: ${result}")
        assert(result.equals(testResult))
      }
      case Left(error) => {
        println(s"Error: ${error}")
        assert(false)
      }
    }
  }

  it should "return the string 'This is A TEST' when given the string 'DenW nW C DHyD'" in {
    val testString = "DenW nW C DHyD"
    val testResult = "This is A TEST"
    keyResult match {
      case Right(key) => {
        val result = CypherMachine.processText(testString, key)
        println(s"Plain Text: ${testString} Encoded Text: ${result}")
        assert(result.equals(testResult))
      }
      case Left(error) => {
        println(s"Error: ${error}")
        assert(false)
      }
    }
  }
}
