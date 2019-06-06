import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import org.scalajs.dom.html
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, RendererOptions}

import scala.scalajs.js
import scala.scalajs.js.JSON



@JSExportTopLevel("Valkyrie")
class Valkyrie {
  @JSExport
  def Client(canvas: html.Canvas): Unit = {
    println("Hello, World")
    val renderer = Pixi.autoDetectRenderer(canvas.clientWidth, canvas.clientHeight, RendererOptions(canvas))

    val graphics = new PIXI.Graphics()
    graphics.beginFill(0xFF3300).lineStyle(1, 0xffffff, 1)
    graphics.endFill()
/*
    var Client = new VORTEX.Client("vortexserver.glitch.me");

    Client.on('connect', ()=>{

      println("connected");

      Client.on('message', ()=>{
        println(data);
      });

      Client.on('close', ()=>{
        println("unconnected :(");
      });

    });
*/



    var down = false
    canvas.onmousedown = (e: dom.MouseEvent) => {
      down = true
    }
    canvas.onmouseup = (e: dom.MouseEvent) => {
      down = false
    }
    canvas.onmousemove = (e: dom.MouseEvent) => {
      if (down) {
        val rect = canvas.getBoundingClientRect()
        graphics.drawCircle(e.clientX - rect.left, e.clientY - rect.top, 10)
        renderer.render(graphics)
      }

    }
    renderer.render(graphics)

  }



}
