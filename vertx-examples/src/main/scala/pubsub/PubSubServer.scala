import org.vertx.java.deploy.Verticle
import org.vertx.scala.core.Implicits._
import scala.collection.JavaConversions._
import org.vertx.scala.core.parsetools.DelimitedBy

class PubSubServer extends Verticle {
  def start() {
    vertx.createNetServer().start(1234) {
      socket =>
        socket.handleData(DelimitedBy("\n")) {
          frame =>
            val line = frame.toString().trim()
            println("Line is " + line)

            line.split("\\,") match {
              case Array("subscribe", topic) =>
                println("Topic is " + topic)
                val set = vertx.sharedData().getSet[String](topic)
                set += socket.writeHandlerID

              case Array("unsubscribe", topic) =>
                vertx.sharedData().getSet(topic).remove(socket.writeHandlerID)

              case Array("publish", topic, message) =>
                println("Publish to topic is " + topic)
                for (actorID <- vertx.sharedData().getSet[String](topic)) {
                  println("Sending to verticle")
                  vertx.eventBus().publish(actorID, message)
                }
            }
        }
    }
  }

}