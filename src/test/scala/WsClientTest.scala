import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.{FlatSpec, Matchers}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import play.api.libs.ws.ahc.StandaloneAhcWSClient

class WsClientTest extends FlatSpec with Matchers with MockitoSugar {
  it should "return a client" in {

    val actor = mock[ActorSystem]
    val materializer = mock[ActorMaterializer]
    val client = mock[StandaloneAhcWSClient]
    val result = new WsClient(actor, materializer, client)
    result.client should not be (null)
  }

}