package js

import facades.vortex.{CANVAS, VORTEX}
import org.scalajs.dom
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, RendererOptions}
import pixiscalajs.extensions.{DefineLoop,Keyboard}

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
    val sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fwobster.png?1546108981626",true)//.from('assets/image.png');
    sprite.anchor = PIXI.Point(0.5f,0.5f)
    sprite.x = 20
    sprite.y = 20
    sprite.scale = PIXI.Point(2,2)



    stage.addChild(sprite)
    println("d")

    val right = Keyboard.bind(39)
    val left = Keyboard.bind(37)
    val up = Keyboard.bind(38)
    val down = Keyboard.bind(40)

    val loop = DefineLoop{
      if(right.isDown) sprite.x+=1
      if(left.isDown) sprite.x-=1
      if(up.isDown) sprite.y-=1
      if(down.isDown) sprite.y+=1

      renderer.render(stage)
    }




  }



}
