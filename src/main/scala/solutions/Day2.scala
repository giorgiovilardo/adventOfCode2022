package solutions

sealed trait RPSOutcome {
  def toPointValue: Int = this match {
    case Win() => 6
    case Draw() => 3
    case Loss() => 0
  }
}
object RPSOutcome {
  def fromString(s: String): Option[RPSOutcome] = s match {
    case "X" => Some(Loss())
    case "Y" => Some(Draw())
    case "Z" => Some(Win())
    case _ => None
  }
}
final case class Win() extends RPSOutcome
final case class Loss() extends RPSOutcome
final case class Draw() extends RPSOutcome

sealed trait RPSMove {
  def toPointValue: Int = this match {
    case Rock() => 1
    case Paper() => 2
    case Scissor() => 3
  }

}
object RPSMove {
  def fromString(s: String): Option[RPSMove] = s match {
    case "A" | "X" => Some(Rock())
    case "B" | "Y" => Some(Paper())
    case "C" | "Z" => Some(Scissor())
    case _ => None
  }

  def calculateWinner(m1: RPSMove, m2: RPSMove): RPSOutcome = (m2, m1) match {
    case (Rock(), Rock()) => Draw()
    case (Rock(), Paper()) => Loss()
    case (Rock(), Scissor()) => Win()
    case (Paper(), Rock()) => Win()
    case (Paper(), Paper()) => Draw()
    case (Paper(), Scissor()) => Loss()
    case (Scissor(), Rock()) => Loss()
    case (Scissor(), Paper()) => Win()
    case (Scissor(), Scissor()) => Draw()
  }

  def calculateMoveFromOutcome(m: RPSMove, o: RPSOutcome): RPSMove =
    (m, o) match {
      case (Rock(), Win()) => Paper()
      case (Rock(), Draw()) => Rock()
      case (Rock(), Loss()) => Scissor()
      case (Paper(), Win()) => Scissor()
      case (Paper(), Draw()) => Paper()
      case (Paper(), Loss()) => Rock()
      case (Scissor(), Win()) => Rock()
      case (Scissor(), Draw()) => Scissor()
      case (Scissor(), Loss()) => Paper()
    }
}

final case class Rock() extends RPSMove {}
final case class Paper() extends RPSMove {}
final case class Scissor() extends RPSMove {}

final case class Day2() {
  def part1PointsFrom(file: Iterator[String]): Int = {
    val rounds = extractRounds(file)

    val pointsForScores = rounds
      .map(i => RPSMove.calculateWinner(i._1, i._2))
      .map(_.toPointValue)
      .sum

    val pointsForMoves = rounds.map(_._2).map(_.toPointValue).sum

    pointsForScores + pointsForMoves
  }

  def part2PointsFrom(file: Iterator[String]): Int = {
    val (moves, outcomes) = {
      val (m, o) = file.mkString
        .replace(" ", "")
        .grouped(2)
        .map(_.splitAt(1))
        .toList
        .unzip(i => (RPSMove.fromString(i._1), RPSOutcome.fromString(i._2)))
      (m.flatten, o.flatten)
    }

    val myMoves = moves
      .zip(outcomes)
      .map(i => RPSMove.calculateMoveFromOutcome(i._1, i._2))

    myMoves.map(_.toPointValue).sum + outcomes.map(_.toPointValue).sum
  }

  private def extractRounds(
      i: Iterator[String]
  ): List[(RPSMove, RPSMove)] = {
    i.mkString
      .flatMap(c => RPSMove.fromString(c.toString))
      .toList
      .grouped(2)
      .flatMap {
        case List(a, b) => Some((a, b))
        case _ => None
      }
      .toList
  }
}
