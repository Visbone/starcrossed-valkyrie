package main.scala




import scala.collection.mutable.{ArrayBuffer, LongMap}




sealed case class Signature(id:Long)

class ActorMessage(sigin:Signature, targetin:Float) {


  final val sig = sigin
  final val target = targetin
  def applyTo (actt:Actor) : ArrayBuffer[ActorMessage] = ???

}





abstract class Actor(id:Long) {


  final def getID():Long = id
  ActorSystem.registerActor(this)

  var MessageStack:ArrayBuffer[ActorMessage] = ArrayBuffer()

  def update():ArrayBuffer[ActorMessage] = {
    val temp = MessageStack.map{ msg =>
      msg.applyTo(this)
    }.flatten
    temp
  }

  final def getSignature():Signature = Signature(getID())


}



object ActorSystem {

  val random = new scala.util.Random
  val ActorList: LongMap[Actor] = LongMap.empty[Actor]

  //registers an actor and returns its id
  def registerActor(a:Actor) = {
    
    
    ActorList+=((a.getID(),a))
  }
  
  def getID:Long = {
    //creates id and hopes it is unique
    var id = Math.abs(random.nextLong())
    while(ActorList.contains(id)){
      id = Math.abs(random.nextLong())
    }
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