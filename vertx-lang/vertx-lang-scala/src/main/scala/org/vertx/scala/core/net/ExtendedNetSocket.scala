package org.vertx.scala.core
package net

import org.vertx.java.core.net.NetSocket
import org.vertx.java.core.buffer.Buffer
import org.vertx.scala.core.parsetools.ParserSpecifier

import Implicits._

class ExtendedNetSocket(ns: NetSocket) {
  type DataHandler = Buffer => Unit

  def handleData(h: DataHandler) = ns.dataHandler(h)
  def handleData(ps: ParserSpecifier)(h: DataHandler) = ns.dataHandler(ps(h))
}