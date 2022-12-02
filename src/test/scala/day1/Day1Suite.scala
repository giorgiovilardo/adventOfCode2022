package day1

import org.scalatest.funsuite.AnyFunSuite
import utils.Utils

final class Day1Suite extends AnyFunSuite {
  private val realData = "day1.txt"
  private val testData = "test_" + realData

  test("Test implementation of part 1 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day1().mostCaloriesCarriedFrom(file) == 15)
  }

  test("Real data of part 1 outputs...") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day1().mostCaloriesCarriedFrom(file) == 69883)
  }

  test("Test implementation of part 2 done") {
    val file = Utils.readFileAsIterator(testData)
    assert(Day1().topThreeCaloriesFrom(file) == 34)
  }

  test("Real data of part 2 outputs...") {
    val file = Utils.readFileAsIterator(realData)
    assert(Day1().topThreeCaloriesFrom(file) == 207576)
  }
}
