package day2

import utils.ReadFiles
import zio.Scope
import zio.test.{Assertion, Spec, TestAspect, TestEnvironment, ZIOSpecDefault, assert}

object day2 extends ZIOSpecDefault {

  val input   = ReadFiles.readFile(2)
  val reports = input.map(Report.apply)

  case class Report(levels: Seq[Int]) {

    val safe: Boolean = {
      val mid = levels
        .sliding(2)
        .map { l =>
          val (l1, l2) = (l.head, l.last)
          val diff     = math.abs(l1 - l2) > 0 && math.abs(l1 - l2) <= 3
          (diff, l1 < l2)
        }
        .toSeq
      mid.map(_._1).forall(_ == true) && mid.map(_._2).distinct.size == 1
    }

    val safeWithDampener: Boolean = levels.zipWithIndex.exists { case (_, i) =>
      Report(levels.take(i) ++ levels.drop(i + 1)).safe
    }

  }

  object Report {
    def apply(s: String): Report = Report(s.split(" ").map(_.toInt).toSeq)
  }

  val p1 = test("p1") {
    val task = reports.count(_.safe)
    assert(task)(Assertion.equalTo(472))
  }

  val p2 = test("p2") {
    val task = reports.count(_.safeWithDampener)
    assert(task)(Assertion.equalTo(520))
  }

  override def spec: Spec[TestEnvironment & Scope, Any] = suite("2")(p1, p2) @@ TestAspect.sequential
}
