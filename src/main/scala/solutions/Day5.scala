package solutions

import scala.collection.mutable

private final case class Move(qty: Int, from: Int, to: Int)
private case object Move {
  def fromString(s: String): Option[Move] = {
    val movePattern = raw"move (\d+) from (\d+) to (\d+)".r
    s match {
      case movePattern(qty, from, to) =>
        Some(Move(qty.toInt, from.toInt, to.toInt))
      case _ => None
    }
  }
}

private case object InputParser {
  def getMoveList(i: List[String]): List[Move] =
    i.filter(_.startsWith("move")).flatMap(Move.fromString)

  def makeStartingPosition(i: List[String]): List[mutable.Stack[Char]] = {
    val filteredList =
      i.filterNot(_.startsWith("move")).filterNot(_ == "")

    val padLength =
      filteredList.map(_.length).fold(0)((a, v) => if (a > v) a else v)

    filteredList
      .map(_.padTo(padLength, ' '))
      .transpose
      .flatMap(isUselessColumn)
      .map(_.dropRight(1))
      .map(_.filterNot(_ == ' '))
      .map(_.reverse)
      .map(new mutable.Stack().pushAll(_))
  }

  private def isUselessColumn(l: List[Char]): Option[List[Char]] = {
    l match {
      case x if x.contains('[') => None
      case x if x.contains(']') => None
      case x if x.forall(_ == ' ') => None
      case _ => Some(l)
    }
  }
}

private case object Gamestate {
  type Gamestate = List[mutable.Stack[Char]]
  def moveOneAtATime(g: Gamestate, m: Move): Gamestate = {
    val srcStack = g(m.from - 1)
    val destStack = g(m.to - 1)
    for (_ <- 0 until m.qty) {
      destStack.push(srcStack.pop())
    }
    g
  }

  def moveAllTogether(g: Gamestate, m: Move): Gamestate = {
    val srcStack = g(m.from - 1)
    val destStack = g(m.to - 1)
    val allBlocksTogether = List.unfold((0, srcStack)) {
      case (i, s) if i < m.qty => Some((s.pop(), (i + 1, s)))
      case _ => None
    }
    val _ = destStack.pushAll(allBlocksTogether.reverse)
    g
  }

  def extractSolution(g: Gamestate): String = {
    g.map(_.pop().toString).fold("")(_ + _)
  }
}

case object Day5 {
  type Gamestate = List[mutable.Stack[Char]]
  def solvePartOne(i: List[String]): String = {
    solveGeneric(i)(Gamestate.moveOneAtATime)
  }

  def solvePartTwo(i: List[String]): String = {
    solveGeneric(i)(Gamestate.moveAllTogether)
  }

  private def solveGeneric(
      i: List[String]
  )(f: (Gamestate, Move) => Gamestate): String = {
    val (movelist, startingPosition) = getMovesAndGamestate(i)
    Gamestate.extractSolution(
      movelist.foldLeft(startingPosition)((state, value) => f(state, value))
    )
  }

  private def getMovesAndGamestate(i: List[String]): (List[Move], Gamestate) =
    (InputParser.getMoveList(i), InputParser.makeStartingPosition(i))
}
