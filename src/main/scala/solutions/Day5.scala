package solutions

import scala.collection.mutable

final case class Move(qty: Int, from: Int, to: Int)
case object Move {
  def fromString(s: String): Option[Move] = {
    val movePattern = raw"move (\d+) from (\d+) to (\d+)".r
    s match {
      case movePattern(qty, from, to) =>
        Some(Move(qty.toInt, from.toInt, to.toInt))
      case _ => None
    }
  }
}

case object InputParser {
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

case object Gamestate {
  type Gamestate = List[mutable.Stack[Char]]
  def move(g: Gamestate, m: Move): Gamestate = {
    val Move(times, srcStackNumber, destStackNumber) = m
    val srcStack = g(srcStackNumber - 1)
    val destStack = g(destStackNumber - 1)
    for (_ <- 0 until times) {
      destStack.push(srcStack.pop())
    }
    g
  }

  def extractSolution(g: Gamestate): String = {
    g.map(_.pop().toString).fold("")(_ + _)
  }
}

case object Day5 {
  type Gamestate = List[mutable.Stack[Char]]
  def solvePartOne(i: List[String]): String = {
    val movelist: List[Move] = InputParser.getMoveList(i)
    val startingPosition: Gamestate =
      InputParser.makeStartingPosition(i)
    Gamestate.extractSolution(
      movelist.foldLeft(startingPosition)((state: Gamestate, value: Move) =>
        Gamestate.move(state, value)
      )
    )
  }
}
