package wenta

import java.time.Instant

import carldata.series.TimeSeries
import org.scalatest.{Matchers, WordSpec}

class ScalingTest extends WordSpec with Matchers {
  "Scaling" should {
    "minmax on simple series" in {
      val idx = (for (i <- 0 until 5) yield Instant.ofEpochSecond(i)).toVector
      val vs = Vector(0d, 5d, 10d, 15d, 20d)
      val vs2 = Vector(0d, 0.25, 0.5, 0.75, 1.0)
      val ts = TimeSeries(idx, vs)
      val expected = TimeSeries(idx, vs2)
      Scaling.minMaxScaler(ts) shouldEqual expected
    }

    "min max scale on empty series" in {
      Scaling.minMaxScaler(TimeSeries.empty) shouldEqual TimeSeries.empty
    }
    "min max scale on one value series" in {
      val idx = (for (i <- 0 until 5) yield Instant.ofEpochSecond(i)).toVector
      val vs = Vector(5d, 5d, 5d, 5d, 5d)
      val vs2 = Vector(0.5, 0.5, 0.5, 0.5, 0.5)
      val ts = TimeSeries(idx, vs)
      val expected = TimeSeries(idx, vs2)
      Scaling.minMaxScaler(ts) shouldEqual expected
    }
  }
}
