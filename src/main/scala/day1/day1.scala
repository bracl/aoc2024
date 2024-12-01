package day1

import utils.ReadFiles
import zio.Scope
import zio.test.{Assertion, Spec, TestAspect, TestEnvironment, ZIOSpecDefault, assert}

object day1 extends ZIOSpecDefault {

  val input = ReadFiles.readFile(1)

  val (left, right) = input.foldLeft((Seq.empty[Int], Seq.empty[Int])) { case ((l, r), i) =>
    val nums = i.split(" ")
    (l.prepended(nums.head.toInt), r.prepended(nums.last.toInt))
  }

  val p1 = test("p1") {
    val task = left.sorted.zip(right.sorted).map { case (l, r) => math.abs(l - r) }.sum
    assert(task)(Assertion.equalTo(2113135))
  }

  val p2 = test("p2") {
    val task = left.map(l => l * right.count(r => r == l)).sum
    assert(task)(Assertion.equalTo(19097157))
  }

  override def spec: Spec[TestEnvironment with Scope, Any] = suite("1")(p1, p2) @@ TestAspect.sequential
}
