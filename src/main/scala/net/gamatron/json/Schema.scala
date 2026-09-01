import scala.quoted.*

def surfaceOf[T] = ???

object Schema {
  inline def listDerivedClasses[T]: List[Nothing] = ${ listDerivedClassesImpl[T] }

  private def listDerivedClassesImpl[T: Type](using Quotes): Expr[List[Nothing]] = {
    import quotes.reflect.*

    Expr.ofList(TypeRepr.of[T].typeSymbol.children.map { child =>
      child.typeRef.asType match
        case '[t] => '{ surfaceOf[t] }
    })
  }
}
