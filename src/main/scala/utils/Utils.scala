package utils

import scala.io.Source

case object Utils {
  def readFileAsString(path: String): String =
    Source.fromResource(path).getLines().mkString("\n")
  def readFileAsIterator(path: String): Iterator[String] =
    Source.fromResource(path).getLines()
  def readFileAsList(path: String): List[String] =
    readFileAsString(path).split("\n").toList
}
