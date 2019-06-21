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

    var player = Player(20f,20f)

    for(i <- 0 to 31){
      var wall = TestWall(i,i)
      stage.addChild(wall.sprite)
    }
    stage.addChild(player.sprite)

    val right = Keyboard.bind(68)
    val left = Keyboard.bind(65)
    val up = Keyboard.bind(87)
    val down = Keyboard.bind(83)

    var camera = new Point(0f,0f)
    var scale = new Point(2,2)
    var center = new Point(renderer.width/2,renderer.height/2)

    val loop = DefineLoop{

      if(right.isDown) player.x+=1
      if(left.isDown) player.x-=1
      if(up.isDown) player.y-=1
      if(down.isDown) player.y+=1
      camera.x=player.x
      camera.y=player.y
      //player = Player(player.x+net.x.toFloat,player.y+net.y.toFloat)
      Entity.entities.map(_.updateSprite(camera,center,scale))


      renderer.render(stage)
    }

    loop.run()



  }



}
