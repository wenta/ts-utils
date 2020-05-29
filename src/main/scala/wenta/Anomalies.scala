package wenta

import java.time.Instant

import carldata.series.TimeSeries

import scala.annotation.tailrec

object Anomalies {
  /**
   * If value appear at least n times it means it is not natural and we should remove it (it is anomaly)
   */
  def removeNInTheRow(ts: TimeSeries[Double], n: Int = 10): TimeSeries[Double] = {
    @tailrec
    def f(xs: Vector[(Instant, Double)], buffer: Vector[(Instant, Double)]
          , res: Vector[(Instant, Double)], lastRemoved: Boolean = false): Vector[(Instant, Double)] = {
      if (xs.isEmpty) res ++ buffer
      else {
        val newBuffer = buffer :+ xs.head
        if (newBuffer.length == n && newBuffer.map(_._2).distinct.length == 1) {
          f(xs.tail, newBuffer.tail, res, lastRemoved = true)
        }
        else if (lastRemoved) {
          val newBufferWithoutLastRemoved = newBuffer.filter(x => x._2 != newBuffer.head._2)
          val missingInBuffer = n - newBufferWithoutLastRemoved.length
          f(xs.drop(missingInBuffer), newBufferWithoutLastRemoved ++ xs.slice(1, missingInBuffer), res)
        }
        else f(xs.tail, newBuffer.tail, res :+ newBuffer.head)
      }
    }

    val dps = f(ts.dataPoints.drop(n - 1), ts.dataPoints.take(n - 1), Vector()).unzip
    TimeSeries(dps._1, dps._2)
  }

}
