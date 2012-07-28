package org.vertx.scala.core
package net

import org.vertx.java.core.net.NetClient
import org.vertx.java.core.net.NetSocket

import Implicits._

class ExtendedNetClient(nc: NetClient) {
  def connect(port: Int, host: String)(handler: NetSocket => Unit) =
    nc.connect(port, host, handler)

  def connect(port: Int)(handler: NetSocket => Unit) =
    nc.connect(port, handler)
}
