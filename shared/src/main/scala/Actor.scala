package main.scala

import java.util.UUID
import java.util.UUID.randomUUID

import scala.collection.mutable.{ArrayBuffer, HashMap}




sealed case class Signature(id:UUID)

class ActorMessage(sigin:Signature, targetin:Float) {

  final val sig = sigin
  final val target = targetin
  def applyTo (actt:ActorTrait) : ArrayBuffer[ActorMessage] = ???

}

trait ActorTrait {

  //an unchangable id
  private val id = Actor.registerActor(this)
  final def getID():UUID = id

  var MessageStack:ArrayBuffer[ActorMessage] = ArrayBuffer()

  def update():ArrayBuffer[ActorMessage] = {
    val temp = MessageStack.map{ msg =>
      msg.applyTo(this)
    }.flatten
    temp
  }

  final def getSignature():Signature = Signature(getID())

}

object Actor {

  val ActorList: HashMap[UUID,ActorTrait] = HashMap.empty[UUID,ActorTrait]

  //registers an actor and returns its id
  def registerActor(a:ActorTrait):UUID = {
    //creates id and hopes it is unique
    var id = randomUUID()
    while(ActorList.contains(id)){
      id = randomUUID()
    }
    //Add Actor and ID to the actor list
    ActorList+=((id,a))
    //Returns the unique id
    id
  }

  private var MessageStack:ArrayBuffer[ActorMessage] = ArrayBuffer()

  def update() = {
    //routes messages
    routeMessages()
    //updates all actors in parallel and picks up messages to route
    MessageStack = ActorList.par.map{ case (id,actor) => actor.update() }.to[ArrayBuffer].flatten
    //ActorList.map{ case (id,actor) => println("b4clr",actor.MessageStack) }
    ActorList.map{ case(id,actor) => actor.MessageStack.clear()}
  }
  def routeMessages() = {
    //Spawns new thread for each actor
    ActorList.par.map{ case (id,actor) =>
      //for each actor the global message stack is filtered to return a new list of messages which is added to each actors message list
      actor.MessageStack = MessageStack.filter( _.target == id )//.to[ArrayBuffer]
    }
    //The global message stack is cleared
    MessageStack.clear()
  }

}