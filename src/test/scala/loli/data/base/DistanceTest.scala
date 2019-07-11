package loli.data.base

import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.sql.SparkSession

case class KeyValue(var key: Int, var value: Int)

object DistanceTest {

  def main(args: Array[String]): Unit = {
    println(Distance.jaccard(List(1), List(1)))
    println(Distance.jaccard(List(1,2), List(1,2)))
    println(Distance.jaccard(List(1,1,2,2,3), List(1,2,3,4,5)))

    val spark = SparkSession
      .builder
      .appName("DistanceTest")
      .master("local")
      .config("spark.worker.timeout", 600)
      .config("spark.akka.timeout", 600)
      .getOrCreate()

    val sc = spark.sparkContext
    println(Distance.jaccard(sc.parallelize(List(1,1,2,2,3)), sc.parallelize(List(1,2,3,4,5))))

    RowMatrix

//    import spark.implicits._
//    val df1 = sc.parallelize(List(1,1,2,2,3)).map((k,v) => KeyValue(k,v)).toDF()
  }
}
