package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day6Suite extends AnyFunSuite {
  private val realData = "day6.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    val testCases = file.zip(List(7, 5, 6, 10, 11))
    for ((input, expected) <- testCases) {
      assert(Day6(input).solvePart1 == expected)
    }
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsString(realData)
    assert(Day6(file).solvePart1 == 1343)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    val testCases = file.zip(List(19, 23, 23, 29, 26))
    for ((input, expected) <- testCases) {
      assert(Day6(input).solvePart2 == expected)
    }
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsString(realData)
    assert(Day6(file).solvePart2 == 2193)
  }
}
