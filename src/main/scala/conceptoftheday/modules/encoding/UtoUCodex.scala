package conceptoftheday.modules.encoding

class UtoUCodex extends Codex{
  def encode(input: String): Either[String, String] = {
    if(input.length > 0) {
      Right(encodeBody(input).mkString)
    } else {
      Left("Input provided was of zero length.")
    }
  }

  def decode(input: Seq[String]): Either[String, String] = {
    if(input.size > 0 && !input(0).equals("`")) {
      Right(input.map(x => decodeLine(x)).mkString)
    } else {
      Left("Input provided had nothing to decode")
    }
  }

  private def encodeBody(input: String): List[String] = {
    val bodyBytes = input.getBytes()
    val targetLines = bodyBytes.grouped(45).toList
    targetLines.map(encodeLine(_)) ::: List("`\n")
  }

  private def encodeLine(line: Array[Byte]):String = {
    val lenChar = (line.length + 32).toChar

    val byteGroupList = line.grouped(3).toList
    lenChar.toString + byteGroupList.map(byteGroup => encodeByteGroup(byteGroup)).mkString + "\n"
  }

  private def encodeByteGroup(byteGroup:Array[Byte]): String = {
    //Is there a better way to pad since padTo never worked.
    val paddedGroup = (byteGroup.length) match {
      case 2 => byteGroup ++ Array(0.toByte)
      case 1 => byteGroup ++ Array(0.toByte, 0.toByte)
      case _ => byteGroup
    }

    //Is there a better way?
    val char1 = ((paddedGroup(0) >> 2) + 32).toChar
    val char2 = ((((paddedGroup(0) & 0x3) << 4) | (paddedGroup(1) >> 4)) + 32).toChar
    val char3 = ((((paddedGroup(1) & 0xF) << 2) | (paddedGroup(2) >> 6)) + 32).toChar
    val char4 = ((paddedGroup(2) & 0x3F) + 32).toChar

    char1.toString + char2.toString + char3.toString + char4.toString
  }

  private def decodeLine(input: String) = {
    val noLength = input.getBytes().drop(1)
    noLength.grouped(4).map(group => decodeByteGroup(group)).mkString
  }

  private def decodeByteGroup(group: Array[Byte]):String = {
    ( decodeByte1(group(0), group(1)) ::
      decodeByte2(group(1), group(2)) ::
      decodeByte3(group(2), group(3)) :: Nil ).mkString
  }

  private def decodeByte1(byte1: Byte, byte2: Byte):Char = {
    val test1a = (((byte1 - 32) << 2)).toChar
    val test1b = ((byte2 - 32) >> 4).toChar
    (test1a | test1b).toChar
  }

  private def decodeByte2(byte1: Byte, byte2: Byte):Char = {
    val test2a = (((byte1 - 32) & 0x0F) << 4).toChar
    val test2b = ((byte2 - 32) >> 2).toChar
    (test2a | test2b).toChar
  }

  private def decodeByte3(byte1: Byte, byte2: Byte):Char = {
    val test3a = (((byte1 - 32) & 0x03) << 6).toChar
    val test3b = (byte2 - 32).toChar
    (test3a | test3b).toChar
  }
}

object UtoUCodex {
  def encode(input: String): Either[String, String] = {
    val codex = new UtoUCodex
    codex.encode(input)
  }

  def decode(input: List[String]): Either[String, String] = {
    val codex = new UtoUCodex
    codex.decode(input)
  }
}