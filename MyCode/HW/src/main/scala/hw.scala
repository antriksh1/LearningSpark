object Hi { 
  def main(args: Array[String]) = {
    
    println("Hi!") 
    
    var str = ""
    
    for(i <- 0 until 100) {
      if(i % 3 == 0) 
        str += "Fizz"
      if(i % 5 == 0)
    	str += "Buzz"
      
      if(str == "")
        str = i.toString()
      
      println(str)
      str = ""
    }
  }
}

