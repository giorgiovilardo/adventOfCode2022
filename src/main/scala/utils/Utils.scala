package utils

import scala.io.Source

case object Utils {
  def readFileAsString(path: String): String = Source.fromResource(path).getLines().mkString("\n")
  def readFileAsIterator(path: String): Iterator[String] = Source.fromResource(path).getLines()
}
