package facades.vortex

import org.scalajs.dom.WebSocket

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Vortex")
object Vortex {

  class Client extends js.Object {

    var ws:WebSocket = js.native
    def on(eventName:String, callback:()=>Unit):Int = js.native

  }

}

