import org.scalatest.wordspec.AnyWordSpec

class StandingsSpec extends AnyWordSpec {

  "The Standings parser" should {
    val hello =
      """Lions 3, Snakes 3
        |Tarantulas 1, FC Awesome 0
        |Lions 1, FC Awesome 1
        |Tarantulas 3, Snakes 1
        |Lions 4, Grouches 0""".stripMargin.lines.toSeq

    val helloExpected =
      """1. Tarantulas, 6 pts
        |2. Lions, 5 pts
        |3. FC Awesome, 1 pt
        |3. Snakes, 1 pt
        |5. Grouches, 0 pts""".stripMargin

    "Print the correct Hello World standings" in {
      val result = Standings.parseInput(hello)
      assert(result === helloExpected)
    }

    val plural =
      """Lions 3, Snakes 3
        |Tarantulas 1, FC Awesome 0
        |FC Awesome 1, Tarantulas 0""".stripMargin.lines.toSeq

    val pluralExpected =
      """1. FC Awesome, 3 pts
        |1. Tarantulas, 3 pts
        |3. Lions, 1 pt
        |3. Snakes, 1 pt""".stripMargin

    "Print pt and pts at correct 'points'(lol)" in {
      val result = Standings.parseInput(plural)
      assert(result === pluralExpected)
    }

    val tie =
      """Lions 3, Snakes 0
        |Snakes 3,  Lions 0
        |Tarantulas 1, FC Awesome 0
        |FC Awesome 1, Tarantulas 0""".stripMargin.lines.toSeq

    val tieExpected =
      """1. FC Awesome, 3 pts
        |1. Lions, 3 pts
        |1. Snakes, 3 pts
        |1. Tarantulas, 3 pts""".stripMargin

    "Print tied teams correctly and in alphabetical order" in {
      val result = Standings.parseInput(tie)
      assert(result === tieExpected)
    }
  }
}
