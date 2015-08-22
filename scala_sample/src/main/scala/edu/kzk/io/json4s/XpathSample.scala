package edu.kzk.io.json4s

object XpathSample extends App {
  import org.json4s._
  import org.json4s.JsonDSL._
  import org.json4s.jackson.JsonMethods._
  import com.fasterxml.jackson.annotation.JsonValue

  // json value
  val jValue = render(
    ("person" ->
      ("name" -> "Joe") ~
      ("age" -> 35) ~
      ("spouse" ->
        ("person" ->
          ("name" -> "Marilyn") ~
          ("age" -> 33)
        )
      )
    ))

  // \\ sample
  var extractedJsonValue = jValue \\ "spouse";
  println(compact(extractedJsonValue))

  // \\ concatenation sample
  extractedJsonValue = jValue \\ "spouse" \\ "person" \\ "age"
  println(compact(extractedJsonValue))

  // findField sample
  val jField = jValue findField {
    case JField("name", _) => true
    case _ => false
  }
  println(jField.get._2.values) // unboxed value

  // filterField sample
  val jFilterFields = jValue filterField {
    case JField("name", _) => true
    case _ => false
  }
  println(jFilterFields)
}