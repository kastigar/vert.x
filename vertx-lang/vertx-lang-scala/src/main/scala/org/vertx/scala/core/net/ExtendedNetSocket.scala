package org.vertx.scala.core
package net

import org.vertx.java.core.net.NetSocket
import org.vertx.java.core.buffer.Buffer

import Implicits._

class ExtendedNetSocket(ns: NetSocket) {
  def handleData(h: Buffer => Unit) = ns.dataHandler(h)
}