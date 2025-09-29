 package object Huffman{
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
 def ocurrencias(cars: List[Char]): List[(Char, Int)] = {
   cars.groupBy(identity).view.mapValues(_.size).toList
 }

 /* Escriba una funcion listaDeHojasOrdenadas, que reciba una lista de frecuencias
 como la producida por la funcion anterior, y devuelva la lista de hojas del arbol
 de Huffman correspondiente, ordenada ascendentemente por la frecuencia de cada
 caracter. */
 def listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja] = {
   frecs.map { case (c, p) => Hoja(c, p) }.sortBy(_.peso)
 }
 def listaUnitaria(arboles: List[ArbolH]): Boolean= {
   arboles match {
     case Nil => false
     case _ :: Nil => true
     case _ => false
   }
 }
 def combinar(arboles: List[ArbolH]): List[ArbolH] = {
   arboles match {
     case Nil => Nil
     case a1 :: a2 :: rest =>
       val nuevo = hacerNodoArbolH(a1, a2)
       nuevo :: rest
   }
 }
 def hastaQue(cond: List[ArbolH]=>Boolean, mezclar:List[ArbolH]=>List[ArbolH] )
 (listaOrdenadaArboles: List[ArbolH]): List[ArbolH] = {...}
 def crearArbolDeHuffman(cars: List[Char]): ArbolH= {...}
 // Part3 3: Decodificar
 type Bit= Int
 def decodificar(arbol: ArbolH, bits: List[Bit]): List[Char] = {...}
 // Parte 4a: Codificando usando arboles de Huffman
 def codificar(arbol: ArbolH)(texto: List[Char]): List[Bit] = {...}
 // Parte 4b: Codificando usando tablas de codigos
 type TablaCodigos=List[(Char, List[Bit])]
 def codigoEnBits(tabla: TablaCodigos)(car: Char): List[Bit] = {...}
 def mezclarTablasDeCodigos(a: TablaCodigos, b: TablaCodigos): TablaCodigos = {...}
 def convertir(arbol: ArbolH): TablaCodigos= {...}
 def codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit] = {...}
 }
