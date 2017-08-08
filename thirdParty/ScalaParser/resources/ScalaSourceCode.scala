package mypack

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio._

import java.util.{List => UtilList}
import java.awt.{List => AwtList}

class Point(xc: Int, yc: Int) {

  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = dx
    y = dy
  }

  def moveX(dx: Int) {
    x = dx
  }

  def moveY(dy: Int) {
    y = dy
  }
}


abstract class Person {
  def name: String
  def age: Int
}

@MyAnnotation
class SimpleClassWithAnnotation {

  def foo(i: Int) {
    var x: Int = i
    throw new IllegalStateException("Thrown in foo")
  }

  def funcWithInnerClass(a: Array) {
    class Inner {}
    for (e <- a) yield e

    for {
      file <- filesHere
    } yield file
  }
}

class Vehicle(color: String) extends IVehicle {
  private var s1,s2: String
  val i: Int

  def getS1() : String = return s1
  def getS2() : String = return s2
}

class Vector2D(x: Double, y: Double) {
  def sum : Int
  val i: Int
  var a: Double
  var s1, s2: String

  def fooWithTry() {
    try {
      a = b
    } catch {
      case e: Exception => e
    } finally {
      c = b
    }
  }

  def func7(i: Int) {
    var x: Int = i
    throw new IllegalStateException("Thrown in func7")
  }
}

case class Employee(val name: String, val age: Int, val salary: Int)
  extends Person

package foo {
  package bar {
    package baz {
      @Serializable
      class Person {
        var name: String
        var age: Int

        def simplefunc1 : Int = while (true) break

        def simplefunc2(i: Int) : Unit = return

        def test() {
          if (true) break
          else break

          while (true) {
            break
          }

          try {
            a = b
          } catch {
            case e: Exception => e
          }

          return
        }
      }
    }
  }
}

trait Similarity {

  def isSimilar(x: Any): Boolean
  def isNotSimilar(x: Any): Boolean = !isSimilar(x)
}

object TraitsTest extends App {
  val p1 = new Point(a, b)
  val p2 = new Point(c, d)

  def foo() {
    print(this.p2)

    if (a) print(p1)

    for (e <- a) yield e

    for {
      file <- filesHere
    } yield file
  }
}