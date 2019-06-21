package shared
import pixiscalajs.PIXI
import pixiscalajs.PIXI.{Point, Sprite}

case class Player(var x:Float,var y:Float) extends Entity{

  override val sprite: Sprite = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fwobster.png?1546108981626",true)
  override val visible: Boolean = true
  def testUpdate(net:Point)={
    val player = this
    player.x+=net.x.toFloat
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<32 && Math.abs(player.y - x.y)<32){
            player.x-=net.x.toFloat
          }

        }
        case _ => {}
      }
    })

    player.y+=net.y.toFloat
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<32 && Math.abs(player.y - x.y)<32){
            player.y-=net.y.toFloat
          }

        }
        case _ => {}
      }
    })
  }

}
