package conceptoftheday.modules.encoding


abstract class Codex {
  def encode(input: String): Either[String, String]
  def decode(input: Seq[String]): Either[String, String]
}
