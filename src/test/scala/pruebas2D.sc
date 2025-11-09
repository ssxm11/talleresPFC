import Benchmark._
import kmedianas2D._

val puntos16_3 = generarPuntos(3, 16).toSeq
tiemposKmedianas(puntos16_3, 3, 0.01)
