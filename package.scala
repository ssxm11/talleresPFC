/* Se desea calcular el maximo de una lista de enteros positivos, no vaca.
 Implemente las funciones maxLin, y maxIt que calculen ese valor, pero la primera
 genere un proceso de recursion lineal, mientras la segunda genere un proceso iterativo.
 Hay tres metodos que se proveen en List[Int] que pueden ser utiles para este ejercicio:
 l.isEmpty: Boolean (devuelve si una lista l esta vaca)
 l.head: Int (devuelve el primer elemento de la lista l)
 l.tail: List[Int] (devuelve la lista sin el primer elemento l)*/
package object FuncionesRecursivas{
     /**
   * Ejercicio 1.1: Máximo de una lista de enteros
   */
  def maxLin(l: List[Int]): Int = {
    // Si la lista está vacía, lanzamos una excepción
    if (l.isEmpty) {
      throw new IllegalArgumentException("La lista no puede ser vacía.")
    }
    
    // Función auxiliar que implementa la recursión lineal
    def go(lista: List[Int], maximoActual: Int): Int = {
      if (lista.isEmpty) {
        maximoActual
      } else {
        val nuevoMaximo = if (lista.head > maximoActual) lista.head else maximoActual
        go(lista.tail, nuevoMaximo)
      }
    }
    
    // Llamada inicial a la función auxiliar
    go(l.tail, l.head)
  }

  def maxIt(l: List[Int]): Int = {
    // Si la lista está vacía, lanzamos una excepción
    if (l.isEmpty) {
      throw new IllegalArgumentException("La lista no puede ser vacía.")
    }
    
    // Función auxiliar que implementa el proceso iterativo
    def loop(lista: List[Int], acumulador: Int): Int = {
      if (lista.isEmpty) {
        acumulador
      } else {
        val nuevoAcumulador = if (lista.head > acumulador) lista.head else acumulador
        loop(lista.tail, nuevoAcumulador)
      }
    }
    
    // Llamada inicial a la función auxiliar
    loop(l.tail, l.head)
  }

/*Su tarea en este ejercicio consiste en implementar, usando recursion, dos funciones:
 movsTorresHanoi y torresHanoi. La primera, movsTorresHanoi , recibe n, el numero
 de discos y devuelve el numero mnimo de movimientos necesarios para mover n discos de
 la primera varilla (o torre) a la tercera usando la segunda como intermediaria. Con este
 valor, se podra calcular cuantos siglos se demoraran los monjes en acabar la tarea.*/

    def movsTorresHanoi(n: Int ): BigInt = {
        if (n == 0) {
            0
        } else {
            2 * movsTorresHanoi(n - 1) + 1
        }
    }
/*La segunda, torresHanoi, recibe n, el numero de discos, y tres enteros t1 t2 t3 repre
sentando los identi cadores de las tres varillas (torres) y devuelve una lista de movimien
tos, donde un movimiento es una pareja (ab) donde ab 
t1 t2 t3 a=b, indicando
 que se mueva el disco en lo alto de la varilla a hacia la varilla b.*/
    def torresHanoi(n:Int , t1:Int , t2:Int , t3:Int ): List [(Int , Int )] = {
        // Pasar n discos de la torre t1 a la torre t3 usando t2 como intermediaria
        // Devuelve la lista de movimientos de parejas (a,b) indicando mover un disco de la torre a hacia
        // la torre b
        if (n == 0) {
            List()
        } else {
            // Mover n-1 discos de t1 a t2 usando t3 como intermediaria
            val movimientos1 = torresHanoi(n - 1, t1, t3, t2)
            // Mover el disco n de t1 a t3
            val movimiento2 = List((t1, t3))
            // Mover n-1 discos de t2 a t3 usando t1 como intermediaria
            val movimientos3 = torresHanoi(n - 1, t2, t1, t3)
            movimientos1 ++ movimiento2 ++ movimientos3  
        }
    }


    
}
