package facades.vortex

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import org.scalajs.dom
import org.scalajs.dom.html


@js.native
trait CANVAS extends html.Canvas {

  def transferControlToOffscreen(): dom.raw.Transferable;

}