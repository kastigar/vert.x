package org.vertx.scala.core
package parsetools

import org.vertx.java.core.parsetools.RecordParser
import org.vertx.java.core.buffer.Buffer

import Implicits._

trait ParserSpecifier extends ((Buffer => Unit) => RecordParser)

object DelimitedBy {
  def apply(delim: String) = new ParserSpecifier {
    def apply(handler: Buffer => Unit) = RecordParser.newDelimited(delim, handler)
  }

  def apply(delim: Array[Byte]) = new ParserSpecifier {
    def apply(handler: Buffer => Unit) = RecordParser.newDelimited(delim, handler)
  }
}

object FixedSize {
  def apply(size: Int) = new ParserSpecifier {
    def apply(handler: Buffer => Unit) = RecordParser.newFixed(size, handler)
  }
}