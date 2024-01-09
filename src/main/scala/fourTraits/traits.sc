// hello

// Can base as many classes as we like on a trait

import java.util.Date

  trait Visitor {
    def id: String
    def createdAt: Date

    def age: Long = new Date().getTime - createdAt.getTime //How long has the visitor been around
  }

  case class Anonymous(
    id: String,
    createdAt: Date = new Date()
  ) extends Visitor

  case class User(
  id: String,
  email: String,
  createdAt: Date = new Date()
  ) extends Visitor

  def older(v1: Visitor, v2: Visitor): Boolean =
    v1.createdAt.before(v2.createdAt)

older(Anonymous("1"), User("2", "test@example.com")) // TRUE

val anon = Anonymous("anon1")
anon.createdAt
anon.age

  trait Feline {
    def colour: String
    def sound: String = "Roar"
  }

  case class Cat(
    colour: String,
    faveFood: String,
    override val sound: String = "Meow"
  ) extends Feline

  case class AlternativeCat(
                colour: String,
                faveFood: String,
              ) extends Feline {
    override def sound: String = "Meow"
  }

  case class Lion(
    maineSize: Int,
    colour: String,
  ) extends Feline


Lion(1, "orange").sound
Cat("ginger", "tuna").sound
AlternativeCat("ginger", "tuna").sound


trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

case class Rectangle(width: Double, height: Double) extends Shape {
  val sides = 4
  val perimeter = 2 * width + 2 * height
  val area = width * height
}

case class Square(size: Double) extends Shape {
  val sides = 4
  val perimeter = 4 * size
  val area = size * size
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  override val perimeter = 2 * width + 2 * height
  override val area = width * height
}

case class Square1(size: Double) extends Rectangular {
  val width = size
  val height = size
}

case class Rectangle(val width: Double, val height: Double) extends Rectangular


// NOTE: If all the subtypes of a trait are known, seal the trait,
// consider making subtypes final if there is no case for extending them
// SEALED TRAITS allows control extensibility of types, majority of cases should use SEALED/FINAL CASE CLASS pattern
// ADVANTAGES:
// Compiler will warn if we miss a case in pattern matching
// Can control extension points of sealed traits and make stronger
// guarantees about the behaviour of subtypes


  // SEALED SHAPE EXERCISE

 sealed trait NewShape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

object Draw {
  def apply(shape: Shape): String = shape match {
    case Rectangle(width, height) => s"A rectangle with a width of ${width} and a height of ${height}"
    case Square(size) => s"A square of size ${size}cm"
    case Circle(radius) => s"a circle with the radius ${radius}"
  }
}

Draw(Circle(20))
Draw(Square(30))


sealed trait Color {
  def red: Double
  def green: Double
  def blue: Double
  def isLight = (red + green + blue) / 3.0 > 0.5
  def isDark = !isLight
}

case object Red extends Color {
  // RGB values as VALS because the values can't change
  val red = 1.0
  val green = 0.0
  val blue = 0.0
}

case object Yellow extends Color {
  val red = 1.0
  val green = 1.0
  val blue = 0.0
}
case object Pink extends Color {
  val red = 1.0
  val green = 0.0
  val blue = 1.0
}

final case class CustomColour(red: Double, green: Double, blue: Double) extends Color


sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

final case class Circle(radius: Double, color: Color) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = 2 * width + 2 * height
  val area = width * height
}


final case class Square(size: Double, color: Color) extends
  Rectangular {
  val width = size
  val height = size
}
final case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular


object Draw {
  def apply(shape: Shape): String = shape match {
    case Circle(radius, color) =>
      s"A ${Draw(color)} circle of radius ${radius}cm"
    case Square(size, color) =>
      s"A ${Draw(color)} square of size ${size}cm"
    case Rectangle(width, height, color) =>
      s"A ${Draw(color)} rectangle of width ${width}cm and height ${
        height}cm"
  }
  def apply(color: Color): String = color match {
    case Red => "red"
    case Yellow => "yellow"
    case Pink => "pink"
    case color => if(color.isLight) "light" else "dark"
  }
}

Draw(Rectangle(10, 20, Red))


