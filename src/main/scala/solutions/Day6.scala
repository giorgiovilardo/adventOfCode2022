package solutions

final case class Day6(input: String) {
  def solvePart1: Int = solver(4)

  def solvePart2: Int = solver(14)

  private def solver: Int => Int = (i: Int) => {
    input.sliding(i).map(_.toSet).indexWhere(_.size == i) + i
  }
}
