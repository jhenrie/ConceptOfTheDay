package conceptoftheday.modules

/**
  * Created by jhenrie on 9/27/16.
  */
object ReverseString {
  def reverse(str: String): String = {
    (for(i <- str.length - 1 to 0 by -1) yield str(i)).mkString
  }

  def recursiveMethod(str: String): String = {
    if(str == null || str.length <= 1) str
    else recursiveMethod(str.substring(1)) + str(0)
  }
}
