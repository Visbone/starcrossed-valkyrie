package js

import facades.vortex.{CANVAS, VORTEX}
import org.scalajs.dom
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, Point, RendererOptions}
import pixiscalajs.extensions.{DefineLoop, Keyboard}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import shared._

@JSExportTopLevel("Valkyrie")
class Valkyrie {
  @JSExport
  def Client(canvas: CANVAS): Unit = {



    /*
    var Client = new VORTEX.Client("vortexserver.glitch.me");

    Client.on("connect", ()=>{

      //println("connected");
      Client.on("message", (obj:js.Object)=>{
        //println(JSON.stringify(obj));
      });

      Client.on("close", ()=>{
        //println("unconnected :(");
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
    println("FF")
    TestWall
    var player = Player(64,64)



    Entity.entities.map(x=>if(x.visible)stage.addChild(x.sprite))

    val right = Keyboard.bind(68)
    val left = Keyboard.bind(65)
    val up = Keyboard.bind(87)
    val down = Keyboard.bind(83)

    var camera = new Point(10*32,5*32)
    var scale = new Point(2,2)
    var center = new Point(renderer.width/2,renderer.height/2)
    var net = new Point(0,0)

    val loop = DefineLoop{
      net.x = 0
      net.y = 0
      if(right.isDown) net.x+=1
      if(left.isDown) net.x-=1
      if(up.isDown) net.y-=1
      if(down.isDown) net.y+=1


      //camera.x=player.x
      //camera.y=player.y
      //player = Player(player.x+net.x.toFloat,player.y+net.y.toFloat)
      player.x+=net.x.toFloat
      Entity.entities.map(ent => {
        ent match {
          case x:TestWall => {

            if(Math.abs(player.x - x.x)<32 && Math.abs(player.y - x.y)<32){
              player.x-=net.x.toFloat
            }

          }
          case _ => {}
        }
      })

      player.y+=net.y.toFloat
      Entity.entities.map(ent => {
        ent match {
          case x:TestWall => {

            if(Math.abs(player.x - x.x)<32 && Math.abs(player.y - x.y)<32){
              player.y-=net.y.toFloat
            }

          }
          case _ => {}
        }
      })


      Entity.entities.map(_.updateSprite(camera,center,scale))


      renderer.render(stage)
    }

    loop.run()



  }



}
