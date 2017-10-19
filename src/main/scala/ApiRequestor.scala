import com.google.inject.Inject
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiData(_uri: String, _path: String, _headers: Map[String,String]){
  def uri: String = _uri
  def path: String = _path
  def headers: Map[String,String] = _headers
}

class Response(_body : String, _status : Int){
  def status : Int = _status
  def body : String = _body
}

class ApiRequestor @Inject()(client : WsClient) {

  def get(data : ApiData): Future[Response] = {
    client.client
      .url(data.uri + data.path)
      .addHttpHeaders(data.headers.toSeq : _*)
      .withRequestTimeout(10 seconds)
      .get()
      .map { wsResponse => new Response(wsResponse.body, wsResponse.status) }

  }

}
