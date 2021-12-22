package recfun

object RecFun extends RecFunInterface :

  def pascal(c: Int, r: Int): Int = {
    if c == 0 || c == r
    then 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def balance(chars: List[Char]): Boolean = {
    def parenthesisCheck(char: Char): Int =
      if char.equals('(') then 1
      else if char.equals(')') then -1
      else 0

    def cuttingChars(rest: List[Char], acc: Int = 0): Boolean =
      if acc < 0 then false
      else if rest.isEmpty then acc == 0
      else cuttingChars(rest.tail, acc + parenthesisCheck(rest.head))

    cuttingChars(chars)
  }

  def countChange(money: Int, coins: List[Int]): Int = {

    def process(currentleMoney: Int, coins: List[Int]): Int =
      if coins.isEmpty || currentleMoney < 0 then 0
      else if currentleMoney == 0 then 1
      else process(currentleMoney - coins.head, coins) +
        process(currentleMoney, coins.tail)

    if money <= 0 then 0
    else process(money, coins)
  }






