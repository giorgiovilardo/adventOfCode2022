package utils

import org.scalatest.funsuite.AnyFunSuite

class UtilsSuite extends AnyFunSuite {
  val filename = "testfile.txt"
  test("readFileAsString should give back file content in string") {
    assert(Utils.readFileAsString(filename) == "line1\nline2\nline3\nline4")
  }
}
