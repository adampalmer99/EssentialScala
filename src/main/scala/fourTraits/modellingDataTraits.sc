trait A {
  def d: D
}
sealed trait D
final case class B() extends D
final case class C() extends D

// alternatively
sealed trait A
final case class D(b: B) extends A
final case class E(c: C) extends A

sealed trait TrafficLight
case object Red extends TrafficLight
case object Amber extends TrafficLight
case object Green extends TrafficLight


sealed trait Calculator
final case class success(result: Int) extends Calculator
final case class fail(reason: String) extends Calculator

sealed trait Source
case object Well extends Source
case object Spring extends Source
case object Tap extends Source
final case class BottledWater(size: Int, source: Source, carbonated: Boolean)

// WORKING WITH DATA
//Structural recursion is essentially the process of breaking down data into smaller pieces

sealed trait A {
  def foo: String
}
final case class B() extends A {
  def foo: String = "It's B!"
}
final case class C() extends A {
  def foo: String = "It's C!"
}