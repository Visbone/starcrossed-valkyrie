package shared

import pixiscalajs.PIXI.{DisplayObject, Point, Sprite}

import scala.collection.mutable.ArrayBuffer

trait Entity {

  var x:Float
  var y:Float
  val visible:Boolean
  val display:DisplayObject

  def updateSprite(camera:Point,center:Point,scale:Point) = {

    display.scale = scale
    display.pivot.x = Entity.anchor.x * display.getLocalBounds().width  / display.scale.x
    display.pivot.y = Entity.anchor.y * display.getLocalBounds().height / display.scale.y
    display.x = (x - camera.x)*scale.x + center.x
    display.y = (y - camera.y)*scale.y + center.y
  }
  val id = Entity.register(this)
  def removefromPool()={
    //Entity.entities.remove(id) this was a disaster waiting to happen

  }
}

object Entity{
  def register(e:Entity):Int = {
    entities.append(e)
    entities.length-1
  }
  var entities:ArrayBuffer[Entity] = ArrayBuffer[Entity]()
  private val anchor:Point = new Point(0.5f,0.5f)
}