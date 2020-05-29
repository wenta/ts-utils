package wenta

import java.time.Instant

import carldata.series.TimeSeries
import org.scalatest.{Matchers, WordSpec}

class AnomaliesTest extends WordSpec with Matchers {
  "Anomalies" should {
    "remove 3 repeated value in a row" in {
      val idx = (for (i <- 0 until 10) yield Instant.ofEpochSecond(i)).toVector
      val vs = Vector(0d, 1d, 1d, 1d, 3d, 4d, 5d, 9d, 0d, 3d)
      val idx2 = Vector(0, 4, 5, 6, 7, 8, 9).map(Instant.ofEpochSecond(_))
      val vs2 = Vector(0d, 3d, 4d, 5d, 9d, 0d, 3d)
      val ts = TimeSeries(idx, vs)
      val expected = TimeSeries(idx2, vs2)
      Anomalies.removeNInTheRow(ts, 3) shouldEqual expected
    }

    "give the same series when there is not enough repetition" in {
      val idx = (for (i <- 0 until 10) yield Instant.ofEpochSecond(i)).toVector
      val vs = Vector(0d, 1d, 1d, 1d, 3d, 4d, 5d, 9d, 0d, 3d)
      val ts = TimeSeries(idx, vs)
      Anomalies.removeNInTheRow(ts, 4) shouldEqual ts
    }
  }
}
