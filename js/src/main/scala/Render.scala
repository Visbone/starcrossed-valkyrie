package main.scala

import org.scalajs.dom
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Pixi, RendererOptions}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Render")
class Render {

    @JSExport
    def main(): Unit = {
      WorkerGlobal.addEventListener("message", onMessage _ )
      println("loading rendering worker")
    }

    def onMessage(msg: dom.MessageEvent)= {

      var data = msg.data.asInstanceOf[workerData];
      var canvas = data.canvas;

      val renderer = Pixi.autoDetectRenderer(canvas.clientWidth, canvas.clientHeight, RendererOptions(canvas))

      val graphics = new PIXI.Graphics()
      graphics.beginFill(0xFF3300).lineStyle(1, 0xffffff, 1)
      graphics.endFill()

      graphics.drawCircle(500, 300, 10)

      renderer.render(graphics)

      println("rendering from worker!")

    }

}
