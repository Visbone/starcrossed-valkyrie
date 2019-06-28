package pixiscalajs.extensions

import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom.window

/**
  * Created by fabienbk on 20/10/16.
  * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
  *
  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
  *
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  */
case class KeyBinding(keyCode : Int) {

  var isUp = true
  var isDown = false
  var press : () => Unit = null
  var release : () => Unit = null

  val downHandler = (event: KeyboardEvent) => {
    if (event.keyCode == keyCode) {
      if (isUp && press != null) {
        press()
      }
      isDown = true
      isUp = false
      event.preventDefault()
    }
  }

  val upHandler = (event: KeyboardEvent) => {
    if (event.keyCode == keyCode) {
      if (isDown && release != null) {
        release()
      }
      isDown = false
      isUp = true
      event.preventDefault();
    }
  };

  window.addEventListener("keydown", downHandler, false)
  window.addEventListener("keyup", upHandler, false)
}