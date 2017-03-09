package conceptoftheday.modules.mirrorencrypt

/**
  * Created by jhenrie on 1/20/17.
  */

case class EncryptionKey(key: Array[Array[Char]])

object EncryptionKey {
  val header = Array(' ','a','b','c','d','e','f','g','h','i','j','k','l','m',' ')
  val footer = Array(' ','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',' ')
  val alpha: Char = 'A'
  val november: Char = 'n'

  def loadKey(key: Array[Array[Char]]):Either[String, EncryptionKey] = {
    checkFormat(key) match {
      case Right(data) => Right( new EncryptionKey(wrapKey(data)) )
      case Left(error) => Left(error)
    }
  }

  def checkFormat(key: Array[Array[Char]]):Either[String, Array[Array[Char]]] = {
    checkRowCount(key).right.flatMap(checkColumnCount(_)).right.flatMap(checkValidCharacter(_))
  }

  private def checkRowCount(key: Array[Array[Char]]): Either[String, Array[Array[Char]]] = {
    if(key.length != 13) Left("Key does not contain 13 rows.")
    else Right(key)
  }

  private def checkColumnCount(key: Array[Array[Char]]): Either[String, Array[Array[Char]]] = {
    key.map( row => {
      if(row.length != 13) return Left("Row in Key is not 13 columns wide")
    })
    Right(key)
  }

  private def checkValidCharacter(key: Array[Array[Char]]): Either[String, Array[Array[Char]]] = {
    key.map( row => {
      row.map( element => {
        if(element != '\\' && element != '/' && element != ' ')
          return Left(s"Invalid character in key: ${element}")
      })
    })
    Right(key)
  }

  private def wrapKey(key: Array[Array[Char]]): Array[Array[Char]] = {
    val partial = key.zipWithIndex.map { case (element, index) => (alpha + index).toChar +: element :+ (november + index).toChar }
    header +: partial :+ footer
  }
}

