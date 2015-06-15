package ch3pkg

import org.apache.spark._
import org.apache.spark.SparkContext._

object ch3 {
  def main(args: Array[String]) = {
    println("> START")
    
    val conf = new SparkConf().setMaster("local").setAppName("ch3");                                                  
    val sc = new SparkContext(conf);
 
    // Transformations	
    val input = sc.parallelize(List(1, 2, 3, 4));
    val result = input.map(x => x*x);
    for(i <- result) { println(i) }
    println(result.collect().mkString(","));
    
    val lines = sc.parallelize(List ("hello world", "hi"));
    val words = lines.flatMap(line => line.split(" "));
    for(w <- words) { println(w) }
    
    //Actions
    val sum = input.reduce((x, y) => x+y)
    println(sum)
    
    val result2 = input.aggregate((0,0))(
        (acc, value) => (acc._1 + value, acc._2 + 1),
        (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    val avg = (result2._1.toDouble / result2._2.toDouble).toDouble
    println(avg)
    
    println("> DONE")
  }
  
}