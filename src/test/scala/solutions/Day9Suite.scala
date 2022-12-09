package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day9Suite extends AnyFunSuite {
  private val realData = "day9.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day9.solvePartOne(file) == 13)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day9.solvePartOne(file) == 6522)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day9.solvePartTwo(file) == 1)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day9.solvePartTwo(file) == 2717)
  }
}
