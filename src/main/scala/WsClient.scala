import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Inject
import play.api.libs.ws.ahc._


class WsClient @Inject()(_actor: ActorSystem, _materializer: ActorMaterializer, _client: StandaloneAhcWSClient){
  def client : StandaloneAhcWSClient = _client
  def system : ActorSystem = _actor
}
