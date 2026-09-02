package my.borer

case object Cbor extends Target

sealed abstract class Target

sealed abstract class AdtEncodingStrategy:
  def writeAdtEnvelopeOpen(w: Writer, typeName: String): w.type
  def writeAdtEnvelopeClose(w: Writer, typeName: String): w.type
  def readAdtEnvelopeOpen(r: Reader, typeName: String): Boolean
  def readAdtEnvelopeClose(r: Reader, openResult: Boolean, typeName: String): Unit

object AdtEncodingStrategy:
  given Default: AdtEncodingStrategy with
    def writeAdtEnvelopeOpen(w: Writer, typeName: String): w.type = ???
    def writeAdtEnvelopeClose(w: Writer, typeName: String): w.type = ???
    def readAdtEnvelopeOpen(r: Reader, typeName: String): Boolean = ???
    def readAdtEnvelopeClose(r: Reader, openResult: Boolean, typeName: String): Unit = ???

final class Writer(val target: Target):
  inline def writingCbor: Boolean = target eq Cbor

  def writeNull(): this.type = ???
  def writeBoolean(value: Boolean): this.type = ???
  def writeChar(value: Char): this.type = ???
  def writeByte(value: Byte): this.type = ???
  def writeShort(value: Short): this.type = ???
  def writeInt(value: Int): this.type = ???
  def writeLong(value: Long): this.type = ???
  def writeFloat(value: Float): this.type = ???
  def writeDouble(value: Double): this.type = ???
  def writeString(value: String): this.type = ???

  def writeMapHeader(length: Int): this.type = ???
  def writeMapStart(): this.type = ???
  def writeBreak(): this.type = ???
  def writeEmptyMap(): this.type = ???

  def write[T](value: T)(using encoder: Encoder[T]): this.type = ???
