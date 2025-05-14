package T7

import scala.util.Random

class Barrera(n:Int){
  //para sincronizar las iteraciones
  private var esperoTodos = true //espero a todos
  private var sigIter = true //solo puedo entrar si la siguiente iteración está abierta (inicialmente si está abierta)
  private var finalizados = 0 //contador de trabajdores que han terminado su iteración

  def finIter(id:Int,iter:Int)= synchronized{
    while (!sigIter) wait() //para controlar si puedo dar la vuelta de nuevo
    finalizados += 1
    log(s"Worker $id ha terminado la iteración $iter----$finalizados")
    if (finalizados < n)
      while (esperoTodos) wait()
    else
      sigIter = true
      esperoTodos = false
      notifyAll()

      //si soy el último en salir
      log(s"------------------------------------------")

  }
}


object EjemWorkersorkers {
  def main(args:Array[String]) =
    val N = 5
    val barrera = new Barrera(N)
    val worker = new Array[Thread](N)
    for (i <- 0 until N)
      worker(i) = thread {
        for (j <- 0 until 30)
          Thread.sleep(Random.nextInt(100))
          barrera.finIter(i,j)
      }

}
