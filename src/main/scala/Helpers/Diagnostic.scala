package Helpers

object Diagnostic {

  def time[R](block: => R): R = {
    val t0     = System.nanoTime()
    val result = block // call-by-name
    val t1     = System.nanoTime()
    println("Time: " + (t1 - t0) + "ns")
    result
  }

}
