import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.ws.DefaultBodyReadables._
import play.api.libs.ws._

import scala.concurrent.ExecutionContext.Implicits._


object Main {
  def main(args: Array[String]) {
      implicit val system = ActorSystem()
      implicit val materializer = ActorMaterializer()
      val injector = Guice.createInjector(new ApiMatchModule())
      val service = injector.instance[WsClient]
      val call = service.client.url("http://www.google.com").get().map { response ⇒
        val statusText: String = response.statusText
        val body = response.body[String]
        println(s"Got a response $statusText")
      }
      call
        .andThen { case _ => service.client.close() }
        .andThen { case _ => service.system.terminate() }

      val conf = new ParameterParser(args)
  }
}