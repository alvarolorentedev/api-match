import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

class ApiMatchModule()(implicit actorSystem : ActorSystem, materializer : ActorMaterializer) extends AbstractModule with ScalaModule {
  def configure : Unit = {
    bind[ActorSystem].toInstance(actorSystem)
    bind[ActorMaterializer].toInstance(materializer)
    bind[StandaloneWSClient].toInstance(StandaloneAhcWSClient())
    bind[WsClient]
  }
}
