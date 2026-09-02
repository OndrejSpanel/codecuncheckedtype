/*
 * Copyright (c) 2019-2026 Mathias Doenitz
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package my.borer



/**
 * Type class responsible for writing an instance of type [[T]] to a [[Writer]].
 */
trait Encoder[T]:
  def write(w: Writer, value: T): Writer

object Encoder:

  /**
   * An [[Encoder]] that might change its encoding strategy if [[T]] has a default value.
   */
  /**
   * An [[Encoder]] that might not actually produce any output for certain values of [[T]]
   * (e.g. because "not-present" already carries sufficient information).
   */
  trait PossiblyWithoutOutput[T] extends Encoder[T]:
    def producesOutputFor(value: T): Boolean

  /**
   * Creates an [[Encoder]] from the given function.
   */
  inline def apply[T](encoder: Encoder[T]): Encoder[T] = encoder

  /**
   * Gets a given [[Encoder]] for [[T]].
   */
  inline def of[T: Encoder]: Encoder[T] = summon

  extension [A](underlying: Encoder[A])
    def withDefaultValue(defaultValue: A): Encoder[A] = ???
    def unwrap: Encoder[A] = ???

  extension [T](underlying: => Encoder[T])
    /**
     * Wraps an [[Encoder]] definition with lazy initialization.
     */
    def recursive: Encoder[T] = ???

  given [T](using codec: Codec[T]): Encoder[T]  = ???
  given [T](using ev: Codec.All[T]): Encoder[T] = ???

  /**
   * Helper type serving only as the target of a `derives Encoder.All` clause.
   * The `borer-derivation` module can then provide the respective `derived` method on the companion object.
   */
  final class All[A](delegate: Encoder[A]) extends Encoder[A]:
    inline def write(w: Writer, value: A): Writer = ???

  object All

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  given forNull: Encoder[Null]       = ???
  given forBoolean: Encoder[Boolean] = ???
  given forChar: Encoder[Char]       = ???
  given forByte: Encoder[Byte]       = ???
  given forShort: Encoder[Short]     = ???
  given forInt: Encoder[Int]         = ???
  given forLong: Encoder[Long]       = ???
  given forFloat: Encoder[Float]     = ???
  given forDouble: Encoder[Double]   = ???
  given forString: Encoder[String]   = ???

/**
 * An [[AdtEncoder]] is an [[Encoder]] which encodes its values with an envelope holding the value's type id.
 *
 * It doesn't change or add to the outside interface of [[Encoder]] but merely serves as a marker
 * signaling that it takes on the responsibility of encoding the type id in addition to the value itself.
 * This allows outside encoders calling an [[AdtEncoder]] to delegate this responsibility rather than performing
 * the task themselves.
 */
trait AdtEncoder[T] extends Encoder[T]
