package solutions

final case class SignalRecorder(phase: Int, value: Int)
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

  def solvePartTwo(i: List[String]): Int = {
    1
  }

  def injectNoOps(i: List[String]): List[String] = {
    i.foldLeft(List(): List[String])((acc, v) => {
      v match {
        case s if s.startsWith("addx") => s :: "noop" :: acc
        case s => s :: acc
      }
    })
  }
  def isInteresting(i: Int): Boolean =
    i match {
      case 20 | 60 | 100 | 140 | 180 | 220 => true
      case _ => false
    }
}
