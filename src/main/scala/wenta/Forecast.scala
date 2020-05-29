package wenta

import java.time.{Duration, Instant}

import carldata.series.TimeSeries

object Forecast {
  def naive[V](ts: TimeSeries[V]): TimeSeries[V] = {
    if (ts.isEmpty) ts
    else TimeSeries(newIndex(ts), ts.values :+ ts.values.last)
  }

  def simpleAverage(ts: TimeSeries[Double]): TimeSeries[Double] = {
    if (ts.isEmpty) ts
    else TimeSeries(newIndex(ts), ts.values :+ (ts.values.sum / ts.values.length))
  }

  def movingAverage(ts: TimeSeries[Double], slidingWindow: Duration = Duration.ofDays(1)): TimeSeries[Double] = {
    if (ts.isEmpty) ts
    else {
      val idx = ts.index.last.plusMillis(1)
      val tail = ts.slice(idx.minus(slidingWindow), idx).values
      TimeSeries(newIndex(ts), ts.values :+ (tail.sum / tail.length))
    }
  }

  def newIndex[V](ts: TimeSeries[V]): Vector[Instant] = ts.index :+ ts.index.last.plus(ts.resolution)
}
