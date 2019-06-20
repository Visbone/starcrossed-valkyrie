package shared

import scala.collection.mutable.ArrayBuffer





sealed case class Signature(id:Float)

class ActorMessage(sigin:Signature, targetin:Float) {

  final val sig = sigin
  final val target = targetin
  def applyTo (actt:ActorTrait) : ArrayBuffer[ActorMessage] = ???

}

trait ActorTrait {

  //an unchangable id
  private val id = Actor.registerActor(this)
  final def getID():Float = id

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

  //private val ActorList: HashMap[Float,Actor] = HashMap.empty[Float,Actor]
  val ActorList:ArrayBuffer[(Float,ActorTrait)] = ArrayBuffer()

  def checkIDExist(tst:Float):Boolean = {
    var out = false
    var i=0
    while(!out){
      out=out||tst==ActorList(1)._1
      i+=1
    }
    out
  }
  //registers an actor and returns its id
  def registerActor(a:ActorTrait):Float = {
    //creates id and hopes it is unique
    var id = Math.random.toFloat

    /* UNCOMMENT THIS IF YOU ARE PARANOID
    while(checkIDExist(id)){
      id = Math.random.toFloat
    }
    */

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