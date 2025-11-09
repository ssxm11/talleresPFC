package kmedianas2D

import Benchmark._

object Main extends App {
  val etas = Seq(0.01, 0.001)
  val ks = Seq(2, 4, 8, 16, 32)
  val tamaños = Seq(1000, 10000, 50000, 100000)

  println("==== Comparación de tiempos: Secuencial vs Paralela ====")

  for (eta <- etas; k <- ks; n <- tamaños) {
    val puntos = generarPuntos(k, n).toSeq
    val (tSeq, tPar, accel) = tiemposKmedianas(puntos, k, eta)

    println(f"\n--- Prueba con n=$n%,d puntos, k=$k, eta=$eta%.3f ---")
    println(f"Tiempo secuencial: ${tSeq.value}%.4f ms")
    println(f"Tiempo paralelo:   ${tPar.value}%.4f ms")
    println(f"Aceleración:       $accel%.2fx")
  }
}
