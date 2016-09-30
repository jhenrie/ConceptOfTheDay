package conceptoftheday.modules

/*
*****1
****2 2
***3 3 3
**4 4 4 4
*5 5 5 5 5
6 6 6 6 6 6

val rows = 6

row 1 = 5 spaces then 1
row 2 = 4 spaces then 2s
row 3 = 3 spaces then 3s
row 4 = 2 spaces then 4s
row 5 = 1 space then 5s
row 6 = 6s

Only works to 9!
*/

/**
  * Created by jhenrie on 9/28/16.
  */
object Pyramid {
  def buildPyramid(totalRows: Int): Either[String, List[String]] = {
    if( totalRows > 9 || totalRows < 1) Left("Row count can not be greater than 9 nor less than 1.")
    else Right(List.tabulate(totalRows + 1)( row => buildRow(row, totalRows)))
  }

  private def buildRow(row: Int, rows: Int): String = {
    filler(row, rows) + content(row)
  }

  private def filler(row: Int, totalRow: Int): String = {
    (for ( i <- 0 until totalRow - row ) yield " ").mkString
  }

  private def content(row: Int): String = {
    (for( i <- 0 until row ) yield row.toString + " ").mkString
  }
}
