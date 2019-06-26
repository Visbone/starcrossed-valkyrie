package main.scala

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


@js.native
@JSGlobal("VORTEX")
object VORTEX extends js.Object {

  @js.native
  class Client extends js.Object {
    def this(url:String) = this()
    def on(eventName:String, callback:js.Function):Int = js.native

  }

}

