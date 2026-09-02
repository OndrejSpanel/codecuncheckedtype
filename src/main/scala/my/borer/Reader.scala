package my.borer

import scala.collection.Factory

final class InputReader[Config](parser: Any, val target: Target):
  inline def apply[T: Decoder]: T = read[T]()

  def readNull(): Null = ???
  def readBoolean(): Boolean = ???
  def readChar(): Char = ???
  def readByte(): Byte = ???
  def readShort(): Short = ???
  def readInt(): Int = ???
  def readLong(): Long = ???
  def readFloat(): Float = ???
  def readDouble(): Double = ???
  def readString(): String = ???

  def hasLong: Boolean = ???
  def tryReadLong(value: Long): Boolean = ???
  def tryReadLongCompare(value: Long): Int = ???
  def hasString: Boolean = ???
  def tryReadString(value: String): Boolean = ???
  def tryReadStringCompare(value: String): Int = ???

  def readArrayHeader(): Long = ???
  def hasArrayHeader: Boolean = ???
  def readArrayHeader(length: Int): this.type = ???
  def readArrayStart(): this.type = ???
  def tryReadArrayStart(): Boolean = ???

  def readMapHeader(): Long = ???
  def hasMapHeader: Boolean = ???
  def readMapHeader(length: Int): this.type = ???
  def readMapStart(): this.type = ???
  def hasMapStart: Boolean = ???
  def tryReadMapStart(): Boolean = ???

  def readBreak(): this.type = ???
  def tryReadBreak(): Boolean = ???

  inline def read[T]()(using decoder: Decoder[T]): T = decoder.read(this)
  def readUntilBreak[M[_], T: Decoder]()(using factory: Factory[T, M[T]]): M[T] = ???
  def readUntilBreak[T](zero: T)(f: T => T): T = ???

  def skipElement(): this.type = ???
  inline def skipTwoElements(): this.type = skipElement().skipElement()

  def readArrayOpen(arity: Long): Boolean = ???
  def readArrayClose[T](unbounded: Boolean, value: T): T = ???
  def readMapOpen(arity: Long): Boolean = ???
  def readMapClose[T](unbounded: Boolean, value: T): T = ???

  def validationFailure(msg: String): Nothing = ???
  def overflow(msg: String): Nothing = ???
  def unexpectedDataItem(expected: String): Nothing = ???
  def unexpectedDataItem(expected: String, actual: String): Nothing = ???
