package solutions

case object Day8 {
  def solvePartOne(i: List[String]): Int = {
    visibilityPerRow(toIntInput(i))
      .zip(
        visibilityPerRow(toIntInput(i.transpose.map(_.mkString))).transpose
      )
      .map(i => i._1.zip(i._2))
      .flatMap(_.map(i => i._1 || i._2))
      .map(if (_) 1 else 0)
      .sum
  }

  def solvePartTwo(i: List[String]): Int = {
    scenicityPerRow2(toIntInput(i))
      .zip(
        scenicityPerRow2(toIntInput(i.transpose.map(_.mkString))).transpose
      )
      .map(i => i._1.zip(i._2))
      .flatMap(i => i.map(k => k._1 * k._2))
      .max
  }

  private def toIntInput(l: List[String]): List[List[Int]] =
    l.map(_.split("").map(_.toInt).toList)

  private type SetLoupe = (Set[Int], Int, Set[Int])
  private type ListLoupe = (List[Int], Int, List[Int])

  private def makeSetLoupeAt(s: List[Int], index: Int): SetLoupe = {
    s.splitAt(index) match {
      case (h, h2 :: t) => (h.toSet, h2, t.toSet)
      case (h, Nil) => (h.toSet, 0, Set())
    }
  }

  private def makeListLoupeAt(s: List[Int], index: Int): ListLoupe = {
    s.splitAt(index) match {
      case (h, h2 :: t) => (h, h2, t)
      case (h, Nil) => (h, 0, List())
    }
  }

  private def safeMax(l: Set[Int]): Int =
    l.fold(0)((init, v) => if (init >= v) init else v)

  private def visibilityPerRow(l: List[List[Int]]): List[List[Boolean]] = {

    l.map(inner => {
      inner.zipWithIndex
        .map { case (_, i) => makeSetLoupeAt(inner, i) }
        .map {
          case (l, _, _) if l.isEmpty => true
          case (_, _, r) if r.isEmpty => true
          case (l, n, r) => safeMax(l) < n || safeMax(r) < n
        }
    })
  }

  def scenicityPerRow2(l: List[List[Int]]): List[List[Int]] = {

    def loupeReducer(t: ListLoupe): Int = {
      final case class Distance(
          highestSeen: Int = 0,
          distance: Int = 0,
          ended: Boolean = false
      )

      def folder(value: Int, distance: Distance) = {
        distance match {
          case Distance(_, _, ended) if ended => distance
          case _ =>
            value match {
              case x if x >= distance.highestSeen =>
                distance.copy(distance = distance.distance + 1, ended = true)
              case _ => distance.copy(distance = distance.distance + 1)
            }
        }
      }

      t match {
        case (l, _, _) if l.isEmpty => 0
        case (_, _, r) if r.isEmpty => 0
        case (l, n, r) =>
          val z = l
            .foldRight(Distance(highestSeen = n))((value, maxDistance) =>
              folder(value, maxDistance)
            )
            .distance
          val zz = r
            .foldLeft(Distance(highestSeen = n))((maxDistance, value) =>
              folder(value, maxDistance)
            )
            .distance
          zz * z
      }
    }

    l.map(inner => {
      inner.zipWithIndex
        .map { case (_, i) => makeListLoupeAt(inner, i) }
        .map(loupeReducer)
    })
  }
}
