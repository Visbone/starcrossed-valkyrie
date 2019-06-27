import pixiscalajs.PIXI
import pixiscalajs.PIXI.Sprite

case class TestWall(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32
  override val display: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fteststone.png?v=1543961365002")
  override val visible: Boolean = true
  TestWall.Tile(xx)(yy).removefromPool()
  TestWall.Tile(xx)(yy)=this
}

case class Space(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32
  override val display: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fteststone.png?v=1543961365002")
  override val visible: Boolean = false
}

case class Goal(xx:Int,yy:Int) extends Entity {
  override var x:Float = xx * 32
  override var y:Float = yy * 32
  override val display: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Ftestwood.png?v=1543961365135")

  override val visible: Boolean = true

  TestWall.Tile(xx)(yy).removefromPool()
  TestWall.Tile(xx)(yy)=this

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