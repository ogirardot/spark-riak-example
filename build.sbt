name := "spark-riak-example"

organization := "com.lateralthoughts"

version := "1.0"

scalaVersion := "2.10.5"

resolvers += Resolver.mavenLocal

// transitive dependencies of spark-riak-connector
//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.5.2"

libraryDependencies += "com.basho.riak" % "spark-riak-connector" % "1.1.0"

