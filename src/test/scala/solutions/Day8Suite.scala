package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day8Suite extends AnyFunSuite {
  private val realData = "day8.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day8.solvePartOne(file))
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day8.solvePartOne(file))
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day8.solvePartTwo(file))
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day8.solvePartTwo(file))
  }
}
