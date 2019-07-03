package VORTEX

import scala.collection.mutable

class Channel(name: String) {

  val subscribers = new mutable.HashSet[Client]()

  def sub(c: Client): Unit = {

    subscribers.add(c)
    c.addChannel(name)

  }

  def unsub(c: Client): Unit = {

    subscribers.remove(c)
    c.removeChannel(name)

  }

  def unsubAll() = for(c <- subscribers) unsub(c)

  def push(data: VORTEX) = for(c <- subscribers) c.push(data)

}