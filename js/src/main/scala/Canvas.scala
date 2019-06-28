package main.scala

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js


@js.native
trait CANVAS extends html.Canvas {

  def transferControlToOffscreen(): dom.raw.Transferable;

}