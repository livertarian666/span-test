import domain.{Team, TeamScore}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Try

object Standings {

  def main(args : Array[String]): Unit = {
   val inputs = getValidInputs(args)
   inputs.foreach {
      input =>
       val parsedInput = parseInput(input)
       println(parsedInput)
   }
  }

  /**
   * Takes args and returns an array of the lines of all valid files
   * @return
   */
  def getValidInputs(args : Array[String]): Array[Seq[String]] = {
    for {
      fileName <- args
      input = Try(Source.fromFile(fileName))
      if input.isSuccess
    } yield input.get.getLines().toSeq
  }

  def parseInput(input: Seq[String]): String ={
    val results: Seq[Team] = input.flatMap(line => parseFixture(line))
    val teams = results.groupBy(result => result.name)

    val printList = teams.map( entry=> {
      val name = entry._1                             //Take team name
      val total = entry._2.map(_.points).sum          //Map points values of team to list and sum for total points
      Team(name, total)                               //Final team to use for print list
    }).toList
      .sorted.reverse                                 //Order
      .groupBy(team => team.points).values            //Group by equal points :)

    var offset = 0                                    //Keep track of offset in case of points ties
    var result = new ListBuffer[String]
    for ((teamList, index) <- printList.zipWithIndex) {
      val pos = index + offset + 1                    //Position is 1-index plus offset
      teamList.foreach(team =>
        result+=s"""$pos. ${team.name}, ${team.points} ${if(team.points ==1) "pt" else "pts"}"""
      )  //print each element in list with pos = index
      offset = offset + teamList.length - 1           //increment offset if list size > 1
    }
    result.toList.mkString("\n")
  }

  /**
   * Takes scoreLine, returns list of Team, which contains
   * the team name and their points from the fixture
   * @return
   */
  def parseFixture(scoreLine: String): List[Team]= {
    val split = scoreLine.split(",").map(_.trim)
    val team1 = split(0)
    val team2 = split(1)
    parseScore(team1).checkResult(parseScore(team2))
  }

  def parseScore(teamScore: String): TeamScore = {
    val whiteSpaceSplit = teamScore.split(" ")
    val name = whiteSpaceSplit.slice(0, whiteSpaceSplit.length -1).mkString(" ")
    val score = whiteSpaceSplit.last.toInt
    TeamScore(name = name, score = score)
  }
}
