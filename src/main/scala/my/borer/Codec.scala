/*
 * Copyright (c) 2019-2026 Mathias Doenitz
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package my.borer


/**
 * A simple encapsulation of an [[Encoder]] and [[Decoder]] for the same type, as one entity.
 *
 * Sometimes it's easier to supply just a single given for a type, rather than two.
 * As an alternative to writing a separate [[Encoder]] and [[Decoder]] for type [[A]]
 * you can also write a [[Codec]] for [[A]].
 * ([[Encoder]] and [[Decoder]] can be implicitly "unpacked" from a codec.)
 *
 * However, in order to not hinder composability Codecs should only ever be _supplied_, never consumed.
 * So, if you write an encoder, decoder or codec for a generic type, which itself requires implicitly
 * available encoders and/or decoders for certain type parameters (like `Encoder.forOption`, for example)
 * then you should never require implicitly available Codecs, but rather Encoders and Decoders separately.
 */
case class Codec[A](encoder: Encoder[A], decoder: Decoder[A])
object Codec:
  /**
   * Helper type serving only as the target of a `derives Codec.All` clause.
   * The `borer-derivation` module can then provide the respective `derived` method on the companion object.
   */
  case class All[A] private[borer] (delegate: Codec[A])
