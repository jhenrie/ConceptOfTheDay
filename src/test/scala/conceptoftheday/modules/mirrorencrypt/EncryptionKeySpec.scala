package conceptoftheday.modules.mirrorencrypt

import org.scalatest.FlatSpec

class EncryptionKeySpec extends FlatSpec{
  val goodKey: Array[Array[Char]] = Array(
    Array('\\',' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,'/'),
    Array(' ' ,'/',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ','\\',' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ','/' ,' ' ,' ','\\',' ',' ',' ',' ' ,' '),
    Array(' ' ,' ','/' ,' ',' ' ,'\\',' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ','/' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ','\\',' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ','/' ,' ',' ' ,'\\',' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '),
    Array(' ' ,' ',' ' ,' ',' ' ,' ' ,' ',' ' ,' ',' ',' ',' ' ,' '))

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
    val result = EncryptionKey.makeKey(badRowFormatKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Left if the key does not contain 13 columns" in {
    val result = EncryptionKey.makeKey(badColumnFormatKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Left if the key does not contain valid characters" in {
    val result = EncryptionKey.makeKey(badCharKey)
    result match {
      case Right(key) => assert(false)
      case Left(error) => {
        println(error)
        assert(true)
      }
    }
  }

  it should "return a Right if the key is properly formatted" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => assert(true)
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'N' from 'A' using single \\ (DOWN)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('A')
        assert(check == 'N')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'b' from 'B' using single / (LEFT, UP)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('B')
        assert(check == 'b')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'Z' from 'n' using single / (DOWN)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('n')
        assert(check == 'Z')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'l' from 'o' using single \\ (LEFT, DOWN)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('o')
        assert(check == 'l')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'x' from 'K' using a box turn" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('K')
        assert(check == 'x')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'v' from 'H' using a mirror loop" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('H')
        assert(check == 'v')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return ' ' from ' ' " in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode(' ')
        assert(check == ' ')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  //******************************************************
  // Decode
  //******************************************************
  it should "return 'A' from 'N' using single \\ (DOWN)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('N')
        assert(check == 'A')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'B' from 'b' using single / (DOWN, LEFT)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('B')
        assert(check == 'b')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'n' from 'Z' using single / (UP)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('Z')
        assert(check == 'n')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'o' from 'l' using single \\ (DOWN, RIGHT)" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('l')
        assert(check == 'o')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'K' from 'x' using the following " in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('x')
        assert(check == 'K')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }

  it should "return 'H' from 'v' using a mirror loop" in {
    val result = EncryptionKey.makeKey(goodKey)
    result match {
      case Right(key) => {
        val check = key.encode('v')
        assert(check == 'H')
      }
      case Left(error) => {
        println(error)
        assert(false)
      }
    }
  }
}
