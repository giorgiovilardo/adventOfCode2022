package solutions

case object Day9 {
  def solvePartOne(f: List[String]): Int = {
    val moves = D9Move.fromInputLines(f)
    val origin = Position(0, 0)
    val (head, tail) = (Head(origin), new Tail(origin))
    moves.foldLeft((head, tail))((ht, m) => {
      val headmove = ht._1.move(m)
      val tailmove = ht._2.moveAfter(headmove.position)
      (headmove, tailmove)
    })
    tail.visitedPositions.size
  }
  def solvePartTwo(f: List[String]): Int = {
    val moves = D9Move.fromInputLines(f)
    val origin = Position(0, 0)
    val snake: List[Snake] = List(
      Head(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin),
      new Tail(origin)
    )
    moves.foldLeft(snake)((l, m) => {
      val headmove = l.head.move(m)
      val tailmove1 = l(1).moveAfter(headmove.position)
      val tailmove2 = l(2).moveAfter(tailmove1.position)
      val tailmove3 = l(3).moveAfter(tailmove2.position)
      val tailmove4 = l(4).moveAfter(tailmove3.position)
      val tailmove5 = l(5).moveAfter(tailmove4.position)
      val tailmove6 = l(6).moveAfter(tailmove5.position)
      val tailmove7 = l(7).moveAfter(tailmove6.position)
      val tailmove8 = l(8).moveAfter(tailmove7.position)
      val tailmove9 = l(9).moveAfter(tailmove8.position)
      List(
        headmove,
        tailmove1,
        tailmove2,
        tailmove3,
        tailmove4,
        tailmove5,
        tailmove6,
        tailmove7,
        tailmove8,
        tailmove9
      )
    })
    snake(9).getVisitedPositions.size
  }
}

sealed trait D9Move
case object D9Move {
  def fromInputLines(i: List[String]): List[D9Move] = {
    i.map { case s"$dir $n" => dir * n.toInt }
      .mkString
      .map(fromChar)
      .toList
  }

  private def fromChar(c: Char): D9Move =
    c match {
      case 'R' => R()
      case 'L' => L()
      case 'D' => D()
      case 'U' => U()
    }

  def fromHeadTailPosition(h: Position, t: Position): D9Move =
    (h, t) match {
      case (Position(hx, hy), Position(tx, ty))
          if hx - tx == 2 && hy - ty == 0 =>
        R()
      case (Position(hx, hy), Position(tx, ty))
          if hx - tx == -2 && hy - ty == 0 =>
        L()
      case (Position(hx, hy), Position(tx, ty))
          if hx - tx == 0 && hy - ty == 2 =>
        U()
      case (Position(hx, hy), Position(tx, ty))
          if hx - tx == 0 && hy - ty == -2 =>
        D()
      case (Position(hx, hy), Position(tx, ty))
          if (hx - tx == 1 && hy - ty == 2) ||
            (hx - tx == 2 && hy - ty == 2) ||
            (hx - tx == 2 && hy - ty == 1) =>
        UR()
      case (Position(hx, hy), Position(tx, ty))
          if (hx - tx == -1 && hy - ty == 2) ||
            (hx - tx == -2 && hy - ty == 2) ||
            (hx - tx == -2 && hy - ty == 1) =>
        UL()
      case (Position(hx, hy), Position(tx, ty))
          if (hx - tx == 1 && hy - ty == -2) ||
            (hx - tx == 2 && hy - ty == -2) ||
            (hx - tx == 2 && hy - ty == -1) =>
        DR()
      case (Position(hx, hy), Position(tx, ty))
          if (hx - tx == -1 && hy - ty == -2) ||
            (hx - tx == -2 && hy - ty == -2) ||
            (hx - tx == -2 && hy - ty == -1) =>
        DL()
      case _ => Stop()
    }
}

final case class R() extends D9Move
final case class L() extends D9Move
final case class U() extends D9Move
final case class D() extends D9Move
final case class UL() extends D9Move
final case class UR() extends D9Move
final case class DL() extends D9Move
final case class DR() extends D9Move
final case class Stop() extends D9Move

final case class Position(x: Int, y: Int)

trait Snake {
  def position: Position
  def move(m: D9Move): Snake
  def moveAfter(p: Position): Snake
  def getVisitedPositions: Set[Position]
}

final case class Head(position: Position) extends Snake {
  def move(m: D9Move): Head = m match {
    case R() => this.copy(position = Position(position.x + 1, position.y))
    case L() => this.copy(position = Position(position.x - 1, position.y))
    case U() => this.copy(position = Position(position.x, position.y + 1))
    case D() => this.copy(position = Position(position.x, position.y - 1))
  }

  override def moveAfter(p: Position): Snake = this

  override def getVisitedPositions: Set[Position] = Set(position)
}

class Tail(var position: Position) extends Snake {
  var visitedPositions: Set[Position] = Set(position)
  def moveAfter(p: Position): Tail = {
    val move = D9Move.fromHeadTailPosition(p, position)
    move match {
      case R() =>
        this.position = this.position.copy(x = this.position.x + 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case L() =>
        this.position = this.position.copy(x = this.position.x - 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case U() =>
        this.position = this.position.copy(y = this.position.y + 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case D() =>
        this.position = this.position.copy(y = this.position.y - 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case UL() =>
        this.position =
          this.position.copy(x = this.position.x - 1, y = this.position.y + 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case UR() =>
        this.position =
          this.position.copy(x = this.position.x + 1, y = this.position.y + 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case DL() =>
        this.position =
          this.position.copy(x = this.position.x - 1, y = this.position.y - 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case DR() =>
        this.position =
          this.position.copy(x = this.position.x + 1, y = this.position.y - 1)
        this.visitedPositions = this.visitedPositions + this.position
        this
      case Stop() => this
    }
  }

  override def move(m: D9Move): Snake = this

  override def getVisitedPositions: Set[Position] = visitedPositions
}
