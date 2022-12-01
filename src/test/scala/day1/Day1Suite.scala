package day1

import org.scalatest.funsuite.AnyFunSuite

final class Day1Suite extends AnyFunSuite {
  test("dwarf method should output `I Nani`") {
    assert(Day1().dwarf == "I nani")
  }
}
