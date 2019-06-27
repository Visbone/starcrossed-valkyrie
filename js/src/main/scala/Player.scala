import pixiscalajs.PIXI
import pixiscalajs.PIXI.Point


case class Player(var x:Float,var y:Float) extends Entity{
  val body = PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Fsurvivor.png?v=1561147595564",true)
  val fist1= PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Ffist.png?v=1561147933663",true)
  val fist2= PIXI.Sprite.fromImage("https://cdn.glitch.com/fb2531f1-6ba2-4512-aa7b-ce93b5f02fe3%2Ffist.png?v=1561147933663",true)
  fist1.y = 13
  fist2.y = 13

  fist1.x = -12
  fist2.x = 12

  body.x = 0
  body.y = 0
  override val display = new PIXI.Container
  display.addChild(body)
  display.addChild(fist1)
  display.addChild(fist2)

  override def updateSprite(camera: Point, center: Point, scale: Point): Unit = {
    super.updateSprite(camera, center, scale)
    display.pivot.x=16
    display.pivot.y=16
  }

  override val visible: Boolean = true
  def testUpdate(net:Point):Int={
    val player = this
    player.x+=net.x.toFloat
    var out = 0
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            player.x-=net.x.toFloat
          }

        }
        case x:TestMonster => {
          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            out=1

          }
        }
        case _ => {}
      }
    })

    player.y+=net.y.toFloat
    Entity.entities.map(ent => {
      ent match {
        case x:TestWall => {

          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            player.y-=net.y.toFloat
          }

        }
        case x:TestMonster => {
          if(Math.abs(player.x - x.x)<30 && Math.abs(player.y - x.y)<30){
            out=1

          }
        }
        case _ => {}
      }
    })
    out
  }

}
