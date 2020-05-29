package wenta

import carldata.series.TimeSeries

object General {
  /**
   * In TimeSeries.sortByIndex values which are not in order are removed.
   * In this function all data remained, return sorted series.
   */
  def sortByIndex[A](ts: TimeSeries[A]): TimeSeries[A] = {
    val dps = ts.dataPoints.sortBy(_._1).unzip
    TimeSeries(dps._1, dps._2)
  }
}
