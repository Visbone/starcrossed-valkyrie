package entity

import java.util.UUID
import com.thoughtworks.enableIf
import main.scala.Actor
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Point, Sprite}

case class Player(var x:Float,var y:Float) extends Entity{

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

  override def updateSprite(camera: Point, center: Point, scale: Point): Unit = {
    super.updateSprite(camera, center, scale)
    display.pivot.x=16
    display.pivot.y=16
  }

  override val visible: Boolean = true
  def testUpdate(net:Point):Int={
    val player = this
    player.x+=net.x.toFloat
    var out = 0
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            player.x-=net.x.toFloat
          }

        }
        case x:TestMonster => {
          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            out=1

          }
        }
        case _ => {}
      }
    })

    player.y+=net.y.toFloat
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            player.y-=net.y.toFloat
          }

        }
        case x:TestMonster => {
          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            out=1

          }
        }
        case _ => {}
      }
    })
    out
  }

}
