package entity

import com.thoughtworks.enableIf
import pixiscalajs.PIXI
import pixiscalajs.PIXI.Sprite

case class Goal(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  override val display: Sprite = PIXI.Sprite.fromImage("assets/goal.png")

  override val visible: Boolean = true

  TestWall.Tile(xx)(yy).removefromPool()
  TestWall.Tile(xx)(yy)=this

}
case class TestWall(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  override val display: Sprite = PIXI.Sprite.fromImage("assets/testwall.png")

  override val visible: Boolean = true
  TestWall.Tile(xx)(yy).removefromPool()
  TestWall.Tile(xx)(yy)=this
}

case class Space(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32

  @enableIf(c => c.compilerSettings.exists(_.matches("""^-Xplugin:.*scalajs-compiler_[0-9\.\-]*\.jar$""")))
  override val display: Sprite = PIXI.Sprite.fromImage("assets/testwall.png")

  override val visible: Boolean = false
}


object TestWall {
  val Tile = Array.ofDim[Entity](48,48)

  for(i <- 0 to Tile.length-1)for(j <- 0 to Tile(0).length-1){
    Tile(i)(j) = Space(i,j)
  }


  for(i <- 1 to 39)for(j <- 1 to 19){

    if(i>0 && i<=10)if(Math.random()<0.2 )if(!(i==39 && j== 10))if(!(i==1 && j== 19))if(!(i==1 && j== 1)){
      TestWall(i,j)
    }
    if(i>10 && i<=20)if(Math.random()<0.25 )if(!(i==39 && j== 10))if(!(i==1 && j== 19))if(!(i==1 && j== 1)){
      TestWall(i,j)
    }
    if(i>20)if(Math.random()<0.25 )if(!(i==39 && j== 10))if(!(i==1 && j== 19))if(!(i==1 && j== 1)){
      TestWall(i,j)
    }


  }

  for(i <- 0 to 20){
    TestWall(i,20)
    TestWall(i,0)
    TestWall(i+20,20)
    TestWall(i+20,0)
    TestWall(40,i)
    TestWall(0,i)
  }

}