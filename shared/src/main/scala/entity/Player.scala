package entity

import java.util.UUID
import com.thoughtworks.enableIf
import main.scala.ActorSystem
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Point, Sprite}

case class Player(var x:Float,var y:Float,val id:Long = ActorSystem.getID) extends Entity(id){

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  override val display = {

    val body = PIXI.Sprite.fromImage("assets/survivor.png")
    val fist1= PIXI.Sprite.fromImage("assets/fist.png")
    val fist2= PIXI.Sprite.fromImage("assets/fist.png")
    fist1.y = 13
    fist2.y = 13

    fist1.x = -12
    fist2.x = 12

    body.x = 0
    body.y = 0

    val out = (new PIXI.Container)
    out.addChild(body)
    out.addChild(fist1)
    out.addChild(fist2)

    out
  }






  override val visible: Boolean = true



}

object Player {

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  def updateSprite(plyr:Player, camera: Point, center: Point, scale: Point): Unit = {
    Entity.updateSprite(plyr, camera, center, scale)
    plyr.display.pivot.x = 16.0
    plyr.display.pivot.y = 16.0
  }

}