package pixiscalajs.extensions

import org.scalajs.dom.window

/** Based on code
  * Created by fabienbk on 20/10/16.
  * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
  *
  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
  *
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  */
object AnimationLoop {

  def apply(loop: => Unit): Unit = {
    loop
    window.requestAnimationFrame((d: Double) => apply(loop))
  }

}

object DefineLoop {
  def apply(loop: => Unit): DefineLoop = new DefineLoop(loop)
}

class DefineLoop(loop: => Unit) {
  def run(): Unit = {
    loop
    window.requestAnimationFrame((d: Double) => run())
  }
}