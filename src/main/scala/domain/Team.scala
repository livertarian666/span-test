package domain

/** A Team and its points, sortable by points*/
case class Team(name: String, points: Int) extends Ordered[Team] {
  override def compare(that: Team): Int = {
    val initialCompare = Integer.compare(points, that.points)
    initialCompare match {
      case 0 =>  -name.compareTo(that.name)
      case _ => initialCompare
    }
  }
}

case class TeamScore(name: String, score: Int) {
  /**
   * Compares two TeamScore(s) returning a list of
   * Teams with their points, 3 for win, 1 for draw
   * and 0 for a loss
   * @param that
   * @return
   */
  def checkResult(that: TeamScore): List[Team] = {
    if(score == that.score) {
      List(Team(name,1),Team(that.name,1))
    } else if (score > that.score) {
      List(Team(name,3),Team(that.name,0))
    } else List(Team(name,0),Team(that.name,3))
  }
}
