package object Comparador {
type AlgoritmoOrd [T ] = List [T ] => ( List [T ] , Int )
type Comparador [T ] = (T,T)=>Boolean
def insert [T] ( e :T, l : List [T ] , comp : Comparador [T ] ) : ( List [T ] , Int ) = {
 // Caso base: lista vacía → simplemente devolvemos el elemento en una lista
    case Nil => (List(e), 0)

    // Si e debe ir antes del primer elemento
    case x :: xs =>
      if (comp(e, x)) {
        (e :: l, 1) // Insertamos delante, hicimos 1 comparación
      } else {
        // Insertar recursivamente en la cola
        val (newTail, c) = insert(e, xs, comp)
        (x :: newTail, c + 1) // agregamos x al inicio y contamos la comparación actual
      }
}
def insertionSort [T] ( comp : Comparador [T ] ) : AlgoritmoOrd [T ] = {
// R e c i b e una l i s t a de e l em en t o s de t i p o T y un comparador de e s o s e l em en t o s
// y d e v u e l v e l a l i s t a ordenada y e l numero de compa rac iones r e a l i z a d a s en una p a r e j a
// usando e l I n s e r t i o n S o r t
5
. . .
}
def menoresQue_noMenoresQue [T ] ( l : List [T ] , v :T, comp : Comparador [T ] ) : ( List [T ] , List [T ] , Int ) = {
l match {
    case Nil => (Nil, Nil, 0)  // Caso base: lista vacía
    case x :: xs =>
      val (menores, noMenores, c) = menoresQue_noMenoresQue(xs, v, comp)
      if (comp(x, v)) {
        (x :: menores, noMenores, c + 1) // x va a la lista de menores
      } else {
        (menores, x :: noMenores, c + 1) // x va a la lista de no menores
      }
  }
}
def quickSort [T] ( comp : Comparador [T ] ) : AlgoritmoOrd [T ] = {
// R e c i b e una l i s t a de e l em en t o s de t i p o T y un comparador de e s o s e l em en t o s
// y d e v u e l v e l a l i s t a ordenada y e l numero de compa rac iones r e a l i z a d a s en una p a r e j a
// Usando e l q u i c k S o r t
case Nil => Nil                       // Caso base: lista vacía
  case x :: xs =>
    // Particionar la lista en menores y mayores (según el comparador)
    val (l1, l2) = xs.partition(e => comp(e, x))

    // Ordenar recursivamente y concatenar
    quickSort(l1, comp) ::: (x :: quickSort(l2, comp))
}
def comparar [T ] ( a1 : AlgoritmoOrd [T ] , a2 : AlgoritmoOrd [T ] , l : List [T ] ) : ( Int , Int ) = {
// R e c i b e dos a l g o r i tm o s de o rdenam ien to y una l i s t a para o rd ena r
// y d e v u e l v e una p a r e j a con e l numero de compa rac iones h e chas por a1 ,
// y e l numero de compa rac iones h e chas por a2 para esa i n s t a n c i a de l en p a r t i c u l a r
// s i l o s dos a l g o r i tm o s dan e l mismo r e s u l t a d o
// s ino , d e v u e l v e (−1,−1)
val ( l1 , c1 ) = a1 ( l )
val ( l2 , c2 ) = a2 ( l )
if ( l1==l2 ) ( c1 , c2 ) else (−1 ,−1)
}
} 