package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day4Suite extends AnyFunSuite {
  private val realData = "day4.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day4.solvePart1(file) == 2)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day4.solvePart1(file) == 580)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(true)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsIterator(realData)
    assert(true)
  }
}
