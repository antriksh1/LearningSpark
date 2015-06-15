//package com.as.Ch2_WordCount

import org.apache.spark._
import org.apache.spark.SparkContext._

object WordCount {
 
    def main(args: Array[String]) {
      //val inputFile = "resources/discogs_20140201_artists.xml";
      val inputFile = "resources/words.txt";
      val outputFile = "out";
      
      val conf = new SparkConf().setMaster("local").setAppName("wordCount")
      conf.set("spark.hadoop.validateOutputSpecs", "false")
      val sc = new SparkContext(conf)
      
      val input = sc.textFile(inputFile)

      val words = input.flatMap(line => line.split(" "))

      val counts = words.map(word => (word, 1)).reduceByKey((x, y) => x + y)

      val counts2 = counts.sortBy(_._2)
      val counts3 = counts.sortByKey()
      
      for(i <- counts2) {println(i)}
      println("-------------")
      for(i <- counts3) {println(i)}
      	
      //counts2.saveAsTextFile(outputFile)
      
    }
}
