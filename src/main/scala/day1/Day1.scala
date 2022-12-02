package day1

final case class Day1() {
  def topThreeCaloriesFrom(input: Iterator[String]): Int = {
    chunkByOptions(input)
      .map(_.sum)
      .sorted(Ordering.Int.reverse)
      .take(3)
      .sum
  }

  def mostCaloriesCarriedFrom(input: Iterator[String]): Int = {
    chunkByOptions(input)
      .map(_.sum)
      .fold(0)((x, y) => if (x >= y) x else y)
  }

  private def chunkByOptions(iter: Iterator[String]): List[List[Int]] = {
    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    var window: List[List[Int]] = List()
    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    var innerWindow: List[Int] = List()
    for (item <- iter.map(_.toIntOption)) {
      item match {
        case Some(value) => innerWindow = innerWindow.appended(value)
        case None =>
          window = window.appended(innerWindow)
          innerWindow = List()
      }
    }
    window.appended(innerWindow)
  }
}
