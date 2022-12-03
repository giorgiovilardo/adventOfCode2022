package solutions

final case class Day3() {}
object Day3 {

  private def charToPointValue(c: Char): Option[Int] = {
    (('a' to 'z').zip(1 to 26).toMap ++
      ('A' to 'Z').zip(27 to 52).toMap).get(c)
  }

  /** Solver for AoC2022 day 3 part 1. Implemented in the companion object.
    *
    * @param i
    *   a string iterator.
    * @return
    *   an Int with the value requested.
    */
  def sumNumericPrioritiesOfSharedItems(i: Iterator[String]): Int = {
    i
      .map(splitInventoryStringInTuples)
      .flatMap(i => i._1.toSet intersect i._2.toSet)
      .flatMap(charToPointValue)
      .sum
  }

  /** Splits an Inventory string (e.g. vJrwpWtwJgWrhcsFMMfFFhFp) into a tuple
    * which contains the two halves of it. This is also a ScalaDoc test.
    *
    * @param s
    *   An Inventory string
    * @return
    *   a tuple with the two halves of the string
    */
  private def splitInventoryStringInTuples(s: String): (String, String) =
    s.splitAt(s.length / 2)

}
