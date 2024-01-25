
sealed trait D
final case class B() extends D
final case class C() extends D

trait A {
  def d: D
}

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

val anA: A = B()

anA.foo


// Sum type pattern match

sealed trait Feline
final case class Lion() extends Feline
final case class Tiger() extends Feline
final case class Panther() extends Feline
final case class Cat(favouriteFood: String) extends Feline


sealed trait Food
case object Antelope extends Food
case object TigerFood extends Food
case object Licorice extends Food
final case class CatFood(food: String) extends Food


sealed trait Feline {
  def dinner: Food
}
final case class Lion() extends Feline {
  def dinner: Food =
    Antelope
}
final case class Tiger() extends Feline {
  def dinner: Food =
    TigerFood
}
final case class Panther() extends Feline {
  def dinner: Food =
    Licorice
}
final case class Cat(favouriteFood: String) extends Feline {
  def dinner: Food =
    CatFood(favouriteFood)
}

sealed trait Feline {
  def dinner: Food =
    this match {
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(favouriteFood) => CatFood(favouriteFood)
    }
}

object Diner {
  def dinner(feline: Feline): Food =
    feline match {
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(food) => CatFood(food)
    }
}

// 3 WAYS TO IMPLEMENT STRUCTURAL RECURSION
// Polymorphism
// Pattern matching in the base trait
// Pattern matching in an external object(Diner example)

