package object Comparador {
type AlgoritmoOrd [T ] = List [T ] => ( List [T ] , Int )
type Comparador [T ] = (T,T)=>Boolean
def insert[T](e: T, l: List[T], comp: Comparador[T]): (List[T], Int) = {
  if (l.isEmpty) {
    (List(e), 0) // caso base: lista vacía
  } else {
    val x = l.head
    val xs = l.tail

    if (comp(e, x)) {
      (e :: l, 1) // insertar delante y contar una comparación
    } else {
      val (newTail, c) = insert(e, xs, comp)
      (x :: newTail, c + 1) // reconstruir lista y acumular comparaciones
    }
  }
}

def insertionSort [T] ( comp : Comparador [T ] ) : AlgoritmoOrd [T ] = {
// R e c i b e una l i s t a de e l em en t o s de t i p o T y un comparador de e s o s e l em en t o s
// y d e v u e l v e l a l i s t a ordenada y e l numero de compa rac iones r e a l i z a d a s en una p a r e j a
// usando e l I n s e r t i o n S o r t
5
. . .
}
def menoresQue_noMenoresQue[T](l: List[T], v: T, comp: Comparador[T]): (List[T], List[T], Int) = {
  if (l.isEmpty) {
    (Nil, Nil, 0) 
  } else {
    val x = l.head
    val xs = l.tail

    val (menores, noMenores, c) = menoresQue_noMenoresQue(xs, v, comp)

    if (comp(x, v)) {
      (x :: menores, noMenores, c + 1) // x va a la lista de menores
    } else {
      (menores, x :: noMenores, c + 1) // x va a la lista de no menores
    }
  }
}

def quickSort[T](comp: Comparador[T]): AlgoritmoOrd[T] = {
  def ordenar(l: List[T]): (List[T], Int) = {
    if (l.isEmpty) {
      (Nil, 0) // caso base: lista vacía
    } else {
      val pivot = l.head
      val xs = l.tail

      
      val (menores, noMenores, cPart) = menoresQue_noMenoresQue(xs, pivot, comp)

      // ordenar recursivamente las dos partes
      val (menoresOrd, cMenores)   = ordenar(menores)
      val (noMenoresOrd, cNoMenores) = ordenar(noMenores)

      // concatenar resultado y acumular comparaciones
      (menoresOrd ++ (pivot :: noMenoresOrd), cPart + cMenores + cNoMenores)
    }
  }
  ordenar

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