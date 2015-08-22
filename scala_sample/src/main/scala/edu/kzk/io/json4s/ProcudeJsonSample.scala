package edu.kzk.io.json4s

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object ProcudeJsonSample extends App {
  // Case Class を宣言
  case class Winner(id: Long, numbers: List[Int])
  case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])

  // Classを作成
  val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
  val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)

  // Json Objectにする
  val json =
    ("lotto-id" -> lotto.id) ~
      ("winning-numbers" -> lotto.winningNumbers) ~
      ("draw-date" -> lotto.drawDate.map(_.toString)) ~
      ("winners" ->
        lotto.winners.map { w =>
          (("winner-id" -> w.id) ~
            ("numbers" -> w.numbers))
        })

  //  val json =
  //    ("lotto" -> ("lotto-id" -> lotto.id) ~
  //      ("winning-numbers" -> lotto.winningNumbers) ~
  //      ("draw-date" -> lotto.drawDate.map(_.toString)) ~
  //      ("winners" ->
  //        lotto.winners.map { w =>
  //          (("winner-id" -> w.id) ~
  //            ("numbers" -> w.numbers))
  //        }))

  println(compact(render(json)))
}