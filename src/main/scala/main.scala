import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._

import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]) {
      implicit val system = ActorSystem()
      implicit val materializer = ActorMaterializer()
      val injector = Guice.createInjector(new ApiMatchModule())
      val service = injector.instance[WsClient]
      val requestor = injector.instance[ApiRequestor]
      requestor.get(new ApiData("http://www.google.com","",Map())).map {
        response =>println("call to google returned code: " + response.status)

      }

      val conf = new ParameterParser(args)
  }
}