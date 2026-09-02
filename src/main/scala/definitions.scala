import my.borer.*
import my.borer.derivation.MapBasedCodecs.*

sealed trait RootHeightFactor derives Codec.All

object RootHeightFactor {
  def derivedClasses = Schema.listDerivedClasses[RootHeightFactor]

  case object Keep extends RootHeightFactor

}
