package shared

import pixiscalajs.PIXI.Sprite

trait Entity {
  val x:Float
  val y:Float
  val visible:Boolean
  val sprite:Sprite
}
