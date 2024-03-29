package recfun

class RecFunSuite extends munit.FunSuite :

  import RecFun.*

  // ------ balance tests -----------------------------------------------------

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: with extra right parenthesis") {
    assert(!balance("())(".toList))
  }

  test("balance: with extra left parenthesis") {
    assert(!balance("()()()(".toList))
  }

  test("balance: with one parenthesis") {
    assert(!balance(")".toList))
  }

  test("balance: a string without parentheses is balanced") {
    assert(balance("text text text text text".toList))
  }

  test("balance: with empty string") {
    assert(balance("".toList))
  }
  // ------ countChange tests -------------------------------------------------

  test("countChange: 4") {
    assertEquals(countChange(4, List(1, 2)), 3)
  }

  test("countChange: 300") {
    assertEquals(countChange(300, List(5, 10, 20, 50, 100, 200, 500)), 1022)
  }

  test("countChange: 301") {
    assertEquals(countChange(301, List(5, 10, 20, 50, 100, 200, 500)), 0)
  }

  test("countChange: 1") {
    assertEquals(countChange(1, List(1)), 1)
  }

  test("countChange: 0") {
    assertEquals(countChange(0, List(1,2,4)), 0)
  }

  test("countChange: 100") {
    assertEquals(countChange(100, List()), 0)
  }

  test("countChange: 300") {
    assertEquals(countChange(300, List(500, 5, 50, 100, 20, 200, 10)), 1022)
  }

  // ------ pascal tests ------------------------------------------------------

  test("pascal: col=0,row=2") {
    assertEquals(pascal(0, 2), 1)
  }

  test("pascal: col=1,row=2") {
    assertEquals(pascal(1, 2), 2)
  }

  test("pascal: col=1,row=3") {
    assertEquals(pascal(1, 3), 3)
  }

  import scala.concurrent.duration.*

  override val munitTimeout = 10.seconds
