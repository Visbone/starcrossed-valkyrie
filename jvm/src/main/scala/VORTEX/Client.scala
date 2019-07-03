package VORTEX

import org.java_websocket.WebSocket

import scala.collection.mutable

object Client {

  var nextID = 0

}

class Client(val ws: WebSocket) {

  val id = Client.nextID
  val data = new mutable.ArrayBuffer[VORTEX]()
  val subscriptions = new mutable.HashSet[String]()

  Client.nextID += 1

  def push(pack: VORTEX): Unit = data.append(pack)

  def empty(): Unit = data.clear()

  def addChannel(name: String): Unit = subscriptions.add(name)

  def removeChannel(name: String): Unit = subscriptions.remove(name)

}
