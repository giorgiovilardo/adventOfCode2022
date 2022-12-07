package solutions

import scala.annotation.tailrec
import scala.collection.mutable

sealed trait Command
final case class Ls() extends Command
final case class Cd(to: String) extends Command

sealed trait Output
final case class Cmd(c: Command) extends Output
final case class Directory(name: String) extends Output
final case class File(name: String, size: Int) extends Output

case object TerminalParser {
  def parse(i: List[String]): List[Output] = {
    i.map {
      case s"$$ cd $dir" => Cmd(Cd(dir))
      case s"$$ ls" => Cmd(Ls())
      case s"dir $dir" => Directory(dir)
      case s"$size $name" => File(name, size.toInt)
    }
  }
}

class FSTree(
    val dir: String,
    val children: mutable.Map[String, FSTree],
    val files: mutable.Map[String, Int],
    val parent: FSTree
) {
  override def toString: String = s"FS<$dir, $children, $files>"
}
object FSTree {
  @tailrec
  def buildFullPath(
      input: List[Output],
      dir: FSTree,
      root: FSTree
  ): Unit = {
    input match {
      case Cmd(Cd("/")) :: t => FSTree.buildFullPath(t, root, root)
      case Cmd(Cd("..")) :: t => FSTree.buildFullPath(t, dir.parent, root)
      case Cmd(Cd(dirName)) :: t =>
        FSTree.buildFullPath(t, dir.children(dirName), root)
      case Cmd(Ls()) :: t => FSTree.buildFullPath(t, dir, root)
      case File(name, size) :: t =>
        dir.files.put(name, size)
        FSTree.buildFullPath(t, dir, root)
      case Directory(name) :: t =>
        dir.children.put(
          name,
          new FSTree(name, mutable.Map.empty, mutable.Map.empty, dir)
        )
        FSTree.buildFullPath(t, dir, root)
      case Nil => ()
    }
  }

  def sizeOfDir(dir: FSTree): Int =
    dir.files.values.sum + dir.children.values.map(sizeOfDir).sum

  def listSizes(dir: FSTree, by: Int => Boolean): List[Int] = {
    val thisDirSize = sizeOfDir(dir)
    val children = dir.children.values.flatMap(listSizes(_, by))
    if (by(thisDirSize)) thisDirSize :: children.toList else children.toList
  }
}

case object Day7 {
  def solvePartTwo(i: List[String]): Int = {

    val state = getState(i)
    val totalUsed = FSTree.sizeOfDir(state)
    val totalUnused = 70_000_000 - totalUsed
    val required = 30_000_000 - totalUnused
    FSTree.listSizes(state, _ >= required).min
  }

  def solvePartOne(i: List[String]): Int = {
    FSTree.listSizes(getState(i), _ < 100000).sum
  }

  def getState(i: List[String]): FSTree = {
    val startingPosition =
      new FSTree("/", mutable.Map.empty, mutable.Map.empty, null)
    FSTree.buildFullPath(
      TerminalParser.parse(i),
      startingPosition,
      startingPosition
    )
    startingPosition
  }
}

//final case class Path(path: String, size: Int = 0)
//
//case object Day7 {
//  private def traversePaths(pathList: List[Path], s: String): List[Path] = {
//    val fileParser = raw"(\d+) (.*)".r
//    val dirParser = raw".* cd (.*)".r
//    s match {
//      case s if s == "$ cd /" => Path("/") :: pathList
//      case s if s == "$ ls" => pathList
//      case fileParser(size, _) =>
//        pathList match {
//          case h :: t => Path(h.path, h.size + size.toInt) :: t
//          case _ => pathList
//        }
//      case s if s == "$ cd .." =>
//        pathList match {
//          case h :: new_h :: t => new_h :: h :: t
////          case h :: new_h :: t =>
////            Path(new_h.path, new_h.size + h.size) :: h :: t
//          case _ => pathList
//        }
//      case dirParser(name) =>
//        pathList match {
//          case h :: t => Path(s"${h.path}$name/") :: h :: t
//          case _ => pathList
//        }
//      case _ => pathList
//    }
//  }
//
//  def solvePartOne(l: List[String]): Int = {
//    val arr = l
//      .foldLeft(List(): List[Path])(traversePaths)
////      .filter(_.size <= 100000)
////      .filter(p => {
////        val r = raw"/\w+/".r
////        r.matches(p.path)
////      })
////      .sortBy(_.path)
////      .map(_.path)
////      .mkString("\n")
//
////      .map(_.size)
////      .sum
//
//    println(arr)
//    12
//  }
//
////  def sumByFirstDir(p: Path): Int = {}
//}
