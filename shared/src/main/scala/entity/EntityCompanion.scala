package entity

import com.thoughtworks.enableIf
import main.scala.{Actor}
import pixiscalajs.PIXI.{DisplayObject, Point, Sprite}

import scala.collection.mutable

abstract class Entity(id:Long) extends Actor(id){

  var x:Float
  var y:Float
  val visible:Boolean



  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  val display:DisplayObject


}

object Entity {

  val Entities = mutable.ListBuffer.empty[Entity]

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  def updateSprite(entity:Entity,camera:Point,center:Point,scale:Point) = {
    entity.display.scale = scale
    entity.display.pivot.x = entity.display.getLocalBounds().width  / (2*entity.display.scale.x)
    entity.display.pivot.y = entity.display.getLocalBounds().height / (2*entity.display.scale.y)
    entity.display.x = (entity.x - camera.x)*scale.x + center.x
    entity.display.y = (entity.y - camera.y)*scale.y + center.y
  }


}