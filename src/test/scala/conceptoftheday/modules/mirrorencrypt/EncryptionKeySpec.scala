package conceptoftheday.modules.mirrorencrypt

import org.scalatest.FlatSpec

class EncryptionKeySpec extends FlatSpec{
  val goodKey: Array[Array[Char]] = Array(
    Array(' ',' ',' ','/',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ','\\',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ','/',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','\\',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '))

  val badRowFormatKey: Array[Array[Char]] = Array(
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '))

  val badColumnFormatKey: Array[Array[Char]] = Array(
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '))

  val badCharKey: Array[Array[Char]] = Array(
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ','\\',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ','/',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ','A',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ','C',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '),
    Array(' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '))

  behavior of "The EncryptionKey object"

  it should "return a Left if the key does not contain 13 rows" in {
    val result = EncryptionKey.loadKey(badRowFormatKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Left if the key does not contain 13 columns" in {
    val result = EncryptionKey.loadKey(badColumnFormatKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Left if the key does not contain valid characters" in {
    val result = EncryptionKey.loadKey(badCharKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Right if the key is properly formatted" in {
    val result = EncryptionKey.loadKey(goodKey)
    result match {
      case Right(key) => assert(true)
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

}
