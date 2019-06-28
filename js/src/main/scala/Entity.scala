import java.util.UUID

import main.scala.{Actor, ActorTrait}
import pixiscalajs.PIXI.{DisplayObject, Point}

import scala.collection.mutable.ArrayBuffer

trait Entity extends ActorTrait{

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

  def removefromPool()={
    //Entity.entities.remove(id) this was a disaster waiting to happen

  }
}

object Entity{
  val anchor = new Point(0.5,0.5)
  object entities{

    def map( func:(Entity)=>Unit ) = {

      Actor.ActorList.map(
        x => x match {
          case (id: UUID, actr: Entity) => func(actr)
          case _ => {}
        })

    }

  }
  //var entities:ArrayBuffer[Entity] = ArrayBuffer[Entity]()
}