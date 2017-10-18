import com.google.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSAuthScheme

import scala.concurrent.Future

class ApiData(_uri: String, _path: String, _headers: Map[String,String]){
  def uri: String = _uri
  def path: String = _path
  def headers: Map[String,String] = _headers
}

class ApiRequestor @Inject()(client : WsClient) {

  def get(data : ApiData): Future[JsValue] = {
    client.client
      .url(data.uri + data.path)
      .addHttpHeaders(data.headers.toSeq : _*)
    Future.successful(Json.parse("{}"))
  }

}
