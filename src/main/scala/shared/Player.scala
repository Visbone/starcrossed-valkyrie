package shared
import pixiscalajs.PIXI
import pixiscalajs.PIXI.Sprite

case class Player(var x:Float,var y:Float) extends Entity{

  override val sprite: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fwobster.png?1546108981626",true)
  override val visible: Boolean = true


}
