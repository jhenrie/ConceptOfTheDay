package conceptoftheday.modules.bombdefuse

import org.scalatest.FlatSpec

class BombSpec extends FlatSpec {
  behavior of "The Bomb object"

  it should "Explode when white wire is cut after white wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), White())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.White)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when black wire is cut after white wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), White())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.White)
    val secondCut = Bomb.cutWire(firstCut, Black())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when white wire is cut after black wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Black())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Black)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when green wire is cut after black wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Black())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Black)
    val secondCut = Bomb.cutWire(firstCut, Green())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when orange wire is cut after black wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Black())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Black)
    val secondCut = Bomb.cutWire(firstCut, Orange())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when purple wire is cut after purple wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Purple())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Purple)
    val secondCut = Bomb.cutWire(firstCut, Purple())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when green wire is cut after purple wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Purple())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Purple)
    val secondCut = Bomb.cutWire(firstCut, Green())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when orange wire is cut after purple wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Purple())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Purple)
    val secondCut = Bomb.cutWire(firstCut, Orange())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when white wire is cut after purple wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Purple())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Purple)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when white wire is cut after red wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Red())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Red)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when black wire is cut after red wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Red())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Red)
    val secondCut = Bomb.cutWire(firstCut, Black())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when purple wire is cut after red wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Red())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Red)
    val secondCut = Bomb.cutWire(firstCut, Purple())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when red wire is cut after red wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Red())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Red)
    val secondCut = Bomb.cutWire(firstCut, Red())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when orange wire is cut after red wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Red())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Red)
    val secondCut = Bomb.cutWire(firstCut, Orange())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when black wire is cut after green wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Green())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Green)
    val secondCut = Bomb.cutWire(firstCut, Black())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when purple wire is cut after green wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Green())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Green)
    val secondCut = Bomb.cutWire(firstCut, Purple())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when red wire is cut after green wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Green())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Green)
    val secondCut = Bomb.cutWire(firstCut, Red())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when green wire is cut after green wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Green())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Green)
    val secondCut = Bomb.cutWire(firstCut, Green())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when white wire is cut after orange wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Orange())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Orange)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when purple wire is cut after orange wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Orange())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Orange)
    val secondCut = Bomb.cutWire(firstCut, Purple())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when green wire is cut after orange wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Orange())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Orange)
    val secondCut = Bomb.cutWire(firstCut, Green())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Explode when orange wire is cut after orange wire is cut" in {
    val firstCut = Bomb.cutWire(Bomb(), Orange())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.Orange)
    val secondCut = Bomb.cutWire(firstCut, Orange())
    assert(secondCut.bombState == BombState.Exploded)
  }

  it should "Defuse when the following wires are cut in this order white red green white" in {
    val firstCut = Bomb.cutWire(Bomb(), White())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.White)
    val secondCut = Bomb.cutWire(firstCut, Red())
    assert(secondCut.count == 2)
    assert(secondCut.bombState == BombState.Red)
    val thirdCut = Bomb.cutWire(secondCut, Green())
    assert(thirdCut.count == 3)
    assert(thirdCut.bombState == BombState.Green)
    val forthCut = Bomb.cutWire(thirdCut, White())
    assert(forthCut.count == 4)
    assert(forthCut.bombState == BombState.Defuse)
  }

  it should "Stays defused once defused" in {
    val firstCut = Bomb.cutWire(Bomb(), White())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.White)
    val secondCut = Bomb.cutWire(firstCut, Red())
    assert(secondCut.count == 2)
    assert(secondCut.bombState == BombState.Red)
    val thirdCut = Bomb.cutWire(secondCut, Green())
    assert(thirdCut.count == 3)
    assert(thirdCut.bombState == BombState.Green)
    val forthCut = Bomb.cutWire(thirdCut, White())
    assert(forthCut.count == 4)
    assert(forthCut.bombState == BombState.Defuse)
    val fifthCut = Bomb.cutWire(forthCut, White())
    assert(fifthCut.count == 4)
    assert(fifthCut.bombState == BombState.Defuse)
  }

  it should "Stays exploded once exploded" in {
    val firstCut = Bomb.cutWire(Bomb(), White())
    assert(firstCut.count == 1)
    assert(firstCut.bombState == BombState.White)
    val secondCut = Bomb.cutWire(firstCut, White())
    assert(secondCut.bombState == BombState.Exploded)
    val thirdCut = Bomb.cutWire(secondCut, White())
    assert(thirdCut.bombState == BombState.Exploded)
  }
}
