package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day6Suite extends AnyFunSuite {
  private val realData = "day6.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    val zipped = file.zip(List(7, 5, 6, 10, 11))
    for ((input, expected) <- zipped) {
      assert(Day6.solvePart1(input) == expected)
    }
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsString(realData)
    assert(Day6.solvePart1(file) == 1343)
  }
//
//  test("Test implementation of part 2 done") {
//    val file = Utils.readFileAsList(testData)
//    assert(Day6.solvePart2(file) == 4)
//  }
//
//  test("Real implementation of part 2 done") {
//    val file = Utils.readFileAsString(realData)
//    assert(Day6.solvePart2(file) == 895)
//  }
}
