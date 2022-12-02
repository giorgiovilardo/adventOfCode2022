package day1

import scala.annotation.tailrec

final case class Day1() {
  def topThreeCaloriesFrom(input: Iterator[String]): Int = {
    functionalChunkBy(input)
      .map(_.sum)
      .sorted(Ordering.Int.reverse)
      .take(3)
      .sum
  }

  def mostCaloriesCarriedFrom(input: Iterator[String]): Int = {
    functionalChunkBy(input)
      .map(_.sum)
      .fold(0)((x, y) => if (x >= y) x else y)
  }

  private def functionalChunkBy(iter: Iterator[String]): List[List[Int]] = {
    @tailrec
    def unfolder(
        totalAcc: List[List[Int]],
        chunkAcc: List[Int],
        iter: List[String]
    ): List[List[Int]] = {
      iter match {
        case h :: t if h != "" =>
          unfolder(totalAcc, chunkAcc.appended(h.toInt), t)
        case h :: t if h == "" && chunkAcc.nonEmpty =>
          unfolder(totalAcc.appended(chunkAcc), List(), t)
        case h :: t if h == "" && chunkAcc.isEmpty =>
          unfolder(totalAcc, List(), t)
        case Nil => totalAcc
        case _ => totalAcc
      }
    }
    unfolder(List(), List(), iter.toList)
  }

//  private def chunkByOptions(iter: Iterator[String]): List[List[Int]] = {
//    @SuppressWarnings(Array("org.wartremover.warts.Var"))
//    var window: List[List[Int]] = List()
//    @SuppressWarnings(Array("org.wartremover.warts.Var"))
//    var innerWindow: List[Int] = List()
//    for (item <- iter.map(_.toIntOption)) {
//      item match {
//        case Some(value) => innerWindow = innerWindow.appended(value)
//        case None =>
//          window = window.appended(innerWindow)
//          innerWindow = List()
//      }
//    }
//    window.appended(innerWindow)
//  }
}
