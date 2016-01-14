package com.lateralthoughts.spark

import com.basho.riak.spark._
import org.apache.spark.{SparkConf, SparkContext}

object QueryAll extends App {

  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SparkMyRiak")
    .set("spark.riak.connection.host", "127.0.0.1")
    .set("spark.riak.connections.min", "1") // optional
    .set("spark.riak.connections.max", "50") // optional

  val sc = new SparkContext(conf)

  val rdd = sc.riakBucket[String]("bucket_name")
    .queryAll()

  rdd.map(_.toUpperCase).saveToRiak("new_bucket")

  sc.stop()
}


