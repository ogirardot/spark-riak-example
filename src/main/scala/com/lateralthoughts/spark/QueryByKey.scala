package com.lateralthoughts.spark

import com.basho.riak.spark._
import org.apache.spark.{SparkConf, SparkContext}

object QueryByKey extends App {

  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SparkMyRiak")
    .set("spark.riak.connection.host", "127.0.0.1")

  val sc = new SparkContext(conf)

  val rdd = sc.riakBucket[String]("bucket_name")
    .queryBucketKeys("key1", "key2")

  rdd.map(_.toUpperCase).saveToRiak("new_bucket")

  sc.stop()
}


