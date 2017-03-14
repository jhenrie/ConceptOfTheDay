package conceptoftheday.modules.mirrorencrypt

/**
  * Created by jhenrie on 3/10/17.
  */
case class GridPoint(x:Int, y:Int) {
  def +(point: GridPoint):GridPoint = {
    val dx = x + point.x
    val dy = y + point.y
    new GridPoint(dx, dy)
  }

  def isEdge(): Boolean = {
    if (x == 0 || y == 0 || x == 14 || y == 14) true
    else false
  }

  def mkString(sep: String = ","): String = {
    x.toString + sep + y.toString()
  }
}