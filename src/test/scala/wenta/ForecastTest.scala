package wenta

import java.time.{Duration, Instant}

import carldata.series.TimeSeries
import org.scalatest.{Matchers, WordSpec}

class ForecastTest extends WordSpec with Matchers {
  "Forecast" should {
    "series with naive method" in {
      val idx = Vector(0, 1, 2, 3).map(Instant.ofEpochSecond(_))
      val idx2 = Vector(0, 1, 2, 3, 4).map(Instant.ofEpochSecond(_))
      val vs = Vector(0, 1, 2, 3)
      val vs2 = Vector(0, 1, 2, 3, 3)
      Forecast.naive(TimeSeries(idx, vs)) shouldEqual TimeSeries(idx2, vs2)
    }

    "empty series with naive method" in {
      Forecast.naive(TimeSeries.empty[Double]) shouldEqual TimeSeries.empty[Double]
    }

    "series with simple average method" in {
      val idx = Vector(0, 1, 2, 3, 4).map(Instant.ofEpochSecond(_))
      val idx2 = Vector(0, 1, 2, 3, 4, 5).map(Instant.ofEpochSecond(_))
      val vs = Vector(0d, 1d, 2d, 3d, 4d)
      val vs2 = Vector(0d, 1d, 2d, 3d, 4d, 2d)
      Forecast.simpleAverage(TimeSeries(idx, vs)) shouldEqual TimeSeries(idx2, vs2)
    }

    "empty series with simple average method" in {
      Forecast.simpleAverage(TimeSeries.empty[Double]) shouldEqual TimeSeries.empty[Double]
    }

    "series with moving average method" in {
      val idx = Vector(0, 1, 2, 3).map(Instant.ofEpochSecond(_))
      val idx2 = Vector(0, 1, 2, 3, 4).map(Instant.ofEpochSecond(_))
      val vs = Vector(0d, 1d, 2d, 3d)
      val vs2 = Vector(0d, 1d, 2d, 3d, 2.5)
      Forecast.movingAverage(TimeSeries(idx, vs), Duration.ofSeconds(2)) shouldEqual TimeSeries(idx2, vs2)
    }

    "empty series with moving average method" in {
      Forecast.movingAverage(TimeSeries.empty[Double]) shouldEqual TimeSeries.empty[Double]
    }
  }
}
