import io.bullet.borer.*
import io.bullet.borer.derivation.MapBasedCodecs.*

sealed trait RootHeightFactor derives Codec.All

object RootHeightFactor {
  def derivedClasses = Schema.listDerivedClasses[RootHeightFactor]

  case object Keep extends RootHeightFactor

  case class Factor() extends RootHeightFactor

}
