package ch3pkg

import org.apache.spark._
import org.apache.spark.rdd._

class SearchFunctions(val query: String) {
	def isMatch(s: String): Boolean = {
	  s.contains(query)
	}
	/*def getMatchesFucntionReference(rdd: RDD[String]) : RDD[String] = {
	  rdd.map(isMatch)
	}
	def getMatchesFieldReference(rdd: RDD[String]) : RDD[String] = {
	  rdd.map(x => x.split(query))
	}*/
	def getMatchesNoReference(rdd: RDD[String]) = {
	  val query_ = this.query
	  rdd.map(x => x.split(query))
	}
}