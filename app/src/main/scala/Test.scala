/**
  * Created by akhil on 10/26/16.
  */
@main
object Test {

  println("Hello world")

  implicit val ttl = 1

  @Debug
  def test(input1: String, input2: String): Unit = {
    println("Call hello" + input1 + input2)
  }

  test("test1", "test2")
}
