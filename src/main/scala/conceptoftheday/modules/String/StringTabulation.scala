package conceptoftheday.modules.String

/**
  * Created by jhenrie on 10/1/16.
  */
object StringTabulation {
  def tabulate(str: String) = {
    val list = (str.toLowerCase).toList
    (list.map(x => (x, list.count(_ == x)))).toMap
  }
}
