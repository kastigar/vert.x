package org.vertx.scala.core
package net

import org.vertx.java.core.net.NetServer
import org.vertx.java.core.net.NetSocket

import Implicits._

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
