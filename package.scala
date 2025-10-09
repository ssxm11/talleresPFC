package object Huffman {
 abstract class ArbolH
 case class Nodo (izq: ArbolH, der: ArbolH,
 cars: List[Char] , peso: Int) extends ArbolH
 case class Hoja(car: Char, peso: Int) extends ArbolH
 // Parte 1: Funciones esenciales y sencillas

 /*Las hojas de arbol corresponden a un caracter y su frecuencia (peso). Los nodos
 internos ademas de contener sus hijos izquierdo y derecho, contienen la lista de caracteres
 que hay en sus hojas, y la suma de las frecuencias (peso) de esas hojas.
 Para empezar, implemente las funciones peso y cars que dado un arbol de Huffman
 devuelven su peso y la lista de caracteres que codifica, respectivamente.*/
 def peso(arbol: ArbolH): Int =arbol match{
    case Hoja(_, p) => p
      case Nodo(_, _, _, p) => p
}
 def cars(arbol: ArbolH): List[Char] =arbol match{
   case Hoja(c, _) => List(c)
   case Nodo(_, _, cs, _) => cs
 }

 def hacerNodoArbolH(izq: ArbolH, der: ArbolH) =
 Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) +peso(der))
 // Parte 2: Construyendo arboles de Huffman
 /*
* En este taller estamos trabajando con listas de caracteres.
* La funcion cadenaALista crea una lista de caracteres correspondiente a una cadena dada
 */


 def cadenaALista(cad: String): List[Char] =cad.toList
    /*Dado un texto, es posible calcular y construir un arbol de Hu man optimo, en el
 sentido en que la codi cacion de ese texto sera de longitud mnima, y sin perdida de
 informacion (es decir, se puede decodi car y recuperar el texto original sin errores). El
 objetivo de esta seccion es que usted escriba una funcion crearArbolDeHuffman que reciba
 un texto en forma de lista de caracteres y devuelva el arbol de Human asociado a ese
 texto.
 Para ayudarlo con esta tarea, siga los siguientes pasos:
 1. Escriba una funcion ocurrencias, que reciba un texto en forma de lista de caracteres
 y devuelva la lista con la frecuencia en que cada caracter aparece en el texto*/
 def ocurrencias(cars: List[Char]): List[(Char, Int)] = cars match {
   case Nil =>
    Nil

  case x :: xs =>
    val (iguales, distintos) = xs.partition(_ == x)
    (x, iguales.length + 1) :: ocurrencias(distintos)
 }

 /* Escriba una funcion listaDeHojasOrdenadas, que reciba una lista de frecuencias
 como la producida por la funcion anterior, y devuelva la lista de hojas del arbol
 de Huffman correspondiente, ordenada ascendentemente por la frecuencia de cada
 caracter. */
 def listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja] = {
    // comparador local: ordena ascendentemente por el peso (frecuencia)
  def compararPorPeso(h1: Hoja, h2: Hoja): Boolean = h1.peso < h2.peso

  // inserta una hoja en la posición correcta dentro de una lista ordenada
  def insertarOrdenado(h: Hoja, hojas: List[Hoja]): List[Hoja] = hojas match {
    case Nil => List(h)
    case x :: xs =>
      if (compararPorPeso(h, x)) h :: hojas
      else x :: insertarOrdenado(h, xs)
  }

  // cuerpo principal que transforma y ordena recursivamente
  frecs match {
    case Nil => Nil
    case (c, p) :: rest =>
      val hojasOrdenadas = listaDeHojasOrdenadas(rest)
      insertarOrdenado(Hoja(c, p), hojasOrdenadas)
  }
 }
    // Construye la lista ordenada de arboles (hojas) a partir de una lista de caracteres
    def listaOrdenadaArboles(carsList: List[Char]): List[ArbolH] =
      listaDeHojasOrdenadas(ocurrencias(carsList))
 def listaUnitaria(arboles: List[ArbolH]): Boolean= {
   arboles match {
     case Nil => false
     case _ :: Nil => true
     case _ => false
   }
 }
 def combinar(arboles: List[ArbolH]): List[ArbolH] = arboles match {
  case Nil => Nil                               // caso base: lista vacía
  case _ :: Nil => arboles                      // caso con un solo árbol, nada que combinar

  case a1 :: a2 :: resto =>
    val nuevo = hacerNodoArbolH(a1, a2)         // combina los dos árboles más livianos

    def insertarOrdenado(a: ArbolH, lista: List[ArbolH]): List[ArbolH] = lista match {
      case Nil => List(a)
      case x :: xs =>
        if (peso(a) <= peso(x)) a :: lista
        else x :: insertarOrdenado(a, xs)
    }

    insertarOrdenado(nuevo, resto)              // inserta manteniendo el orden por peso/frecuencia
}
def hastaQue(cond: List[ArbolH] => Boolean, mezclar: List[ArbolH] => List[ArbolH])(listaOrdenadaArboles: List[ArbolH]): List[ArbolH] =
  if (cond(listaOrdenadaArboles)) listaOrdenadaArboles
  else hastaQue(cond, mezclar)(mezclar(listaOrdenadaArboles))



def crearArbolDeHuffman(cars: List[Char]): ArbolH = {
  hastaQue(_.size == 1, combinar)(listaOrdenadaArboles(cars)).head
}


 // Part3 3: Decodificar
 type Bit= Int
def decodificar(arbol: ArbolH, bits: List[Bit]): List[Char] = {
  def recorrer(nodo: ArbolH, bs: List[Bit]): List[Char] = nodo match {
    case Hoja(c, _) =>
      // Llegamos a una hoja -> devolvemos el caracter y reiniciamos desde la raíz
      c :: (if (bs.isEmpty) Nil else recorrer(arbol, bs))

    case Nodo(izq, der, _, _) =>
      bs match {
        case Nil       => Nil 
        case b :: resto =>
          if (b == 0) recorrer(izq, resto)
          else recorrer(der, resto)
      }
  }

  recorrer(arbol, bits)
  }
 // Parte 4a: Codificando usando arboles de Huffman
 def codificar(arbol: ArbolH)(texto: List[Char]): List[Bit] = {
  def codigoDeChar(c: Char, nodo: ArbolH, camino: List[Bit]): List[Bit] = nodo match {
    case Hoja(car, _) if car == c =>
      camino

    case Nodo(izq, der, _, _) =>
      val izqCamino = codigoDeChar(c, izq, camino :+ 0)
      if (izqCamino.nonEmpty) izqCamino
      else codigoDeChar(c, der, camino :+ 1)

    case _ =>
      Nil
  }

  texto.flatMap(c => codigoDeChar(c, arbol, Nil))
 }
 // Parte 4b: Codificando usando tablas de codigos
 type TablaCodigos=List[(Char, List[Bit])]
 def codigoEnBits(tabla: TablaCodigos)(car: Char): List[Bit] = tabla match {
  case Nil => Nil // caso base
  case (c, bits) :: resto =>
    if (c == car) bits 
    else codigoEnBits(resto)(car) // seguimos buscando en el resto
    }
 def mezclarTablasDeCodigos(a: TablaCodigos, b: TablaCodigos): TablaCodigos = {
  val izquierda = a.map { case (c, bits) => (c, 0 :: bits) }
  val derecha   = b.map { case (c, bits) => (c, 1 :: bits) }
  izquierda ++ derecha
 }
 def convertir(arbol: ArbolH): TablaCodigos=arbol match {
  case Hoja(c, _) => List((c, List()))
  case Nodo(izq, der, _, _) =>
    val izqCod = convertir(izq)
    val derCod = convertir(der)
    mezclarTablasDeCodigos(izqCod, derCod)
    }
 def codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit] = {
  val tabla = convertir(arbol)
  texto.flatMap(codigoEnBits(tabla))
 }
 }
