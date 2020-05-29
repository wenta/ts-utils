package wenta

import java.time.Instant

import carldata.series.TimeSeries
import org.scalatest.{Matchers, WordSpec}

class GeneralTest extends WordSpec with Matchers {
  "General" should {
    "sort by index" in {
      val idx = (for (i <- 1 to 5) yield Instant.ofEpochSecond(5 - i)).toVector
      val vs = Vector(20d, 15d, 10d, 5d, 0d)
      val idx2 = (for (i <- 0 until 5) yield Instant.ofEpochSecond(i)).toVector
      val vs2 = Vector(0d, 5d, 10d, 15d, 20d)
      val ts = TimeSeries(idx, vs)
      val expected = TimeSeries(idx2, vs2)
      General.sortByIndex(ts) shouldEqual expected
    }

    "sort by index on empty series" in {
      General.sortByIndex(TimeSeries.empty[Double]) shouldEqual TimeSeries.empty[Double]
    }
  }
}
