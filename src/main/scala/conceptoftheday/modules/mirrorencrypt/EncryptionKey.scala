package conceptoftheday.modules.mirrorencrypt

/**
  * Created by jhenrie on 1/20/17.
  */

case class EncryptionKey private(key: Array[Array[Char]]) {
  def encode(character: Char): Char = {
    val (startPoint, direction) = startLocation(character)
    val point = traverseGrid(startPoint + direction, direction)
    getKeyCell(point)
  }

  def mkString(sep: String = "|"): String = {
    (key.map( row => row.mkString(sep) )).mkString("\n")
  }

  def getKeyCell(point: GridPoint): Char = {
    key(point.x)(point.y)
  }

  private val UP = GridPoint(-1,0)
  private val DOWN = GridPoint(1,0)
  private val RIGHT = GridPoint(0,1)
  private val LEFT = GridPoint(0, -1)

  private def startLocation(character: Char): (GridPoint, GridPoint) = {
    character match {
      case c if c >= 'A' && c <= 'M' => (GridPoint(c - 'A' + 1, 0), RIGHT)
      case c if c >= 'N' && c <= 'Z' => (GridPoint(14, c - 'N' + 1), UP)
      case c if c >= 'a' && c <= 'm' => (GridPoint(0, c - 'a' + 1), DOWN)
      case c if c >= 'n' && c <= 'z' => (GridPoint(c - 'n' + 1, 14), LEFT)
      case _ => (GridPoint(0,0), GridPoint(0,0))
    }
  }

  private def traverseGrid(point: GridPoint, direction: GridPoint):GridPoint = {
    point match {
      case point if point.isEdge() => point
      case point if getKeyCell(point) == ' ' => traverseGrid(point + direction, direction)
      case point if getKeyCell(point) == '\\' => {
        direction match {
          case UP => traverseGrid(point + LEFT, LEFT)
          case DOWN => traverseGrid(point + RIGHT, RIGHT)
          case RIGHT => traverseGrid(point + DOWN, DOWN)
          case LEFT => traverseGrid(point + UP, UP)
        }
      }
      case point if getKeyCell(point) == '/' => {
        direction match {
          case UP => traverseGrid(point + RIGHT, RIGHT)
          case DOWN => traverseGrid(point + LEFT, LEFT)
          case RIGHT => traverseGrid(point + UP, UP)
          case LEFT => traverseGrid(point + DOWN, DOWN)
        }
      }
      case _ => new GridPoint(0,0)
    }
  }
}

object EncryptionKey {
  val header = Array(' ','a','b','c','d','e','f','g','h','i','j','k','l','m',' ')
  val footer = Array(' ','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',' ')
  val alpha: Char = 'A'
  val november: Char = 'n'

  def makeKey(key: Array[Array[Char]]):Either[String, EncryptionKey] = {
    checkFormat(key) match {
      case Right(data) => Right( new EncryptionKey(wrapKey(data)) )
      case Left(error) => Left(error)
    }
  }

  private def checkFormat(key: Array[Array[Char]]):Either[String, Array[Array[Char]]] = {
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

