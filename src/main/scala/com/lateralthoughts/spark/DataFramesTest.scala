package com.lateralthoughts.spark

import java.util.UUID

import com.basho.riak.spark._
import com.basho.riak.spark.rdd.BucketDef
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

case class ConnectionEvent(id: String, userId: String, session: Int)

object DataFramesTest extends App{

  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SparkMyRiak")
    .set("spark.riak.connection.host", "127.0.0.1")

  val sc = new SparkContext(conf)

  val sqlContext = new SQLContext(sc)

  private val events = List(
    ConnectionEvent(UUID.randomUUID().toString, "jonBonJovi", 1),
    ConnectionEvent(UUID.randomUUID().toString, "jonBonJovi", 2),
    ConnectionEvent(UUID.randomUUID().toString, "jonBonJovi", 3),
    ConnectionEvent(UUID.randomUUID().toString, "titof", 4),
    ConnectionEvent(UUID.randomUUID().toString, "michael", 5)
  )
  sc.parallelize(events).saveAsRiakBucket(BucketDef("connections"))

  println(
    sc.riakBucket[ConnectionEvent](
      bucketName = "connections",
      bucketType = "default"
    ).queryAll().collect().toList)
}


