package recfun

object RecFun extends RecFunInterface :

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) then
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def balance(chars: List[Char]): Boolean = {
    def parenthesisCheck(char: Char): Int = {
      if char.equals('(') then 1 else if char.equals(')') then -1 else 0
    }

    def cuttingChars(rest: List[Char], acc: Int = 0): Boolean =
      if acc < 0 then
        false else if acc > 0 && rest.isEmpty then
        false else if rest.isEmpty && acc == 0 then
        true else cuttingChars(rest.tail, acc + parenthesisCheck(rest.head))

    cuttingChars(chars)
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    def process(quantityOfCoin: Int, currentlyMony: Int, currentlyCoins: List[Int]): Int =
      if currentlyMony == 0 then
        1 else if currentlyMony < 0 then
        0 else if quantityOfCoin <= 0 && currentlyMony >= 1 then
        0 else process((quantityOfCoin - 1), currentlyMony, currentlyCoins.tail) + process(quantityOfCoin, (currentlyMony - currentlyCoins.head), currentlyCoins)

    process(coins.length, money, coins)
  }


