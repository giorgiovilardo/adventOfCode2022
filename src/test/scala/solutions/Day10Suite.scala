package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

class Day10Suite extends AnyFunSuite {
  private val realData = "day10.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day10.solvePartOne(file) == 13140)
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day10.solvePartOne(file) == 17180)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    val expected =
      """##..##..##..##..##..##..##..##..##..##..
        |###...###...###...###...###...###...###.
        |####....####....####....####....####....
        |#####.....#####.....#####.....#####.....
        |######......######......######......####
        |#######.......#######.......#######.....""".stripMargin
    assert(Day10.solvePartTwo(file) == expected)
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    val expected =
      """###..####.#..#.###..###..#....#..#.###..
        |#..#.#....#..#.#..#.#..#.#....#..#.#..#.
        |#..#.###..####.#..#.#..#.#....#..#.###..
        |###..#....#..#.###..###..#....#..#.#..#.
        |#.#..#....#..#.#....#.#..#....#..#.#..#.
        |#..#.####.#..#.#....#..#.####..##..###..""".stripMargin
    assert(Day10.solvePartTwo(file) == expected)
  }
}
