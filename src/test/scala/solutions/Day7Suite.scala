package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day7Suite extends AnyFunSuite {
  private val realData = "day7.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day7.solvePartOne(file) == 95437)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day7.solvePartOne(file) == 1086293)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day7.solvePartTwo(file) == 24933642)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day7.solvePartTwo(file) == 366028)
  }
}
