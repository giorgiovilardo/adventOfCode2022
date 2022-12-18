package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day11Suite extends AnyFunSuite {
  private val realData = "day11.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day11.solvePartOne(file) == 1)
  }
//
//  test("Real implementation of part 1 done") {
//    val file = Utils.readFileAsList(realData)
//    assert(Day11.solvePartOne(file) == 1)
//  }
//
//  test("Test implementation of part 2 done") {
//    val file = Utils.readFileAsList(testData)
//    assert(Day11.solvePartTwo(file) == 1)
//  }
//
//  test("Real implementation of part 2 done") {
//    val file = Utils.readFileAsList(realData)
//    assert(Day11.solvePartTwo(file) == 1)
//  }
}
