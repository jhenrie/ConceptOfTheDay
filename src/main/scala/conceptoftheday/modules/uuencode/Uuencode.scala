package conceptoftheday.modules.uuencode

import java.io.{File, FileWriter}


/**
  * Created by jhenrie on 10/18/16.
  */
object Uuencode {
  def encode(input: Option[String], file: Option[String]): Option[String] = {
    input match {
      case Some(text) => {
        file match {
          case Some(targetFile) => {
            val encoding = (List(header(targetFile)) ::: body(text) ::: List(footer())).mkString
            val fd = new File(targetFile)
            fd.createNewFile()

            val writer = new FileWriter(fd)

            writer.write(encoding)
            writer.flush()
            writer.close()

            Some(encoding)
          }
          case None => None
        }
      }
      case None => None
    }
  }

  private def header(file: String): String = {
    s"begin 644 ${file}\n"
  }

  private def body(input: String): List[String] = {
    val inputBytes = input.getBytes()
    val targetLines = inputBytes.grouped(45).toList
    targetLines.map(encodeLine(_)) ::: List("`\n")

  }

  private def footer(): String = {
    "end"
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

    buildChar1(paddedGroup(0)).toString + buildChar2(paddedGroup(0), paddedGroup(1)).toString +
      buildChar3(paddedGroup(1), paddedGroup(2)).toString + buildChar4(paddedGroup(2)).toString
  }

  private def buildChar1(byte: Byte): Char = {
    ((byte >> 2) + 32).toChar
  }

  private def buildChar2(byte1: Byte, byte2: Byte): Char = {
    ((((byte1 & 0x3) << 4) | (byte2 >> 4)) + 32).toChar
  }

  private def buildChar3(byte1: Byte, byte2: Byte): Char = {
    ((((byte1 & 0xF) << 2) | (byte2 >> 6)) + 32).toChar
  }

  private def buildChar4(byte: Byte): Char = {
    ((byte & 0x3F) + 32).toChar
  }
}
