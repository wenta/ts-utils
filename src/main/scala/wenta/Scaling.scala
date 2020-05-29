package wenta

import carldata.series.TimeSeries

object Scaling {
  /**
   *
   * @param ts        original series to change
   * @param seriesMin minimal value in result series, lay on minimum of `ts`, default 0
   * @param seriesMax max value in result series, lay on maximum of `ts`, default 1
   * @return scaled series
   */
  def minMaxScaler(ts: TimeSeries[Double], seriesMin: Double = 0, seriesMax: Double = 1): TimeSeries[Double] = {
    if (ts.isEmpty) ts
    else {
      val min = ts.values.min
      val max = ts.values.max
      if (max - min == 0) { // when all series value are equal
        ts.mapValues(_ => 0.5)
      }
      else {
        val scale = (seriesMax - seriesMin) / (max - min)
        ts.mapValues(x => scale * x + seriesMin - min * scale)
      }

    }

  }
}
