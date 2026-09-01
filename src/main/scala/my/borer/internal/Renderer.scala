/*
 * Copyright (c) 2019-2026 Mathias Doenitz
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package my.borer.internal

import my.borer.{Output, Receiver}

/**
 * Common parent type of [[my.borer.cbor.CborRenderer]] and [[my.borer.json.JsonRenderer]]
 */
abstract private[borer] class Renderer extends Receiver:
  def out: Output
