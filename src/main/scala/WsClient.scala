import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Inject
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc._


class WsClient @Inject()(_actor: ActorSystem, _materializer: ActorMaterializer, _client: StandaloneWSClient){
  def client : StandaloneWSClient = _client
  def system : ActorSystem = _actor
}
