package solutions

private final case class SignalRecorder(step: Int, value: Int)
private final case class SignalAccumulator(total: Int, atPhases: Int)
private object SignalAccumulator {
  def zero: SignalAccumulator = SignalAccumulator(1, 0)
}

case object Day10 {
  def solvePartOne(i: List[String]): Int = {
    prepareInput(i)
      .foldLeft(SignalAccumulator.zero)((acc, signal) => {
        signal match {
          case SignalRecorder(phase, value) if `isPhaseInteresting?`(phase) =>
            acc.copy(
              total = acc.total + value,
              atPhases = acc.atPhases + (phase * acc.total)
            )
          case SignalRecorder(_, value) => acc.copy(total = acc.total + value)
        }
      })
      .atPhases
  }

  def solvePartTwo(i: List[String]): String = {
    val grid = Grid.empty
    prepareInput(i).foldLeft(grid)(_.update(_)).toString
  }

  private def prepareInput(i: List[String]): List[SignalRecorder] = {
    def injectNoOps(l: List[String]): Vector[(String, Int)] =
      l.foldLeft(Vector(): Vector[String])((commands, inputLine) => {
        inputLine match {
          case inputLine if inputLine.startsWith("addx") =>
            commands.appended("noop").appended(inputLine)
          case _ => commands.appended(inputLine)
        }
      }).zipWithIndex

    injectNoOps(i)
      .foldLeft(Vector(): Vector[SignalRecorder])((commands, inputLine) => {
        inputLine match {
          case (s"addx $n", index) =>
            commands.appended(SignalRecorder(index + 1, n.toInt))
          case (_, index) => commands.appended(SignalRecorder(index + 1, 0))
        }
      })
      .toList
  }

  private def `isPhaseInteresting?`(i: Int): Boolean =
    i match {
      case 20 | 60 | 100 | 140 | 180 | 220 => true
      case _ => false
    }
}

final case class Grid(cells: Array[Array[Char]], cursor: Int) {
  def update(s: SignalRecorder): Grid = {
    val SignalRecorder(phase, value) = s
    val currentlyDrawCell = (phase - 1) % 40
    val currentlyDrawRow = cells((phase - 1) / 40)
    val spriteCoverageOnIndexes = currentlyDrawRow.zipWithIndex
      .slice(
        cursor - 1,
        cursor + 2
      )
      .map(_._2)
    if (spriteCoverageOnIndexes.indexOf(currentlyDrawCell) != -1)
      currentlyDrawRow(currentlyDrawCell) = '#'
    Grid(cells, cursor + value)
  }

  override def toString: String =
    cells.map(_.mkString).mkString("\n")
}

object Grid {
  def empty: Grid = Grid(Array.ofDim[Char](6, 40).map(_.map(_ => '.')), 1)
}
