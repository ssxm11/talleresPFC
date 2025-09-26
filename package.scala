 package object Huffman{
 abstract class ArbolH
 case class Nodo (izq: ArbolH, der: ArbolH,
 cars: List[Char] , peso: Int) extends ArbolH
 case class Hoja(car: Char, peso: Int) extends ArbolH
 // Parte 1: Funciones esenciales y sencillas
 def peso(arbol: ArbolH): Int =arbol match{ ...}
 def cars(arbol: ArbolH): List[Char] =arbol match {...}
 def hacerNodoArbolH(izq: ArbolH, der: ArbolH) =
 Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) +peso(der))
 // Parte 2: Construyendo arboles de Huffman
 /*
* En este taller estamos trabajando con listas de caracteres.
* La funcion cadenaALista crea una lista de caracteres correspondiente a una cadena dada
 */
 def cadenaALista(cad: String): List[Char] =cad.toList
 def ocurrencias(cars: List[Char]): List[(Char, Int)] = {...}
 def listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja] = {...}
 def listaUnitaria(arboles: List[ArbolH]): Boolean= {...}
 def combinar(arboles: List[ArbolH]): List[ArbolH] = {...}
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
