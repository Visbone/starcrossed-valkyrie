package shared

import pixiscalajs.PIXI
import pixiscalajs.PIXI.Sprite

case class TestWall(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32
  override val sprite: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fteststone.png?v=1543961365002")
  override val visible: Boolean = true
}

case class Space(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32
  override val sprite: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fteststone.png?v=1543961365002")
  override val visible: Boolean = false
}

object TestWall {
  val Tile = Array.ofDim[Entity](32,32)
  for(i <- 0 to 32)for(j <- 0 to 32){
    Tile(i)(j)=Space(i,j)
  }

}