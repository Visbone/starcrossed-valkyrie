import java.nio.ByteBuffer
import boopickle.Default._
import org.portablescala.reflect.Reflect

trait Sendable {

  def companion: SendableCompanion = ???

}

@org.portablescala.reflect.annotation.EnableReflectiveInstantiation
trait SendableCompanion {

  val ID:Int
  val toByteBuffer:(Sendable)=>(java.nio.ByteBuffer)
  val fromByteBuffer:(java.nio.ByteBuffer)=>Sendable

  Sendable.convertByteBuffer(ID) = (( (msg:Sendable) => (ID,toByteBuffer(msg))),(id:Int,buff:java.nio.ByteBuffer) => id match {
    case ID => fromByteBuffer(buff)
    case _ => throw new Exception("Sendable ID mismatch, are two sendables using the same ID?")
  })

}

object Sendable {
  var convertByteBuffer:Array[((Sendable)=>(Int,java.nio.ByteBuffer),(Int,java.nio.ByteBuffer)=>Sendable)] = new Array(1024)
}

case class ReflectRequest(ID:Int, msg:ByteBuffer) extends Sendable {
  override def companion: SendableCompanion = ReflectRequest
}
object ReflectRequest extends SendableCompanion {
  override val ID: Int = 0
  override val fromByteBuffer: ByteBuffer => Sendable = bytes =>{
    Unpickle[ReflectRequest].fromBytes(bytes)
  }
  override val toByteBuffer: Sendable => ByteBuffer = (snd:Sendable) => snd match {
    case x:ReflectRequest => {
      Pickle.intoBytes[ReflectRequest](x)
    }
    case _ => throw new Exception("Sendable ID mismatch, are two sendables using the same ID?")
  }
}

case class ReflectCommand(classname:String) extends  Sendable {
  override def companion = ReflectCommand

  val clsOpt = Reflect.lookupLoadableModuleClass(classname)
  try {
    val cls = clsOpt.get
    val instance = cls.loadModule()
  }catch{
    case _ => {
      //TODO ADD FAILURE STATE
    }
  }

}
object ReflectCommand extends SendableCompanion{
  override val ID: Int = 1
  override val toByteBuffer: Sendable => ByteBuffer = (snd:Sendable) => snd match {
    case x:ReflectCommand => {
      Pickle.intoBytes[ReflectCommand](x)
    }
    case _ => throw new Exception("Sendable ID mismatch, are two sendables using the same ID?")
  }
  override val fromByteBuffer: ByteBuffer => Sendable = bytes =>{
    Unpickle[ReflectCommand].fromBytes(bytes)
  }
}