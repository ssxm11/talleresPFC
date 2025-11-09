package object kmedianas2D {
import scala.annotation.tailrec
import common._
import scala.util.Random
import scala.collection.{Map, Seq}
import scala.collection.parallel.CollectionConverters._

def generarPuntos(k: Int, num: Int): Seq[Punto] = {
  val randx = new Random
  val randy = new Random
  (0 until num).map { i =>
    val x = ((i + 1) % k) * 1.0 / k + randx.nextDouble() * 0.5
    val y = ((i + 5) % k) * 1.0 / k + randy.nextDouble() * 0.5
    new Punto(x, y)
  }
}
def inicializarMedianas(k: Int, puntos: Seq[Punto]): Seq[Punto] = {
  val rand = new Random
  (0 until k).map(_ => puntos(rand.nextInt(puntos.length)))
}

/** umbral seguro: al menos 1, y proporcional al tamaño n.
  * Ajusta n/10 según tu máquina (n/100 o math.sqrt(n) también pueden usarse).
  */
def umbral(n: Int): Int = {
  val u = math.max(1, n / 10)   // 10% del total, nunca menos de 1
  u
}

class Punto(val x: Double, val y: Double) {
  private def cuadrado(v: Double): Double = v * v
  def distanciaAlCuadrado(that: Punto): Double =
    cuadrado(that.x - x) + cuadrado(that.y - y)


  private def round(v: Double): Double = (v * 100).toInt / 100.0

  override def toString = s"(${round(x)}, ${round(y)})"
}
def hallarPuntoMasCercano(p: Punto, medianas: Seq[Punto]): Punto = {
  assert(medianas.nonEmpty)
  medianas.map(m => (m, p.distanciaAlCuadrado(m)))
          .sortBy(_._2)
          .head._1
}
def clasificarSeq(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
  puntos.groupBy(p => hallarPuntoMasCercano(p, medianas))
}
def clasificarPar(umb: Int)(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
  if (puntos.size <= umb) {
    // Caso base: procesar secuencialmente
    clasificarSeq(puntos, medianas)
  } else {
    // Dividir el trabajo en dos mitades
    val (izq, der) = puntos.splitAt(puntos.size / 2)

    // Ejecutar ambas mitades en paralelo
    val (resIzq, resDer) = parallel(
      clasificarPar(umb)(izq, medianas),
      clasificarPar(umb)(der, medianas)
    )

    // Combinar resultados
    (resIzq.keySet ++ resDer.keySet).map { med =>
      med -> (resIzq.getOrElse(med, Seq()) ++ resDer.getOrElse(med, Seq()))
    }.toMap
  }
}
def calculePromedioSeq(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
  if (puntos.isEmpty) medianaVieja
  else
    new Punto(
      puntos.map(_.x).sum / puntos.length,
      puntos.map(_.y).sum / puntos.length
    )
}

def calculePromedioPar(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
  if (puntos.isEmpty) medianaVieja
  else {
    val puntosPar = puntos.par 
    new Punto(
      puntosPar.map(_.x).sum / puntosPar.length,
      puntosPar.map(_.y).sum / puntosPar.length
    )
  }
}
def actualizarSeq(
  clasif: Map[Punto, Seq[Punto]],
  medianasViejas: Seq[Punto]
): Seq[Punto] = {
  for (mediana <- medianasViejas)
    yield calculePromedioSeq(mediana, clasif(mediana))
}
def actualizarPar(
  clasif: Map[Punto, Seq[Punto]],
  medianasViejas: Seq[Punto]
): Seq[Punto] = {

  if (medianasViejas.size <= 1) {
    // Caso base: no hay nada que paralelizar
    actualizarSeq(clasif, medianasViejas)
  } else {
    // Dividimos en dos mitades
    val (izq, der) = medianasViejas.splitAt(medianasViejas.length / 2)

    // Procesamos en paralelo
    val (resIzq, resDer) = parallel(
      actualizarPar(clasif, izq),
      actualizarPar(clasif, der)
    )

    resIzq ++ resDer
  }
}
def hayConvergenciaSeq(
  eta: Double,
  medianasViejas: Seq[Punto],
  medianasNuevas: Seq[Punto]
): Boolean = {
  require(medianasViejas.length == medianasNuevas.length,
          "Las colecciones de medianas deben tener la misma longitud")

  var convergencia = true
  var i = 0

  while (i < medianasViejas.length && convergencia) {
    val distancia = medianasViejas(i).distanciaAlCuadrado(medianasNuevas(i))
    if (distancia > eta * eta)  // comparamos usando eta²
      convergencia = false
    i += 1
  }

  convergencia
}


def hayConvergenciaPar(
  eta: Double,
  medianasViejas: Seq[Punto],
  medianasNuevas: Seq[Punto]
): Boolean = {

  if (medianasViejas.length <= 1) {
    // caso base
    hayConvergenciaSeq(eta, medianasViejas, medianasNuevas)
  } else {
    // dividimos en dos mitades
    val mitad = medianasViejas.length / 2

    val (viejasIzq, viejasDer) = medianasViejas.splitAt(mitad)
    val (nuevasIzq, nuevasDer) = medianasNuevas.splitAt(mitad)

    // paralelizamos las comprobaciones
    val (resIzq, resDer) = parallel(
      hayConvergenciaPar(eta, viejasIzq, nuevasIzq),
      hayConvergenciaPar(eta, viejasDer, nuevasDer)
    )

    resIzq && resDer
  }
}


@tailrec
final def kMedianasSeq(
  puntos: Seq[Punto],
  medianas: Seq[Punto],
  eta: Double
): Seq[Punto] = {
  // Clasificar los puntos según las medianas actuales
  val clasificacion = clasificarSeq(puntos, medianas)

  // Calcular las nuevas medianas
  val nuevasMedianas = actualizarSeq(clasificacion, medianas)

  // Verificar si ya hay convergencia
  if (hayConvergenciaSeq(eta, medianas, nuevasMedianas))
    nuevasMedianas // Caso base: convergió
  else
    kMedianasSeq(puntos, nuevasMedianas, eta) // Paso recursivo
}
@tailrec
final def kMedianasPar(
  puntos: Seq[Punto],
  medianas: Seq[Punto],
  eta: Double
): Seq[Punto] = {
  // Clasificación concurrente
  val clasificacion = clasificarPar(umbral(puntos.length))(puntos, medianas)

println(s"[Par] n=${puntos.length}, umbral=${umbral(puntos.length)}")

  // Actualización concurrente
  val nuevasMedianas = actualizarPar(clasificacion, medianas)

  // Verificación concurrente
  val convergencia = hayConvergenciaPar(eta, medianas, nuevasMedianas)

  if (convergencia)
    nuevasMedianas // Caso base: convergió
  else
    kMedianasPar(puntos, nuevasMedianas, eta) // Paso recursivo
}

  
}
