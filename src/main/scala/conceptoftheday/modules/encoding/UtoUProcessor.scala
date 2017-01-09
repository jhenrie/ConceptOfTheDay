package conceptoftheday.modules.encoding

import java.io.{BufferedReader, File, FileReader, PrintWriter}

import scala.io.Source
import scala.util.{Failure, Success, Try}

class UtoUProcessor(utoUCodex: UtoUCodex) extends CodexProcessor(utoUCodex) {
  def write(message: String, filename: String):Either[String, String] = {
    val fd = Try(new File(filename))
    val writer = fd.flatMap( file => Try(new PrintWriter(file)))
    writer match {
      case Success(writer) => {
        utoUCodex.encode(message) match {
          case Right(msg) => {
            val contents = header(filename) + msg + footer()
            writer.write(contents)
            writer.close()
            Right(contents)
          }
          case Left(error) => { Left(error) }
        }
      }
      case Failure(e) => { Left(e.getMessage) }
    }
  }

  def read(filename: String):Either[String, String] = {
    val fileContents = Source.fromFile(filename).getLines().toList
    if (fileContents.isEmpty) {
      Left("File was empty")
    } else {
      utoUCodex.decode(fileContents.slice(1, fileContents.size - 1))
    }
  }

  private def header(filename: String): String = {
    s"begin 644 ${filename}\n"
  }

  private def footer(): String = {
    "end"
  }
}

object UtoUProcessor {
  def write(message: String, filename: String): Either[String, String] = {
    val writer = new UtoUProcessor(new UtoUCodex)
    writer.write(message, filename)
  }

  def read(filename: String): Either[String, String] = {
    val reader = new UtoUProcessor(new UtoUCodex)
    reader.read(filename)
  }
}