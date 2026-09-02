/*
 * Copyright (c) 2019-2026 Mathias Doenitz
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package my.borer.derivation.helpers

import my.borer.*

def failMissing(r: Reader, typeName: String, mask: Int, fieldNames: List[String]): Nothing = ???

def failMissing(r: Reader, typeName: String, mask: Long, fieldNames: List[String]): Nothing = ???

def failMissing(r: Reader, typeName: String, m0: Long, m1: Long, fieldNames: List[String]): Nothing = ???

def failMissing(r: Reader, typeName: String, ones: Iterator[Int], fieldNames: List[String]): Nothing = ???

def failDuplicateMapKey(r: Reader, key: Long | String, typeName: String): Nothing = ???

def readAdtValue[T, A](r: Reader, typeId: Long, decoder: Decoder[A]): T = ???

def readAdtValue[T, A](r: Reader, typeId: String, decoder: Decoder[A]): T = ???
