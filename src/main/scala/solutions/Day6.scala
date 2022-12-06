package solutions

case object Day6 {
  def solvePart1(s: String): Int = {
    s
      .sliding(4)
      .map(_.toSet)
      .indexWhere(_.size == 4) + 4
  }
}
