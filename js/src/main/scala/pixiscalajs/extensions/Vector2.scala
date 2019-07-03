package pixiscalajs.extensions

import pixiscalajs.PIXI.Point


/** Based on code
  * Created by fabienbk on 20/10/16.
  * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
  *
  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
  *
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  */
case class Vector2(var x : Double, var y : Double) {
  def inverse(): Vector2 = return this * -1

  def +(point : Vector2) = Vector2(x + point.x, y + point.y)
  def -(point : Vector2) = Vector2(x - point.x, y - point.y)
  def *(k : Double) = Vector2(x * k, y * k)
  def /(k : Double) = Vector2(x / k, y / k)

  def sqrMagnitude = x*x+y*y
  def magnitude = Math.sqrt(x*x+y*y)
  def normalized = this / magnitude

  def rotate(degrees: Double) : Vector2 = rotateRadians(degrees * Vector2.DEG_TO_RADS)

  def rotateRadians(radians: Double) : Vector2 = {
    val ca = Math.cos(radians);
    val sa = Math.sin(radians);
    Vector2(ca*x - sa*y, sa*x + ca*y);
  }
}

object Vector2 {
  val DEG_TO_RADS : Double = Math.PI/180;

  val Down = Vector2( 0, 1)
  val Up   = Vector2( 0,-1)
  val Left = Vector2(-1, 0)
  val Right= Vector2( 1, 0)
  val Zero = Vector2( 0, 0)

  def Random = Vector2(scala.util.Random.nextFloat()*2 - 1, scala.util.Random.nextFloat()*2 - 1)
}

object Implicits {
  implicit def pointToPoint2D(p: Point) = Vector2(p.x, p.y)

  implicit def point2DToPoint(p: Vector2) = Point(p.x, p.y)
}