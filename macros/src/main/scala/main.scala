/**
  * Created by akhil on 10/26/16.
  */
import scala.annotation.compileTimeOnly
import scala.meta._

@compileTimeOnly("@main not expanded")
class main extends scala.annotation.StaticAnnotation {
  import autocomplete._
  inline; def apply(defn: Defn) = meta {
    val q"object $name { ..$stats}" = defn
    val main =
      q"""
         def main(args: Array[String]): Unit = { ..$stats}
       """

    q"object $name { $main}"
  }
}
