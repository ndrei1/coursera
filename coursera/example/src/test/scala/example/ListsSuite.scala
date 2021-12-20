package example

class ListsSuite extends munit.FunSuite :

  test("one plus one is two (0pts)") {
    assert(Lists.sum(List(1, 1)) == 2)
  }

  test("negative one plus one is zero (0pts)") {
    assert(Lists.sum(List(-1, 1)) == 0)
  }
  test("negative one plus negative one is negative two (0pts)") {
    assert(Lists.sum(List(-1, -1)) == -2)
  }

  test("one plus two is three (0pts)?") {
    assert(Lists.sum(List(1, 2)) == 3)
  }

  test("details why one plus one is not three (0pts)") {
    assert(Lists.sum(List(1, 1)) != 3)
  }

  test("Lists.max throws an exception if array is empty") {
    try
      Lists.max(List())
      fail("No exception has been thrown")
    catch
      case e: java.util.NoSuchElementException => ()
  }

  import Lists.*

  test("sum of a few numbers (10pts)") {
    assert(Lists.sum(List(1, 2, 0)) == 3)
  }

  test("max of a few negative numbers (10pts)") {
    assert(Lists.max(List(-3, -7, -2)) == -2)
  }

  test("max of a few numbers (10pts)") {
    assert(Lists.max(List(3, 7, 2)) == 7)
  }

  import scala.concurrent.duration.*

  override val munitTimeout = 1.seconds
