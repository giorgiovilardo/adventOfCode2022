package solutions

case object Day11 {
  def solvePartOne(i: List[String]): Int = {
    parseInput(i)
    1
  }

  def solvePartTwo(i: List[String]): Int = 1

  def parseInput(i: List[String]) = {
    i
      .filterNot(_ == "")
      .map(_.trim)
      .grouped(6)
      .toList
      .map(_.sorted)
      .map(_.mkString("|"))
      .map(Monkey.fromString)
      .zipWithIndex
      .map { case (monkey, i) => (i, monkey) }
      .toMap
  }

}

final class Monkey(
    id: Int,
    heldItems: Vector[Int],
    operation: Int => Int,
    test: Int => Int
) {
  def addItem(i: Int) = {
    heldItems :+ i
  }
}
case object Monkey {
  def fromString(s: String): Monkey = {
    s match {
      case s"If false: throw to monkey $falseMk|If true: throw to monkey $trueMk|Monkey $id:|Operation: new = old $operator $qty|Starting items: $items|Test: divisible by $divisible" =>
        operator match {
          case "+" =>
            new Monkey(
              id = id.toInt,
              heldItems = items.split(",").map(_.trim).map(_.toInt).toVector,
              operation = i => i + qty.toInt,
              test = {
                case x if x % divisible.toInt != 0 => trueMk.toInt
                case _ => falseMk.toInt
              }
            )
          case "*" =>
            new Monkey(
              id = id.toInt,
              heldItems = items.split(",").map(_.trim).map(_.toInt).toVector,
              operation = i => i * qty.toInt,
              test = {
                case x if x % divisible.toInt != 0 => trueMk.toInt
                case _ => falseMk.toInt
              }
            )
        }
    }
  }
}
