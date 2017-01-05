
import scala.meta._
/**
  * Created by akhil on 10/26/16.
  */

class Cache(implicit ttl: Int) extends scala.annotation.StaticAnnotation {
  import autocomplete._
  inline def apply(defn: Any): Any = meta {
    defn match {
      case defn: Defn.Def =>
        println("11111")
        defn.tparams
        val s =
          q"""
                    $ttl
          """
        println(s)
        //println(ttl)
        println(defn.show[Structure])
        defn

      case _ =>
        abort("@Cache must annotate def")
    }
  }
}

class Debug extends scala.annotation.StaticAnnotation {
  import autocomplete._
  inline def apply(defn: Any): Any = meta {
    defn match {
      case defn: Defn.Def =>
        val printlnStatements = defn.tparams.map { param =>
          q"""println(
                ${param.name.syntax} + ": " +
                ${Term.Name(param.name.value)})"""
        }
        val body: Term = q"""
          { ..$printlnStatements }
          val start = _root_.java.lang.System.currentTimeMillis()
          val result = ${defn.body}
          val elapsed = _root_.java.lang.System.currentTimeMillis() - start
          println("Method " + ${defn.name.syntax} + " ran in " + elapsed + "ms")
          result
          """
        defn.copy(body = body)
      case _ =>
        abort("@Debug most annotate a def")
    }
  }
}