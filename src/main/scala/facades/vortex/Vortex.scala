package facades.vortex

import org.scalajs.dom.WebSocket

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Vortex")
object Vortex extends js.Object {

  @js.native
  class Client extends js.Object {

    //var ws:WebSocket = js.native
    def on(eventName:String, callback:()=>Unit):Int = js.native
    def on(eventName:String, callback:(js.Object)=>Unit):Int = js.native
    def on(eventName:String, callback:(js.Object,js.Object)=>Unit):Int = js.native
    def on(eventName:String, callback:(js.Object,js.Object,js.Object)=>Unit):Int = js.native

  }

}

