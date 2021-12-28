package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite :

  import FunSets.*


  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s0 = singletonSet(0)
    val snegative1000 = singletonSet(-1000)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s7 = singletonSet(7)
    val s1000 = singletonSet(1000)
    val sAll = union(s1, union(s2, union(s3, union(s4, union(s5, s1000)))))
    val emptySet: FunSet = (x => false)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
      assert(contains(s2, 2), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      assert(contains(sAll, 1), "Union 1")
      assert(contains(sAll, 2), "Union 2")
      assert(contains(sAll, 3), "Union 3")
      assert(contains(sAll, 4), "Union 4")
      assert(contains(sAll, 5), "Union 5")
      assert(contains(sAll, 1000), "Union 1000")
      assert(!contains(sAll, 7), "Union 7")
    }
  }

  test("union with empty Funset") {
    new TestSets {
      val s = union(emptySet, s1)
      assert(contains(s, 1))
      assert(!contains(s, 2))
    }
  }

  test("intersect contains elements") {
    new TestSets {
      val i = intersect(sAll, s3)
      assert(contains(i, 3), "Intersect 1")
      assert(!contains(i, 2), "Intersect 2")
      assert(!contains(i, 4), "Intersect 4")
      assert(!contains(i, 1000), "Intersect 1000")
    }
  }

  test("intersect with empty Funset") {
    new TestSets {
      val i = intersect(emptySet, s1)
      assert(!contains(i, 1), "Intersect 1")
    }
  }
  test("filter work correct") {
    new TestSets {
      assert(contains(filter(sAll, x => x > 1), 2))
      assert(contains(filter(sAll, x => x < 3), 1))
      assert(contains(filter(sAll, x => x == 4), 4))
    }
  }

  test("forall returns true with all conditions") {
    new TestSets {
      val s1AndS2 = union(s1, s2)
      assert(!forall(s1AndS2, x => x <= 0))
      assert(forall(s1AndS2, x => x > 0))
      assert(forall(s1AndS2, x => x + 3 > 3))
    }
  }

  test("forall returns true for an empty Funset with any predicate") {
    new TestSets {
      assert(forall(emptySet, x => false))
    }
  }
  test("exists returns true if one element satisfies the condition") {
    new TestSets {
      val setWithOnlyOdds = union(s2, s3)
      assert(exists(setWithOnlyOdds, x => x + 1 >= 3))
      assert(exists(setWithOnlyOdds, x => x - 1 >= 1))
    }
  }

  test("map change every element") {
    new TestSets {
      val s = union(union(singletonSet(2000), s2), s3)
      val mapped = map(s, number => number * 3)
      assert(!contains(mapped, 6000), "map 3")
      assert(contains(mapped, 6), "map 3")
      assert(contains(mapped, 9), "map 9")
    }
  }

  import scala.concurrent.duration.*

  override val munitTimeout = 10.seconds
