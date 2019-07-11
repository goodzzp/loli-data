package loli.data.base

import org.apache.spark.rdd.RDD

/**
  * common algorithms form distance
  */
object Distance {
  /**
    * jaccard distance of two bags
    *
    * @param as bag1
    * @param bs bag2
    * @return similar
    */
  def jaccard(as: List[Int], bs: List[Int]): Double = {
    val (xs, ys) = if (as.size <= bs.size) (as, bs) else (bs, as)
    val xCounts = xs.groupBy(x => x).map(x => x._1 -> x._2.size)
    val mins = ys.foldLeft(Map(): Map[Int, Int]) { (ms, y) =>
      lazy val yCount: Int = ms.getOrElse(y, 0)
      if (xCounts.contains(y) && xCounts(y) > yCount) {
        ms.updated(y, yCount + 1)
      } else {
        ms
      }
    }

    val sum = mins.valuesIterator.sum
    1 - sum.toDouble / (as.size + bs.size - sum)
  }

  /**
    * jaccard distance of two rdd
    *
    * @param rdd1 rdd
    * @param rdd2 rdd
    * @return similar
    */
  def jaccard(rdd1: RDD[Int], rdd2: RDD[Int]): Double = {
    val map1 = rdd1.map(x => (x, 1)).reduceByKey((x, y) => x + y)
    val map2 = rdd2.map(x => (x, 1)).reduceByKey((x, y) => x + y)

    val intersection = map1.join(map2).map(x =>
      Math.min(x._2._1, x._2._2)
    ).sum()
    1 - intersection.toDouble / (rdd1.count() + rdd2.count() - intersection)
  }
}
