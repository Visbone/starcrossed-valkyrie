package entity

import java.util.UUID
import com.thoughtworks.enableIf
import main.scala.Actor
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Point, Sprite}

case class TestMonster(var x:Float,var y:Float) extends Entity {
  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  override val display: Sprite = PIXI.Sprite.fromImage("assets/wobster.png")

  override val visible: Boolean = true

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  var net = new Point()

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  def testUpdate()={

    val monster = this
    net.x = Math.random()*2-1//if(Math.random() < 0.5)1 else -1
    net.y = Math.random()*2-1//if(Math.random() < 0.5)1 else -1

    var b:(UUID,Double) = (null,Double.PositiveInfinity)
    Actor.ActorList.foreach( act=> act._2 match {
      case Player(x,y) => {
        println(x)
        var dd =Math.sqrt((x-monster.x)*(x-monster.x)+
          (y-monster.y)*(y-monster.y)
        )
        if(dd < b._2) b = (act._1,dd)

      }
      case _ => {}
    })


    Actor.ActorList(b._1) match {
      case Player(x,y)=>{
        var dx = (x - monster.x)/32
        var dy = (y - monster.y)/32
        val dd = Math.sqrt(dx*dx+dy*dy).toFloat
        dx = dx/dd
        dy=  dy/dd
        val s = 10
        val (vx,vy) = (dx/(s+dd),dy/(s+dd))
        net.x=0
        net.y=0
        net.x+=vx
        net.y+=vy
        val nd = Math.sqrt(net.x*net.x+net.y*net.y).toFloat//Math.max(Math.abs(net.x),Math.abs(net.y))
        net.x/=nd
        net.y/=nd
        //net.x+=Math.random()*3-1.5
        //net.y+=Math.random()*3-1.5
        //println(net.x,net.y)
      }
      case _ => {}
    }









    monster.x += net.x.toFloat

    Entity.entities.map(ent => {
      ent match {
        case x: TestWall => {

          if (Math.abs(monster.x - x.x) < 30 && Math.abs(monster.y - x.y) < 30) {
            monster.x -= net.x.toFloat
          }

        }
        case _ => {}
      }
    })

    monster.y += net.y.toFloat

    Entity.entities.map(ent => {
      ent match {
        case x: TestWall => {

          if (Math.abs(monster.x - x.x) < 30 && Math.abs(monster.y - x.y) < 30) {
            monster.y -= net.y.toFloat
          }

        }
        case _ => {}
      }
    })

  }

}
