import org.json4s._
 import org.json4s.jackson.JsonMethods._
  
object json4s_sample {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(263); 

  val json =
  ("person" ->
    ("name" -> "Joe") ~
    ("age" -> 35) ~
    ("spouse" ->
      ("person" ->
        ("name" -> "Marilyn") ~
        ("age" -> 33)
      )
    );System.out.println("""json  : <error> = """ + $show(json ))}
  )
  
}
