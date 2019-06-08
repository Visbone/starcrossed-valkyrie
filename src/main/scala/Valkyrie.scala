import facades.vortex.{VORTEX}
import facades.vortex.{CANVAS}

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
  def Client(canvas: CANVAS): Unit = {

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

    val offscreen = canvas.transferControlToOffscreen();
    var renderWorker = new dom.raw.Worker("Render.js");
    //renderWorker.postMessage(js.Dictionary("canvas" -> offscreen), js.Array(offscreen));

    /*var down = false
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
    renderer.render(graphics)*/

  }



}
