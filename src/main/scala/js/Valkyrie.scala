package js

import facades.vortex.{CANVAS, VORTEX}
import org.scalajs.dom
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, Point, RendererOptions}
import pixiscalajs.extensions.{DefineLoop, Keyboard}
import shared.TestWall.Tile

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import shared._

import scala.collection.mutable.ArrayBuffer

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
    var player = Player(32,32)
    var player2 = Player(32,32*20-32)

    var goal = Goal(39,10)

    Entity.entities.map(x=>if(x.visible)stage.addChild(x.sprite))

    val right = Keyboard.bind(68)
    val left = Keyboard.bind(65)
    val up = Keyboard.bind(87)
    val down = Keyboard.bind(83)

    val right2 = Keyboard.bind(39)
    val left2 = Keyboard.bind(37)
    val up2 = Keyboard.bind(38)
    val down2 = Keyboard.bind(40)

    var camera = new Point(10*32*2,2*5*32)
    var scale = new Point(1,1)
    var center = new Point(renderer.width/2,renderer.height/2)
    var net = new Point(0,0)
    var net2 = new Point(0,0)

    val loop = DefineLoop{
      net.x = 0
      net.y = 0
      if(right.isDown) net.x+=1
      if(left.isDown) net.x-=1
      if(up.isDown) net.y-=1
      if(down.isDown) net.y+=1

      net2.x = 0
      net2.y = 0
      if(right2.isDown) net2.x+=1
      if(left2.isDown) net2.x-=1
      if(up2.isDown) net2.y-=1
      if(down2.isDown) net2.y+=1

      //camera.x=player.x
      //camera.y=player.y
      //player = Player(player.x+net.x.toFloat,player.y+net.y.toFloat)
      player.testUpdate(net)
      player2.testUpdate(net2)
      if((Math.abs(player.x-goal.x)<32 && Math.abs(player.y-goal.y)<32)||(Math.abs(player2.x-goal.x)<32 && Math.abs(player2.y-goal.y)<32)){
        player.x=32
        player.y=32
        player2.x=32
        player2.y=32*20-32

      }


      Entity.entities.map(_.updateSprite(camera,center,scale))


      renderer.render(stage)
    }

    loop.run()



  }



}
