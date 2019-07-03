package VORTEX

import java.net.InetSocketAddress
import java.util.{Timer, TimerTask}
import java.util.concurrent.ConcurrentHashMap

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import boopickle.Default._

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConverters._

trait VORTEX

abstract class Server(address: InetSocketAddress, RATE: Int)
  extends WebSocketServer(address)
{

  val clients = new ConcurrentHashMap[Int, Client]
  val channels = new ConcurrentHashMap[String, Channel]
  val setInterval = new Timer

  implicit val Pickler = compositePickler[VORTEX]

  def Client(id: Int) = clients.get(id)

  def Channel(name: String) = {

    if(channels.containsKey(name))
      channels.get(name)
    else {
      val c = new Channel(name)
      channels.put(name, c)
    }

  }

  def send(c: Client, data: ArrayBuffer[VORTEX]) = {

    val ws = c.ws

    if(ws.isOpen) ws.send(Pickle.intoBytes(data))

  }

  override def run() = {

    setInterval.scheduleAtFixedRate(new TimerTask {
      override def run(): Unit = {

        sendToAllClients()

        for(c: Client <- clients.values().asScala){

          sendToClient(c)
          send(c, c.data)
          c.empty()

        }

      }
    }, 0, 1000/RATE)
    super.run()
  }

  override def stop() = setInterval.cancel()

  override def onOpen(ws: WebSocket, handshake: ClientHandshake): Unit = {

    val c = new Client(ws)
    ws.setAttachment(c.id)
    clients.put(c.id, c)
    connect(c)

  }

  override def onMessage(ws: WebSocket, message: String): Unit = {

    val id = ws.getAttachment[Int]

  }

  override def onClose(ws: WebSocket, code: Int, reason: String, remote: Boolean): Unit = {

    val id = ws.getAttachment[Int]
    val c = Client(id)

    for(name <- c.subscriptions) Channel(name).unsub(c)
    clients.remove(id)

    close(id)

  }

  override def onError(ws: WebSocket, ex: Exception): Unit = ???

  def connect(c: Client): Unit

  def message(): Unit

  def close(id: Int): Unit

  def sendToClient(c: Client): Unit

  def sendToAllClients(): Unit

}