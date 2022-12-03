package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day3Suite extends AnyFunSuite {
  private val realData = "day3.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day3.sumNumericPrioritiesOfSharedItems(file) == 157)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day3.sumNumericPrioritiesOfSharedItems(file) == 7967)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day3.sumPrioritiesOfBadges(file) == 70)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day3.sumPrioritiesOfBadges(file) == 2716)
  }
}
