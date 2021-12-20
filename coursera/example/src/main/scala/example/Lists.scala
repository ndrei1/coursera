package example

object Lists:

  def sum(xs: List[Int]): Int = xs.sum

  def max(xs: List[Int]): Int = if xs.isEmpty then
    throw java.util.NoSuchElementException("array is empty") else xs.max
