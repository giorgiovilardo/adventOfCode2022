package solutions

case object Day4 {
  def solvePart1(i: Iterator[String]): Int =
    i
      .flatMap(_.split(","))
      .flatMap(_.split("-"))
      .toList
      .grouped(2)
      .flatMap(makeRangeSets)
      .grouped(2)
      .map(_.toList)
      .flatMap(isOneASubset)
      .count(_ == true)

  private def isOneASubset(l: List[Set[Int]]): Option[Boolean] = {
    l.length match {
      case 2 =>
        val (f, s) = (l.take(1).head, l.takeRight(1).head)
        Some((f subsetOf s) || (s subsetOf f))
      case _ => None
    }
  }

  private def makeRangeSets(l: List[String]): Option[Set[Int]] = {
    l.length match {
      case 2 => Some((l.take(1).head.toInt to l.takeRight(1).head.toInt).toSet)
      case _ => None
    }
  }
}
