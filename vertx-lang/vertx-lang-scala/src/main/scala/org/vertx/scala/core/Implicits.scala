package org.vertx.scala.core

import org.vertx.java.core.Handler
import org.vertx.java.core.SimpleHandler

import org.vertx.java.core.net._
import org.vertx.scala.core.net._

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
