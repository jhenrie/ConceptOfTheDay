package conceptoftheday.modules.encoding

/**
  * Created by jhenrie on 11/17/16.
  */
abstract class CodexProcessor(codex: Codex) {
  def write(message: String, filename: String): Either[String, String]
  def read(filename: String): Either[String, String]
}
