package conceptoftheday.modules.mirrorencrypt

/**
  * Created by jhenrie on 2/2/17.
  */
object CypherMachine {

  def processText(text: String, key: EncryptionKey): String = {
    text.map( input => key.encode(input) )
  }
}
