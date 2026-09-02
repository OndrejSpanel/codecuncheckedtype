import my.borer.*
import my.borer.MapBasedCodecs.*

sealed trait RootHeightFactor derives Codec.All

object RootHeightFactor:
  case object Keep extends RootHeightFactor
