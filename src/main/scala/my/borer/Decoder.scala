/*
 * Copyright (c) 2019-2026 Mathias Doenitz
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package my.borer



/**
 * Type class responsible for reading an instance of type [[T]] from a [[Reader]].
 */
trait Decoder[T]:
  def read(r: Reader): T

object Decoder:

  /**
   * A [[Decoder]] that might change its encoding strategy if [[T]] has a default value.
   */
  /**
   * Creates a [[Decoder]] from the given function.
   */
  inline def apply[T](decoder: Decoder[T]): Decoder[T] = decoder

  /**
   * Gets a given [[Decoder]] for [[T]].
   */
  inline def of[T: Decoder]: Decoder[T] = summon

  extension [A](underlying: Decoder[A])

    /**
     * Maps the result of the underlying [[Decoder]] with the given function.
     * The function can throw exceptions to terminate the decoding process with an error.
     * If the thrown exception is not a [[Borer.Error]] itself it will be
     * wrapped in a [[Borer.Error.General]] instance.
     */
    /**
     * Changes the default value of the [[Decoder]] to a new value.
     * If the underlying [[Decoder]] is not [[Decoder.DefaultValueAware]]
     * this method has no effect.
     */
    def withDefaultValue(defaultValue: A): Decoder[A] = ???

  extension [T](underlying: => Decoder[T])
    /**
     * Wraps a [[Decoder]] definition with lazy initialization.
     */
    def recursive: Decoder[T] = ???

  given [T](using codec: Codec[T]): Decoder[T]  = ???
  given [T](using ev: Codec.All[T]): Decoder[T] = ???

  /**
   * Helper type serving only as the target of a `derives Encoder.All` clause.
   * The `borer-derivation` module can then provide the respective `derived` method on the companion object.
   */
  final class All[A](delegate: Decoder[A]) extends Decoder[A]:
    inline def read(r: Reader): A = ???

  object All

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  given forNull: Decoder[Null]       = ???
  given forBoolean: Decoder[Boolean] = ???
  given forInt: Decoder[Int]         = ???
  given forLong: Decoder[Long]       = ???
  given forFloat: Decoder[Float]     = ???
  given forDouble: Decoder[Double]   = ???
  given forString: Decoder[String]   = ???

  given forChar: Decoder[Char] = ???
  def forChar(intDecoder: Decoder[Int]): Decoder[Char] = ???
  given forByte: Decoder[Byte] = ???
  def forByte(intDecoder: Decoder[Int]): Decoder[Byte] = ???
  given forShort: Decoder[Short] = ???
  def forShort(intDecoder: Decoder[Int]): Decoder[Short] = ???

/**
 * An [[AdtDecoder]] is a [[Decoder]] whose `read` method expects to read an envelope
 * (holding the type id) around the actual value encoding.
 *
 * In order to be able to collapse several envelope levels into a single one, when several [[AdtDecoder]] instances
 * call each other, this type also provides `read` overloads which don't read the type id envelope themselves
 * but can receive the type id from the outside.
 */
trait AdtDecoder[T] extends Decoder[T]:

  def read(r: Reader, typeId: Long): T

  def read(r: Reader, typeId: String): T
