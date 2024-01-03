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