package facades.vortex

import org.scalajs.dom.WebSocket

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


@js.native
@JSGlobal("VORTEX")
object VORTEX extends js.Object {

  @js.native
  class Client extends js.Object {
    def this(url:String) = this()
    //var ws:WebSocket = js.native
    def on(eventName:String, callback:js.Function):Int = js.native
/*
    def on(eventName:String, callback:(js.Object)=>Unit):Int = js.native
    def on(eventName:String, callback:(js.Object,js.Object)=>Unit):Int = js.native
    def on(eventName:String, callback:(js.Object,js.Object,js.Object)=>Unit):Int = js.native
*/
  }

}

