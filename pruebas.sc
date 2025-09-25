import Comparador ._
 import scala.util.Random
 val random=newRandom()
 def listaAlAzar(long:Int): List[Int] ={
 //Crea una lista de long enteros,
 // con valores aleatorios entre 1 y long 2
 val v=Vector.fill(long){
 random.nextInt(long *2)+1}
 v.toList
 }

 def menorQue(a:Int, b:Int): Boolean=a<b
 def mayorQue(a:Int, b:Int): Boolean=a>b


//## pruebas para insert
//###prueba de caso base 1
val (res0,c0)=insert(111,Nil,menorQue)
// res0 debe ser List(111), c0=0 comparaciones
//### prueba peor caso 2
val (resP,cP)=insert(111,List(99, 88, 77, 66, 55),mayorQue)
// resP debe ser List(1,2,3,4,5), cP=4 comparaciones

// ### prueba caso en lista ordenada con muchos elementos 3
val lAsc=(1 to 100).toList
val (resA,cA)=insert(50,lAsc,menorQue)
// resA debe ser List(1,2,3,4,5,50,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100), cA=51 comparaciones

// ### prueba caso en lista aleatoria desordenada de 10 elementos 4
val al1=listaAlAzar(10)
val (resAl,cAl)=insert(7,al1,menorQue)
val (resAl,cAl)=insert(7,al1,mayorQue)

// ### prueba caso con elementos negativos 5
val lNeg=List(-10,-5,0,5,10)
val (resN,cN)=insert(1,lNeg,menorQue)

// ## pruebas para insertionSort

// ## pruebas para menoresQue_noMenoresQue
//### prueba de caso base 1
 val (menores,noMenores,c0)=menoresQue_noMenoresQue(Nil,5,menorQue)
    // res0 debe ser (Nil,Nil,0)
//### prueba caso con varios elementos 2

 val l1=List(5,3,8,1,4)
 val (menores,noMenores,c1)=menoresQue_noMenoresQue(l1,4,menorQue)
 // menores debe ser List(1,3), noMenores debe ser List(5,8,4), c1=5 comparaciones

// ### prueba caso con todos menores 3
 val l2=List(1,2,3)
 val (menores2,noMenores2,c2)=menoresQue_noMenoresQue(l2,4,menorQue)
    // menores2 debe ser List(1,2,3), noMenores2 debe ser Nil, c2=3 comparaciones
// ### prueba caso con todos no menores 4
 val l3=List(5,6,7)
 val (menores3,noMenores3,c3)=menoresQue_noMenoresQue(l3,4,menorQue)
 // menores3 debe ser Nil, noMenores3 debe ser List(5,6,7), c3=3 comparaciones

 // ### prueba caso con lista con muchos elementos aleatorio
 val l4=listaAlAzar(100)
 val (menores4,noMenores4,c4)=menoresQue_noMenoresQue(l4,50,menorQue)
 // menores4 debe ser List(...), noMenores4 debe ser List(...), c4=... comparaciones

// ## pruebas para quickSort
//### prueba de caso base 1
 val res0=quickSort[Int](menorQue)(Nil)
    // res0 debe ser Nil
//### prueba caso con varios elementos 2
 val l1=List(5,3,8,1,4)
    val res1=quickSort[Int](menorQue)(l1)
    // res1 debe ser List(1,3,4,5,8)
// ### prueba caso con lista ya ordenada 3
    val l2=(1 to 10).toList
        val res2=quickSort[Int](menorQue)(l2)
    // res2 debe ser List(1,2,3,4,5,6,7,8,9,10)
// ### prueba caso con lista ordenada en orden inverso 4
    val l3=(1 to 10).toList.reverse
        val res3=quickSort[Int](menorQue)(l3)
    // res3 debe ser List(1,2,3,4,5,6,7,8,9,10)
    // ### prueba caso con lista con muchos elementos aleatorio 5
    val l4=listaAlAzar(50)
        val res4=quickSort[Int](menorQue)(l4)
    // res4 debe ser List(...)





 val iSort_Asc= insertionSort[Int](menorQue)
 val iSort_Desc= insertionSort[Int](mayorQue)
 iSort_Asc(List(4,5,6,1,2,3))
 val qSort_Asc=quickSort[Int](menorQue)
 val qSort_Desc=quickSort[Int](mayorQue)
 qSort_Asc(List(4,5,6,1,2,3))
 comparar(iSort_Asc,qSort_Asc,List(4,5,6,1,2,3))
 comparar(iSort_Asc,qSort_Desc,List(4,5,6,1,2,3))
 val lAsc100=(1 to 100).toList
 val lAsc1000=(1 to 1000).toList
 val lDsc100=(1 to 100).toList.reverse
 val lDsc1000=(1 to 1000).toList.reverse
 comparar(iSort_Asc,qSort_Asc,lAsc100)
 comparar(iSort_Asc,qSort_Asc,lAsc1000)
 comparar(iSort_Asc,qSort_Asc,lDsc100)
 comparar(iSort_Asc,qSort_Asc,lDsc100)
 val l5=listaAlAzar(5)
 val l10=listaAlAzar(10)
 val l20=listaAlAzar(20)
 val l50=listaAlAzar(50)
 

 comparar(iSort_Asc ,qSort_Asc , l5)
 comparar(iSort_Asc ,qSort_Asc , l10)
 comparar(iSort_Asc ,qSort_Asc , l20)
 comparar(iSort_Asc ,qSort_Asc , l50)