package ch4pkg

import org.apache.spark._
import org.apache.spark.SparkContext._

object ch4 {
	def main(args: Array[String]) {
		val conf = new SparkConf().setMaster("local").setAppName("wordCount")
		val sc = new SparkContext(conf)
      
		val input = sc.parallelize(List( ("panda",0), ("pink",7), ("pirate",4), ("panda",1), ("pink",4) ))
		// Dont know how to use
		/*implicit val sortByAvgValue = new Ordering[Int] {
		  override def compare(a: Int, b: Int) = a.compare(b)
		}*/
		
		//per key sum, and count - to get average - based on Eg. 4-8
		val result = input.mapValues(x => (x, 1))
						.reduceByKey( (k1, k2) => (k1._1 + k2._1, k1._2 + k2._2) )
						.mapValues( v => v._1.toDouble / v._2.toDouble )
		
		for(i <- result.sortByKey()) { println(i) }
		
		//per average - based on Eg. 4-13
		// combineByKey[C](createCombiner: (V) ⇒ C, mergeValue: (C, V) ⇒ C, mergeCombiners: (C, C) ⇒ C): RDD[(K, C)]
		val result2 = input
				.combineByKey(
						(v) => (v, 1), // if value not found
						(valueAcc: (Int, Int), newValue) => (valueAcc._1 + newValue, valueAcc._2 + 1), // if value found
						(valueAcc1: (Int, Int), valueAcc2: (Int, Int)) => (valueAcc1._1 + valueAcc2._1, valueAcc1._2 + valueAcc2._2))
				.map( { case(key, value) => (key, value._1 / value._2.toFloat)  } )
		
		val result2Sorted = result2.sortBy(_._2)
		for(i <- result2Sorted) {println(i)}
		println("--------")
		result2Sorted.collectAsMap().map(println(_)); // This collection may have a random order
		// result2Sorted.map(println(_)); // doesnt work for some reason - maybe because its not a collection
		
	}
}