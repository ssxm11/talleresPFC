import Huffman._

////////////////////////////////////////////////////////
// PRUEBAS PARA peso(arbol: ArbolH): Int
////////////////////////////////////////////////////////

// Caso 1: Hoja simple con peso 1
val pesoPrueba1 = peso(Hoja('a', 1))
// Esperado: 1

// Caso 2: Hoja con peso mayor
val pesoPrueba2 = peso(Hoja('b', 15))
// Esperado: 15
/* 
 * 1-2: Casos base (Hoja) con diferentes pesos
*/

// Caso 3: Nodo simple con dos hojas, recursivo simple (Nodo con dos hojas)
val pesoPrueba3 = peso(hacerNodoArbolH(Hoja('x', 1), Hoja('e', 1)))
// Esperado: 2

// Caso 4: Árbol con varios niveles, recursivo con estructura balanceada.
val arbolComplejo1 = hacerNodoArbolH(
  hacerNodoArbolH(Hoja('a', 3), Hoja('b', 5)),
  hacerNodoArbolH(Hoja('c', 2), Hoja('d', 4))
)
val pesoPrueba4 = peso(arbolComplejo1)
// Esperado: 14


// Caso 5: Árbol recursivo con estructura asimétrica profunda
val arbolAsimetrico = hacerNodoArbolH(
  Hoja('z', 10),
  hacerNodoArbolH(
    Hoja('y', 3),
    hacerNodoArbolH(Hoja('x', 1), Hoja('w', 2))
  )
)
val pesoPrueba5 = peso(arbolAsimetrico)
// Esperado: 16


////////////////////////////////////////////////////////
// PRUEBAS PARA cars(arbol: ArbolH): List[Char]
////////////////////////////////////////////////////////


// Caso 1: Hoja simple
val carsPrueba1 = cars(Hoja('a', 1))
// Esperado: List('a')

// Caso 2: Nodo con dos hojas
val carsPrueba2 = cars(hacerNodoArbolH(Hoja('x', 1), Hoja('y', 2)))
// Esperado: List('x', 'y')

// Caso 3: Árbol con tres niveles
val arbolTresNiveles = hacerNodoArbolH(
  hacerNodoArbolH(Hoja('a', 1), Hoja('b', 2)),
  Hoja('c', 3)
)
val carsPrueba3 = cars(arbolTresNiveles)
// Esperado: List('a', 'b', 'c')

// Caso 4: Árbol balanceado con 4 hojas
val arbolBalanceado = hacerNodoArbolH(
  hacerNodoArbolH(Hoja('d', 1), Hoja('e', 1)),
  hacerNodoArbolH(Hoja('f', 1), Hoja('g', 1))
)
val carsPrueba4 = cars(arbolBalanceado)
// Esperado: List('d', 'e', 'f', 'g')

// Caso 5: Árbol asimétrico profundo
val carsPrueba5 = cars(arbolAsimetrico)
// Esperado: List('z', 'y', 'x', 'w')





////////////////////////////////////////////////////////
// PRUEBAS PARA ocurrencias(cars: List[Char]): List[(Char, Int)]
////////////////////////////////////////////////////////


// Caso 1: Lista vacía
val ocurrenciasPrueba1 = ocurrencias(List())
// Esperado: List()

// Caso 2: Un solo carácter
val ocurrenciasPrueba2 = ocurrencias(List('a'))
// Esperado: List(('a', 1))

// Caso 3: Múltiples ocurrencias del mismo carácter
val ocurrenciasPrueba3 = ocurrencias(List('a', 'a', 'a', 'a'))
// Esperado: List(('a', 4))

// Caso 4: Caracteres diferentes sin repetición
val ocurrenciasPrueba4 = ocurrencias(List('a', 'b', 'c', 'd'))
// Esperado: List(('a', 1), ('b', 1), ('c', 1), ('d', 1))

// Caso 5: Mezcla de caracteres con diferentes frecuencias
val ocurrenciasPrueba5 = ocurrencias(cadenaALista("aabbbcccc"))
// Esperado: List(('a', 2), ('b', 3), ('c', 4))

// Caso 6: Texto real con espacios
val ocurrenciasPrueba6 = ocurrencias(cadenaALista("hola mundo"))
// Esperado: List(('h', 1), ('o', 2), ('l', 1), ('a', 1), (' ', 1), ('m', 1), ('u', 1), ('n', 1), ('d', 1))





////////////////////////////////////////////////////////
// PRUEBAS PARA listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja]
////////////////////////////////////////////////////////


// Caso 1: Lista vacía
val hojasPrueba1 = listaDeHojasOrdenadas(List())
// Esperado: List()

// Caso 2: Una sola frecuencia
val hojasPrueba2 = listaDeHojasOrdenadas(List(('a', 5)))
// Esperado: List(Hoja('a', 5))

// Caso 3: Frecuencias ya ordenadas
val hojasPrueba3 = listaDeHojasOrdenadas(List(('a', 1), ('b', 3), ('c', 5)))
// Esperado: List(Hoja('a', 1), Hoja('b', 3), Hoja('c', 5))

// Caso 4: Frecuencias en orden inverso
val hojasPrueba4 = listaDeHojasOrdenadas(List(('z', 10), ('y', 5), ('x', 1)))
// Esperado: List(Hoja('x', 1), Hoja('y', 5), Hoja('z', 10))

// Caso 5: Frecuencias desordenadas
val hojasPrueba5 = listaDeHojasOrdenadas(List(('d', 3), ('a', 1), ('c', 5), ('b', 2)))
// Esperado: List(Hoja('a', 1), Hoja('b', 2), Hoja('d', 3), Hoja('c', 5))

// Caso 6: Frecuencias con valores repetidos
val hojasPrueba6 = listaDeHojasOrdenadas(List(('a', 2), ('b', 2), ('c', 2)))
// Esperado: List(Hoja('a', 2), Hoja('b', 2), Hoja('c', 2)) - orden de inserción





////////////////////////////////////////////////////////
// PRUEBAS PARA listaUnitaria(arboles: List[ArbolH]): Boolean
////////////////////////////////////////////////////////


// Caso 1: Lista vacía
val unitariaPrueba1 = listaUnitaria(List())
// Esperado: false

// Caso 2: Lista con un elemento (Hoja)
val unitariaPrueba2 = listaUnitaria(List(Hoja('a', 1)))
// Esperado: true

// Caso 3: Lista con un elemento (Nodo)
val unitariaPrueba3 = listaUnitaria(List(hacerNodoArbolH(Hoja('a', 1), Hoja('b', 2))))
// Esperado: true

// Caso 4: Lista con dos elementos
val unitariaPrueba4 = listaUnitaria(List(Hoja('a', 1), Hoja('b', 2)))
// Esperado: false

// Caso 5: Lista con múltiples elementos
val unitariaPrueba5 = listaUnitaria(List(Hoja('a', 1), Hoja('b', 2), Hoja('c', 3)))
// Esperado: false





////////////////////////////////////////////////////////
// PRUEBAS PARA combinar(arboles: List[ArbolH]): List[ArbolH]
////////////////////////////////////////////////////////

// Caso 1: Lista vacía
val combinarPrueba1 = combinar(List())
// Esperado: List()

// Caso 2: Lista con un solo árbol
val combinarPrueba2 = combinar(List(Hoja('a', 5)))
// Esperado: List(Hoja('a', 5))

// Caso 3: Lista con dos árboles
val combinarPrueba3 = combinar(List(Hoja('a', 1), Hoja('b', 2)))
// Esperado: List(Nodo(...)) con peso 3

// Caso 4: Lista con tres árboles ordenados.
val a1 = Hoja('a', 1)
val b2 = Hoja('b', 2)
val c5 = Hoja('c', 5)
val combinarPrueba4 = combinar(List(a1, b2, c5))
// Esperado: List(Nodo(a1, b2, List('a', 'b'), 3), c5)

// Caso 5: Lista donde el resultado debe insertarse al principio
val combinarPrueba5 = combinar(List(Hoja('a', 1), Hoja('b', 1), Hoja('c', 10)))
// Esperado: List(Nodo(...peso 2), Hoja('c', 10))

// Caso 6: Lista donde el resultado se inserta en medio
val a1 = Hoja('a', 1)
val b2 = Hoja('b', 2)
val c5 = Hoja('c', 5)
val d10 = Hoja('d', 10)
val combinarPrueba6 = combinar(List(a1, b2, c5, d10))
// Esperado: List(Nodo(a1, b2, List('a', 'b'), 3), c5, d10).




////////////////////////////////////////////////////////
// PRUEBAS PARA hastaQue
////////////////////////////////////////////////////////


// Caso 1: Lista que ya cumple la condición
val hastaQuePrueba1 = hastaQue(listaUnitaria, combinar)(List(Hoja('a', 1)))
// Esperado: List(Hoja('a', 1))

// Caso 2: Lista con dos elementos
val hastaQuePrueba2 = hastaQue(listaUnitaria, combinar)(List(Hoja('a', 1), Hoja('b', 2)))
// Esperado: List(Nodo(...)) con un solo elemento

// Caso 3: Lista con tres elementos
val hastaQuePrueba3 = hastaQue(listaUnitaria, combinar)(
  List(Hoja('a', 1), Hoja('b', 2), Hoja('c', 3))
)
// Esperado: List(Nodo(...)) árbol completo

// Caso 4: Lista con cuatro elementos balanceados
val hastaQuePrueba4 = hastaQue(listaUnitaria, combinar)(
  List(Hoja('a', 1), Hoja('b', 1), Hoja('c', 1), Hoja('d', 1))
)
// Esperado: List(Nodo(...)) árbol completo balanceado

// Caso 5: Lista con pesos muy diferentes
val hastaQuePrueba5 = hastaQue(listaUnitaria, combinar)(
  List(Hoja('a', 1), Hoja('b', 1), Hoja('c', 1), Hoja('d', 10))
)
// Esperado: List(Nodo(...)) árbol completo asimétrico





////////////////////////////////////////////////////////
// PRUEBAS PARA crearArbolDeHuffman(cars: List[Char]): ArbolH
////////////////////////////////////////////////////////

// Caso 1: Lista con un solo carácter repetido
val huffmanPrueba1 = crearArbolDeHuffman(List('a', 'a', 'a'))
// Esperado: Hoja('a', 3)

// Caso 2: Lista con dos caracteres diferentes
val huffmanPrueba2 = crearArbolDeHuffman(List('a', 'b'))
// Esperado: Nodo con dos hojas

// Caso 3: Texto simple balanceado
val huffmanPrueba3 = crearArbolDeHuffman(cadenaALista("aabb"))
// Esperado: Nodo balanceado

// Caso 4: Texto con diferentes frecuencias
val huffmanPrueba4 = crearArbolDeHuffman(cadenaALista("aaabbc"))
// Esperado: Árbol asimétrico con 'a' más cerca de la raíz

// Caso 5: Texto real complejo
val huffmanPrueba5 = crearArbolDeHuffman(cadenaALista("La vida es dura"))
// Esperado: Árbol completo de Huffman optimizado


////////////////////////////////////////////////////////
// PRUEBAS PARA decodificar(arbol: ArbolH, bits: List[Bit]): List[Char]
////////////////////////////////////////////////////////


val arbolDecode = crearArbolDeHuffman(cadenaALista("aabbcc"))

// Caso 1: Decodificar lista vacía de bits
val decodePrueba1 = decodificar(arbolDecode, List())
// Esperado: List()

// Caso 2: Decodificar un solo carácter
val bitsSoloA = codificar(arbolDecode)(List('a'))
val decodePrueba2 = decodificar(arbolDecode, bitsSoloA)
// Esperado: List('a')

// Caso 3: Decodificar varios caracteres
val bitsABC = codificar(arbolDecode)(List('a', 'b', 'c'))
val decodePrueba3 = decodificar(arbolDecode, bitsABC)
// Esperado: List('a', 'b', 'c')

// Caso 4: Decodificar mensaje completo
val mensajeOriginal = cadenaALista("abccba")
val arbolMsg = crearArbolDeHuffman(mensajeOriginal)
val bitsMensaje = codificar(arbolMsg)(mensajeOriginal)
val decodePrueba4 = decodificar(arbolMsg, bitsMensaje)
// Esperado: List('a', 'b', 'c', 'c', 'b', 'a')

// Caso 5: Decodificar con árbol del ejemplo del enunciado
val a = 'a'
val b = 'b'
val c = 'c'
val d = 'd'
val e = 'e'
val f = 'f'
val g = 'g'
val h = 'h'
val arbolEjemplo = crearArbolDeHuffman(List(
  a, a, a, a, a, a, a, a, a, a, a, a, a, a, a,
  b, b, b, b, b, b, b, b,
  c, c, c, c, c,
  d, d, d,
  e, e, e, e, e, e, e, e, e, e, e,
  f, f, f, f,
  g, h, h
))
val bitsBADAEADF = codificar(arbolEjemplo)(List(b, a, d, a, e, a, d, f))
val decodePrueba5 = decodificar(arbolEjemplo, bitsBADAEADF)
// Esperado: List('b', 'a', 'd', 'a', 'e', 'a', 'd', 'f')





////////////////////////////////////////////////////////
// PRUEBAS PARA codificar(arbol: ArbolH)(texto: List[Char]): List[Bit]
////////////////////////////////////////////////////////

// Caso 1: Codificar lista vacía
val codifPrueba1 = codificar(arbolDecode)(List())
// Esperado: List()

// Caso 2: Codificar un solo carácter
val codifPrueba2 = codificar(arbolDecode)(List('a'))
// Esperado: List de bits correspondiente a 'a'

// Caso 3: Codificar dos caracteres
val codifPrueba3 = codificar(arbolDecode)(List('a', 'b'))
// Esperado: Concatenación de bits de 'a' y 'b'

// Caso 4: Codificar mensaje con repeticiones
val codifPrueba4 = codificar(arbolDecode)(List('a', 'a', 'b', 'b', 'c', 'c'))
// Esperado: Bits completos del mensaje

// Caso 5: Verificar idempotencia (codificar-decodificar)
val textoOriginal = cadenaALista("huffman")
val arbolHuffman = crearArbolDeHuffman(textoOriginal)
val bitsCodeados = codificar(arbolHuffman)(textoOriginal)
val textoRecuperado = decodificar(arbolHuffman, bitsCodeados)
val codifPrueba5 = (textoOriginal == textoRecuperado)
// Esperado: true

////////////////////////////////////////////////////////
// PRUEBAS PARA codigoEnBits(tabla: TablaCodigos)(car: Char): List[Bit]
////////////////////////////////////////////////////////




val tablaEjemplo: TablaCodigos = List(
  ('a', List(0, 0)),
  ('b', List(0, 1)),
  ('c', List(1, 0)),
  ('d', List(1, 1))
)

// Caso 1: Buscar en tabla vacía
val codigoBitsPrueba1 = codigoEnBits(List())('a')
// Esperado: List()

// Caso 2: Buscar primer elemento
val codigoBitsPrueba2 = codigoEnBits(tablaEjemplo)('a')
// Esperado: List(0, 0)

// Caso 3: Buscar elemento intermedio
val codigoBitsPrueba3 = codigoEnBits(tablaEjemplo)('c')
// Esperado: List(1, 0)

// Caso 4: Buscar último elemento
val codigoBitsPrueba4 = codigoEnBits(tablaEjemplo)('d')
// Esperado: List(1, 1)

// Caso 5: Buscar elemento que no existe
val codigoBitsPrueba5 = codigoEnBits(tablaEjemplo)('z')
// Esperado: List()




////////////////////////////////////////////////////////
// PRUEBAS PARA mezclarTablasDeCodigos
////////////////////////////////////////////////////////

// Caso 1: Ambas tablas vacías
val mezclarPrueba1 = mezclarTablasDeCodigos(List(), List())
// Esperado: List()

// Caso 2: Primera tabla vacía
val mezclarPrueba2 = mezclarTablasDeCodigos(List(), List(('a', List(1))))
// Esperado: List(('a', List(1, 1)))

// Caso 3: Segunda tabla vacía
val mezclarPrueba3 = mezclarTablasDeCodigos(List(('b', List(0))), List())
// Esperado: List(('b', List(0, 0)))

// Caso 4: Ambas tablas con elementos
val mezclarPrueba4 = mezclarTablasDeCodigos(
  List(('a', List()), ('b', List(0))),
  List(('c', List()), ('d', List(1)))
)
// Esperado: izquierda con 0 prefijado, derecha con 1 prefijado

// Caso 5: Tablas con códigos largos
val mezclarPrueba5 = mezclarTablasDeCodigos(
  List(('x', List(0, 1, 0))),
  List(('y', List(1, 1, 1)))
)
// Esperado: List(('x', List(0, 0, 1, 0)), ('y', List(1, 1, 1, 1)))




////////////////////////////////////////////////////////
// PRUEBAS PARA convertir(arbol: ArbolH): TablaCodigos
////////////////////////////////////////////////////////

// Caso 1: Árbol con una sola hoja
val convertirPrueba1 = convertir(Hoja('a', 5))
// Esperado: List(('a', List()))

// Caso 2: Árbol con dos hojas
val convertirPrueba2 = convertir(hacerNodoArbolH(Hoja('a', 1), Hoja('b', 2)))
// Esperado: List(('a', List(0)), ('b', List(1)))

// Caso 3: Árbol con tres niveles
val arbolTresNiv = hacerNodoArbolH(
  hacerNodoArbolH(Hoja('a', 1), Hoja('b', 2)),
  Hoja('c', 3)
)
val convertirPrueba3 = convertir(arbolTresNiv)
// Esperado: códigos correctos para a, b, c

// Caso 4: Árbol balanceado
val arbolBal = hacerNodoArbolH(
  hacerNodoArbolH(Hoja('a', 1), Hoja('b', 1)),
  hacerNodoArbolH(Hoja('c', 1), Hoja('d', 1))
)
val convertirPrueba4 = convertir(arbolBal)
// Esperado: todos con códigos de longitud 2

// Caso 5: Árbol del ejemplo complejo
val convertirPrueba5 = convertir(arbolEjemplo)
// Esperado: tabla completa con códigos de diferentes longitudes



////////////////////////////////////////////////////////
// PRUEBAS PARA codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit]
////////////////////////////////////////////////////////


// Caso 1: Texto vacío
val rapidoPrueba1 = codificarRapido(arbolDecode)(List())
// Esperado: List()

// Caso 2: Un solo carácter
val rapidoPrueba2 = codificarRapido(arbolDecode)(List('a'))
// Esperado: Mismo resultado que codificar

// Caso 3: Varios caracteres
val rapidoPrueba3 = codificarRapido(arbolDecode)(List('a', 'b', 'c'))
// Esperado: Mismo resultado que codificar

// Caso 4: Equivalencia con codificar (texto corto)
val textoCorto = cadenaALista("abcd")
val arbolCorto = crearArbolDeHuffman(textoCorto)
val rapidoPrueba4a = codificar(arbolCorto)(textoCorto)
val rapidoPrueba4b = codificarRapido(arbolCorto)(textoCorto)
val rapidoPrueba4 = (rapidoPrueba4a == rapidoPrueba4b)
// Esperado: true

// Caso 5: Equivalencia con codificar (texto largo)
val textoLargo = cadenaALista("abracadabra con huffman")
val arbolLargo = crearArbolDeHuffman(textoLargo)
val rapidoPrueba5a = codificar(arbolLargo)(textoLargo)
val rapidoPrueba5b = codificarRapido(arbolLargo)(textoLargo)
val rapidoPrueba5 = (rapidoPrueba5a == rapidoPrueba5b)
// Esperado: true







////////////////////////////////////////////////////////
// PRUEBAS INTEGRADAS para el ciclo completo de codificación/decodificación
////////////////////////////////////////////////////////

// Prueba integral 1: Texto simple
val textoIntegral1 = cadenaALista("hola")
val arbolIntegral1 = crearArbolDeHuffman(textoIntegral1)
val bitsIntegral1 = codificarRapido(arbolIntegral1)(textoIntegral1)
val recuperadoIntegral1 = decodificar(arbolIntegral1, bitsIntegral1)
val integralPrueba1 = (textoIntegral1 == recuperadoIntegral1)
// Esperado: true

// Prueba integral 2: Texto con repeticiones
val textoIntegral2 = cadenaALista("aaaaabbbcc")
val arbolIntegral2 = crearArbolDeHuffman(textoIntegral2)
val bitsIntegral2 = codificarRapido(arbolIntegral2)(textoIntegral2)
val recuperadoIntegral2 = decodificar(arbolIntegral2, bitsIntegral2)
val integralPrueba2 = (textoIntegral2 == recuperadoIntegral2)
// Esperado: true

// Prueba integral 3: Texto del enunciado
val textoIntegral3 = cadenaALista("La vida es dura")
val arbolIntegral3 = crearArbolDeHuffman(textoIntegral3)
val bitsIntegral3 = codificarRapido(arbolIntegral3)(textoIntegral3)
val recuperadoIntegral3 = decodificar(arbolIntegral3, bitsIntegral3)
val integralPrueba3 = (textoIntegral3 == recuperadoIntegral3)
// Esperado: true

// Prueba integral 4: Texto con caracteres especiales
val textoIntegral4 = cadenaALista("¡Hola, mundo! ¿Cómo estás?")
val arbolIntegral4 = crearArbolDeHuffman(textoIntegral4)
val bitsIntegral4 = codificarRapido(arbolIntegral4)(textoIntegral4)
val recuperadoIntegral4 = decodificar(arbolIntegral4, bitsIntegral4)
val integralPrueba4 = (textoIntegral4 == recuperadoIntegral4)
// Esperado: true

// Prueba integral 5: Comparación de eficiencia de codificación
val textoEficiencia = cadenaALista("programacion funcional con scala")
val arbolEficiencia = crearArbolDeHuffman(textoEficiencia)
val bitsHuffman = codificarRapido(arbolEficiencia)(textoEficiencia)
val bitsASCII = textoEficiencia.length * 8 // ASCII usa 8 bits por carácter
val longitudHuffman = bitsHuffman.length
val ahorroPorcentaje = ((bitsASCII - longitudHuffman).toDouble / bitsASCII) * 100
val integralPrueba5 = ahorroPorcentaje > 0
// Esperado: true (Huffman debe comprimir mejor que ASCII)






////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

// VERIFICACIÓN DE PROPIEDADES DEL ALGORITMO DE HUFFMAN

////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

// Propiedad 1: El código es prefijo (no hay código que sea prefijo de otro)
val textoProp1 = cadenaALista("abcdef")
val arbolProp1 = crearArbolDeHuffman(textoProp1)
val tablaProp1 = convertir(arbolProp1)
def esPrefijoDe(bits1: List[Bit], bits2: List[Bit]): Boolean = {
  bits1 != bits2 && bits2.take(bits1.length) == bits1
}
def verificarCodigoPrefijo(tabla: TablaCodigos): Boolean = {
  val codigos = tabla.map(_._2)
  codigos.forall(c1 => 
    codigos.forall(c2 => !esPrefijoDe(c1, c2) && !esPrefijoDe(c2, c1))
  )
}
val propiedadPrefijo = verificarCodigoPrefijo(tablaProp1)
// Esperado: true (códigos de Huffman son prefijos)

// Propiedad 2: Caracteres más frecuentes tienen códigos más cortos
val textoProp2 = cadenaALista("aaaaaabbc") // 'a' es más frecuente
val arbolProp2 = crearArbolDeHuffman(textoProp2)
val tablaProp2 = convertir(arbolProp2)
val codigoA = tablaProp2.find(_._1 == 'a').get._2.length
val codigoB = tablaProp2.find(_._1 == 'b').get._2.length
val codigoC = tablaProp2.find(_._1 == 'c').get._2.length
val propiedadFrecuencia = codigoA <= codigoB && codigoA <= codigoC
// Esperado: true (caracter más frecuente tiene código <= que los demás)

// Propiedad 3: Idempotencia - codificar y decodificar recupera el original
def verificarIdempotencia(texto: List[Char]): Boolean = {
  if (texto.isEmpty) true
  else {
    val arbol = crearArbolDeHuffman(texto)
    val codificado = codificarRapido(arbol)(texto)
    val decodificado = decodificar(arbol, codificado)
    texto == decodificado
  }
}
val propiedadIdempotencia1 = verificarIdempotencia(cadenaALista("test"))
val propiedadIdempotencia2 = verificarIdempotencia(cadenaALista("compression"))
val propiedadIdempotencia3 = verificarIdempotencia(cadenaALista("aabbccdd"))
// Esperados: true, true, true

// Propiedad 4: La tabla de códigos tiene tantas entradas como caracteres únicos
val textoProp4 = cadenaALista("aabbccddee")
val arbolProp4 = crearArbolDeHuffman(textoProp4)
val tablaProp4 = convertir(arbolProp4)
val caracteresUnicos = textoProp4.distinct.length
val entradasTabla = tablaProp4.length
val propiedadCompletitud = caracteresUnicos == entradasTabla
// Esperado: true

// Propiedad 5: codificar y codificarRapido producen el mismo resultado
def verificarEquivalencia(texto: List[Char]): Boolean = {
  if (texto.isEmpty) true
  else {
    val arbol = crearArbolDeHuffman(texto)
    val bits1 = codificar(arbol)(texto)
    val bits2 = codificarRapido(arbol)(texto)
    bits1 == bits2
  }
}
val propiedadEquivalencia1 = verificarEquivalencia(cadenaALista("equivalencia"))
val propiedadEquivalencia2 = verificarEquivalencia(cadenaALista("test de funciones"))
val propiedadEquivalencia3 = verificarEquivalencia(cadenaALista("abc"))
// Esperados: true, true, true




////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

// CASOS EXTREMOS Y EDGE CASES

////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

/*
// Texto con un solo carácter único (árbol trivial)
val edgeCaso1 = cadenaALista("aaaaaaa")
val edgeArbol1 = crearArbolDeHuffman(edgeCaso1)
val edgeBits1 = codificarRapido(edgeArbol1)(edgeCaso1)
val edgeRecuperado1 = decodificar(edgeArbol1, edgeBits1)
val edgePrueba1 = (edgeCaso1 == edgeRecuperado1)
// Esperado: true
*/



// Edge case 1: Texto con todos los caracteres únicos
val edgeCaso2 = cadenaALista("abcdefgh")
val edgeArbol2 = crearArbolDeHuffman(edgeCaso2)
val edgeBits2 = codificarRapido(edgeArbol2)(edgeCaso2)
val edgeRecuperado2 = decodificar(edgeArbol2, edgeBits2)
val edgePrueba2 = (edgeCaso2 == edgeRecuperado2)
// Esperado: true

// Edge case 2: Texto muy corto (dos caracteres)
val edgeCaso3 = cadenaALista("ab")
val edgeArbol3 = crearArbolDeHuffman(edgeCaso3)
val edgeBits3 = codificarRapido(edgeArbol3)(edgeCaso3)
val edgeRecuperado3 = decodificar(edgeArbol3, edgeBits3)
val edgePrueba3 = (edgeCaso3 == edgeRecuperado3)
// Esperado: true

// Edge case 3: Texto con caracteres espaciales y puntuación
val edgeCaso4 = cadenaALista("   ...   ???")
val edgeArbol4 = crearArbolDeHuffman(edgeCaso4)
val edgeBits4 = codificarRapido(edgeArbol4)(edgeCaso4)
val edgeRecuperado4 = decodificar(edgeArbol4, edgeBits4)
val edgePrueba4 = (edgeCaso4 == edgeRecuperado4)
// Esperado: true

// Edge case 4: Texto con distribución de Fibonacci (caso interesante)
val edgeCaso5 = cadenaALista("aaabbcccccdddddddddeeeeeeeeeeeeeee")
val edgeArbol5 = crearArbolDeHuffman(edgeCaso5)
val edgeTabla5 = convertir(edgeArbol5)
val edgeBits5 = codificarRapido(edgeArbol5)(edgeCaso5)
val edgeRecuperado5 = decodificar(edgeArbol5, edgeBits5)
val edgePrueba5 = (edgeCaso5 == edgeRecuperado5)
// Esperado: true




////////////////////////////////////////////////////////
// PRUEBAS PARA
////////////////////////////////////////////////////////

