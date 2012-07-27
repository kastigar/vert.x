import org.vertx.java.core.Handler
import org.vertx.java.core.SimpleHandler
import org.vertx.java.core.net.NetClient
import org.vertx.java.core.net.NetSocket
import org.vertx.java.core.buffer.Buffer
import org.vertx.java.core.net.NetServer

object Implicits {
  implicit def function1handler[A](f: A => Unit) = new Handler[A] {
    def handle(arg: A) = f(arg)
  }

  implicit def function0handler(f: ()=> Unit) = new SimpleHandler {
    def handle() = f()
  }

  implicit def extendNetClient(nc: NetClient) = new ExtendedNetClient(nc)
  implicit def extendNetSocket(ns: NetSocket) = new ExtendedNetSocket(ns)
  implicit def extendNetServer(ns: NetServer) = new ExtendedNetServer(ns)
}

import Implicits._

class ExtendedNetClient(nc: NetClient) {
  def connect(port: Int, host: String)(handler: NetSocket => Unit) =
    nc.connect(port, host, handler)

  def connect(port: Int)(handler: NetSocket => Unit) =
    nc.connect(port, handler)
}

class ExtendedNetSocket(ns: NetSocket) {
  def handleData(h: Buffer => Unit) = ns.dataHandler(h)
}

class ExtendedNetServer(ns: NetServer) {
  def start(port: Int, host: String)(handler: NetSocket => Unit) = {
    ns.connectHandler(handler)
    ns.listen(port, host)
  }

  def start(port: Int)(handler: NetSocket => Unit) = {
    ns.connectHandler(handler)
    ns.listen(port)
  }
}
