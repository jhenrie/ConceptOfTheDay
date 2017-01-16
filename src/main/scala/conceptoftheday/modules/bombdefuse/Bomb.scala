package conceptoftheday.modules.bombdefuse

object BombState extends Enumeration {
  type State = Value
  val Initial, Exploded, Defuse, White, Black, Purple, Red, Green, Orange = Value
}

case class Bomb(count: Int = 0, bombState: BombState.State = BombState.Initial)

trait Wire{ val id: BombState.State }
case class White(id: BombState.State = BombState.White) extends Wire
case class Black(id: BombState.State = BombState.Black) extends Wire
case class Purple(id: BombState.State = BombState.Purple) extends Wire
case class Red(id: BombState.State = BombState.Red) extends Wire
case class Green(id: BombState.State = BombState.Green) extends Wire
case class Orange(id: BombState.State = BombState.Orange) extends Wire

object Bomb{

  private def cloneNewState(d: Bomb, s: BombState.State, c: Int) = {
    if(c == 4) {
      defuse(d)
    } else {
      d.copy(bombState = s, count = c)
    }
  }

  private def detonate(d: Bomb) = {
    println("Boom")
    d.copy(bombState = BombState.Exploded)
  }

  private def defuse(d: Bomb) = {
    println("Bomb defused")
    d.copy(bombState = BombState.Defuse, count = 4)
  }

  def cutWire(d: Bomb, w: Wire): Bomb = {
    d.bombState match {
      case BombState.Initial => cloneNewState(d, w.id, d.count + 1)
      case BombState.White =>
        w match {
          case _: White | _: Black => detonate(d)
          case _: Purple | _: Red | _: Green | _: Orange => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case BombState.Black =>
        w match {
          case _: White | _: Green | _: Orange => detonate(d)
          case _: Purple | _: Red | _: Black => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case BombState.Purple =>
        w match {
          case _: Purple | _: Green | _: Orange | _: White => detonate(d)
          case _: Black | _: Red => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case BombState.Red =>
        w match {
          case _: White | _: Black | _: Purple | _: Red | _: Orange => detonate(d)
          case _: Green => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case BombState.Green =>
        w match {
          case _: Black | _: Purple | _: Red | _: Green => detonate(d)
          case _: Orange | _: White => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case BombState.Orange =>
        w match {
          case _: White | _: Purple | _: Green | _: Orange => detonate(d)
          case _: Red | _: Black => cloneNewState(d, w.id, d.count + 1)
          case _ => d
        }
      case _ => d
    }
  }
}
