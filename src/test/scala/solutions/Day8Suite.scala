package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day8Suite extends AnyFunSuite {
  private val realData = "day8.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day8.solvePartOne(file) == 21)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day8.solvePartOne(file) == 1711)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day8.solvePartTwo(file) == 8)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day8.solvePartTwo(file) == 301392)
  }
}
