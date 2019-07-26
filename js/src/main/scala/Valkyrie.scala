
//import main.scala.vortex.{CANVAS, VORTEX}
import java.util.UUID

import entity._
import main.scala.{ActorSystem, VORTEX}
import org.scalajs.dom.html
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, Point, RendererOptions}
import pixiscalajs.extensions.{DefineLoop, Keyboard}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}



@JSExportTopLevel("Valkyrie")
class Valkyrie {

  @JSExport
  def Client(canvas: html.Canvas): Unit = {
/*

    var Client = new VORTEX.Client("ws://vortexserver.glitch.me");

    Client.on("connect", ()=>{

      println("connected");
      Client.on("message", (data: js.Array[Byte])=>{
        println(data);
      });

      Client.on("close", ()=>{
        println("unconnected :(");
      });

    });
*/

    val renderer = Pixi.autoDetectRenderer(canvas.clientWidth, canvas.clientHeight, RendererOptions(canvas))

    val stage = new PIXI.Container() {
      width = renderer.width
      height = renderer.height
    }
    //graphics.addChild(stage)


    PIXI.SCALE_MODES.DEFAULT = PIXI.SCALE_MODES.NEAREST




    //stage.addChild(sprite)
    println("BRUNO7")

    var player = Player(32,32)

    //Entity.entities.map(x=>if(x.visible)stage.addChild(x.display))
    ActorSystem.ActorList.foreach(
      x => x match {
        case (id: Long, actr: Entity) => {if(actr.visible){stage.addChild(actr.display);println("isvisible")};println("exists")}
        case _ => {}
      }

    )

    val right = Keyboard.bind(68)
    val left = Keyboard.bind(65)
    val up = Keyboard.bind(87)
    val down = Keyboard.bind(83)

    var camera = new Point(player.x,player.y)//(10*32*2,2*5*32)
    var scale = new Point(1,1)//(1.0/32.0,1.0/32.0)
    var center = new Point(renderer.width/2,renderer.height/2)
    var net = new Point(0,0)

    val loop = DefineLoop{
      net.x = 0
      net.y = 0
      if(right.isDown) net.x+=1
      if(left.isDown) net.x-=1
      if(up.isDown) net.y-=1
      if(down.isDown) net.y+=1

      player.x+=net.x.toFloat
      player.y+=net.y.toFloat

      //println(player.x,player.y)

      //println(scale.x,scale.y)

      Player.updateSprite(player,camera,center,scale)



      renderer.render(stage)
    }

    loop.run()



  }



}
