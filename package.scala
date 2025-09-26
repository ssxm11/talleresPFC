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


def insertionSort[T](comp: Comparador[T]): AlgoritmoOrd[T] = { l =>
    def ordenar(resto: List[T], acumulado: List[T], comparaciones: Int): (List[T], Int) =
      if (resto.isEmpty) (acumulado, comparaciones)
      else {
        val x = resto.head
        val xs = resto.tail
        val (nuevoAcumulado, c) = insert(x, acumulado, comp)
        ordenar(xs, nuevoAcumulado, comparaciones + c)
      }
    ordenar(l, Nil, 0)
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

def comparar[T](a1: AlgoritmoOrd[T], a2: AlgoritmoOrd[T], l: List[T]): (Int, Int) = {
  val (l1, c1) = a1(l)
  val (l2, c2) = a2(l)

  if (l1 == l2) (c1, c2)
  else (-1, -1)
}

} 