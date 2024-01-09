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
