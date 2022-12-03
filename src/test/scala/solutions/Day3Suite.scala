package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day3Suite extends AnyFunSuite {
  private val realData = "day3.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(file.nonEmpty)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(file.nonEmpty)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(file.nonEmpty)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(file.nonEmpty)
  }
}
