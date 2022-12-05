package solutions

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day5Suite extends AnyFunSuite {
  private val realData = "day5.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day5.solvePartOne(file) == "CMZ")
  }

  test("Real implementation of part 1 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day5.solvePartOne(file) == "VCTFTJQCG")
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsList(testData)
    assert(Day5.solvePartTwo(file) == "MCD")
  }

  test("Real implementation of part 2 done") {
    val file = Utils.readFileAsList(realData)
    assert(Day5.solvePartTwo(file) == "GCFGLDNJZ")
  }
}

final class MoveSuite extends AnyFunSuite {
  private val correctString = "move 1 from 2 to 1"
  private val badString = "move 12 from a to"

  test("Should convert a correct move string") {
    assert(Move.fromString(correctString).contains(Move(1, 2, 1)))
  }

  test("Should convert a correct move string with big numbers") {
    assert(Move.fromString("move 123 from 34 to 5").contains(Move(123, 34, 5)))
  }

  test("Should not convert a bad move string") {
    assert(Move.fromString(badString).isEmpty)
  }
}

final class InputParserSuite extends AnyFunSuite {
  private val correctMoveList = List(
    "move 1 from 2 to 1",
    "move 121 from 4 to 7",
    "move 999 from 4 to 55"
  )
  private val badMoveList = correctMoveList.appended("move a from 123 from 3")

  test("Should convert a correct move list") {
    val expected = List(
      Move(1, 2, 1),
      Move(121, 4, 7),
      Move(999, 4, 55)
    )
    assert(InputParser.getMoveList(correctMoveList) == expected)
  }

  test("Should skip bad values") {
    val startingLength = 4
    val expectedLength = 3
    assert(badMoveList.length == startingLength)
    assert(
      InputParser.getMoveList(badMoveList).length == expectedLength
    )
  }

}
