package shared

import pixiscalajs.PIXI.{Point, Sprite}

import scala.collection.mutable.ArrayBuffer

trait Entity {

  var x:Float
  var y:Float
  val visible:Boolean
  val sprite:Sprite

  def updateSprite(camera:Point,center:Point,scale:Point) = {
    sprite.anchor = Entity.anchor
    sprite.scale = scale
    sprite.x = (x - camera.x)*scale.x + center.x
    sprite.y = (y - camera.y)*scale.y + center.y
  }
  Entity.entities.append(this)


}

object Entity{
  var entities:ArrayBuffer[Entity] = ArrayBuffer[Entity]()
  private val anchor:Point = new Point(0.5f,0.5f)
}