import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import play.api.libs.json.Json
import play.api.libs.ws.{StandaloneWSClient, StandaloneWSRequest}

import scala.concurrent.Await
import scala.concurrent.duration._

trait WSRequest extends StandaloneWSRequest {
  type Self = StandaloneWSRequest
}

class ApiRequestorTest extends FlatSpec with Matchers with MockitoSugar with BeforeAndAfter {

  val client = mock[WsClient]
  val playClient = mock[StandaloneWSClient]
  val request = mock[WSRequest]

  before {
    when(client.client).thenReturn(playClient)
  }

  "get" should "return result from request" in {
    val randomUrl = Faker.RandomUrl
    val randomPath = "/" + Faker.RandomString + "/" + Faker.RandomString
    when(playClient.url(randomUrl + randomPath)).thenReturn(request)

    val randomHeaders = Map(Faker.RandomString -> Faker.RandomString)

    when(request.addHttpHeaders(randomHeaders.toSeq : _*)).thenReturn(request)
    val ApiData = new ApiData(randomUrl, randomPath, randomHeaders)
    val requestor = new ApiRequestor(client)
    val result = requestor.get(ApiData)

    val expectedResponse = Json.parse("{}")
    val response = Await.result(result, 0 nano)

    response should be (expectedResponse)

    verify(playClient).url(randomUrl + randomPath)
    verify(request).addHttpHeaders(randomHeaders.toSeq : _*)


//    client.client expects '
//    true should be (true)
  }
}