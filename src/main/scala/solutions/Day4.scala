package solutions

case object Day4 {
  def solvePart1(i: Iterator[String]): Int =
    toSetTuples(i)
      .flatMap(isOneASubset)
      .count(_ == true)

  def solvePart2(i: Iterator[String]): Int =
    toSetTuples(i)
      .flatMap(doesOneIntersect)
      .count(_ == true)

  private def toSetTuples(i: Iterator[String]): Iterator[List[Set[Int]]] =
    i
      .flatMap(_.split(","))
      .flatMap(_.split("-"))
      .toList
      .grouped(2)
      .flatMap(makeRangeSets)
      .grouped(2)
      .map(_.toList)

  private def isOneASubset(l: List[Set[Int]]): Option[Boolean] = {
    l.length match {
      case 2 =>
        val (f, s) = (l.take(1).head, l.takeRight(1).head)
        Some((f subsetOf s) || (s subsetOf f))
      case _ => None
    }
  }

  private def doesOneIntersect(l: List[Set[Int]]): Option[Boolean] = {
    l.length match {
      case 2 =>
        val (f, s) = (l.take(1).head, l.takeRight(1).head)
        Some((f intersect s).nonEmpty && (s intersect f).nonEmpty)
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
