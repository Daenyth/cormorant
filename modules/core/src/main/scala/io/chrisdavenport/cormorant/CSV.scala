package io.chrisdavenport.cormorant

sealed trait CSV
object CSV {
  final case class Complete(headers: Headers, rows: Rows) extends CSV {
    def stripTrailingRow: Complete = 
      this.copy(rows = rows.stripTrailingRow)
  }
  final case class Rows(rows: List[Row]) extends CSV {
    def stripTrailingRow: Rows = {
      val initial: List[Row] = rows match {
        case Nil => Nil
        case other => other.init
      }
      Rows(initial)
    }
  }

  final case class Headers(l: List[Header]) extends CSV
  final case class Header(value: String) extends CSV

  final case class Row(l: List[Field]) extends CSV
  final case class Field(x: String) extends CSV

}
