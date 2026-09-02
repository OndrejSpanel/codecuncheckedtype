package my.borer

import scala.quoted.*

object MapBasedCodecs:

  extension (c: Codec.All.type)
    inline def derived[A]: Codec.All[A] = ${ derivedImpl[A] }

  private def derivedImpl[T: Type](using quotes: Quotes): Expr[Codec.All[T]] =
    import quotes.reflect.*
    TypeRepr.of[T].typeSymbol.children.head.typeRef.asType match
      case '[b] =>
        '{
          if ((??? : T).isInstanceOf[b]) ??? else ???
          Codec.All[T]()
        }


object Codec:
  case class All[A]()
