import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import play.api.libs.ws.{StandaloneWSClient, StandaloneWSRequest, StandaloneWSResponse}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

trait WSRequest extends StandaloneWSRequest {
  type Self = StandaloneWSRequest
  type Response = StandaloneWSResponse
}

class ApiRequestorTest extends FlatSpec with Matchers with MockitoSugar with BeforeAndAfter {

  val client = mock[WsClient]
  val playClient = mock[StandaloneWSClient]
  val request = mock[WSRequest]
  val response = mock[StandaloneWSResponse]

  before {
    when(client.client).thenReturn(playClient)
  }

  "get method call" should "return result from request" in {
    val randomUrl = Faker.RandomUrl
    val randomPath = "/" + Faker.RandomString + "/" + Faker.RandomString
    val randomHeaders = Map(Faker.RandomString -> Faker.RandomString)
    val expectedResponse = "{ 'something': '"+ Faker.RandomString +"'}"
    val statusCode = Faker.RandomInt

    when(playClient.url(randomUrl + randomPath)).thenReturn(request)
    when(request.addHttpHeaders(randomHeaders.toSeq : _*)).thenReturn(request)
    when(request.withRequestTimeout(10 seconds)).thenReturn(request)
    when(request.get()).thenReturn(Future.successful(response))
    when(request.get()).thenReturn(Future.successful(response))
    when(response.body).thenReturn(expectedResponse)
    when(response.status).thenReturn(statusCode)

    val ApiData = new ApiData(randomUrl, randomPath, randomHeaders)
    val requestor = new ApiRequestor(client)
    val result = requestor.get(ApiData)
    val requestResponse = Await.result(result, 0 nano)

    requestResponse.body should be (expectedResponse)
    requestResponse.status should be (statusCode)

    verify(request).withRequestTimeout(10 seconds)
  }
}