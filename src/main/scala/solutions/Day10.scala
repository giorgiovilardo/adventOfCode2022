package solutions

final case class SignalRecorder(step: Int, value: Int)
final case class SignalAccumulator(total: Int, atPhases: Int)

case object Day10 {
  def solvePartOne(i: List[String]): Int = {
    injectNoOps(i).reverse.zipWithIndex
      .foldLeft(List(): List[SignalRecorder])((acc, v) => {
        v match {
          case (s"addx $n", i) => SignalRecorder(i + 1, n.toInt) :: acc
          case (_, i) => SignalRecorder(i + 1, 0) :: acc
        }
      })
      .reverse
      .foldLeft(SignalAccumulator(1, 0))((acc, v) => {
        v match {
          case SignalRecorder(idx, value) if isInteresting(idx) =>
            acc.copy(
              total = acc.total + value,
              atPhases = acc.atPhases + (idx * acc.total)
            )
          case SignalRecorder(_, value) => acc.copy(total = acc.total + value)
        }
      })
      .atPhases
  }

  def solvePartTwo(i: List[String]): String = {
    val grid = new Grid()
    injectNoOps(i).reverse.zipWithIndex
      .foldLeft(List(): List[SignalRecorder])((acc, v) => {
        v match {
          case (s"addx $n", i) => SignalRecorder(i + 1, n.toInt) :: acc
          case (_, i) => SignalRecorder(i + 1, 0) :: acc
        }
      })
      .reverse
      .foldLeft(grid)((acc, v) => {
        acc.update(v)
      })
      .toString
  }

  private def injectNoOps(i: List[String]): List[String] = {
    i.foldLeft(List(): List[String])((acc, v) => {
      v match {
        case s if s.startsWith("addx") => s :: "noop" :: acc
        case s => s :: acc
      }
    })
  }

  private def isInteresting(i: Int): Boolean =
    i match {
      case 20 | 60 | 100 | 140 | 180 | 220 => true
      case _ => false
    }
}

private class Grid {
  private val cells: Array[Array[Char]] =
    Array.ofDim[Char](6, 40).map(_.map(_ => '.'))
  private var cursor = 1
  def update(s: SignalRecorder): Grid = {
    val SignalRecorder(phase, value) = s
    val currentlyDrawRow = (phase - 1) / 40
    val currentlyDrawCell = (phase - 1) % 40
    val correctRow = cells(currentlyDrawRow)
    val spriteCoverageOnIndexes = correctRow.zipWithIndex
      .slice(
        cursor - 1,
        cursor + 2
      )
      .map(_._2)
    if (spriteCoverageOnIndexes.indexOf(currentlyDrawCell) != -1)
      correctRow(currentlyDrawCell) = '#'
    cursor += value
    this
  }

  override def toString: String =
    cells.map(_.mkString).mkString("\n")
}
