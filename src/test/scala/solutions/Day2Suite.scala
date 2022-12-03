package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day2Suite extends AnyFunSuite {
  private val realData = "day2.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day2().part1PointsFrom(file) == 15)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day2().part1PointsFrom(file) == 12772)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day2().part2PointsFrom(file) == 12)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day2().part2PointsFrom(file) == 11618)
  }
}
