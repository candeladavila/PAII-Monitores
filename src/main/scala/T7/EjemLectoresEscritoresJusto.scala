package T7

import scala.util.Random

object EjemLectoresEscritoresJusto {
  //VARIABLES
  private var hayEscritores = false
  private var nLectores = 0
  private var nEscritores = 0 //número de escritores que están esperando

  def openL(i:Int) = synchronized {
    while (hayEscritores || nEscritores > 0) {
      wait()
    }
    nLectores += 1
    log(s"Entra lector $i")
  }

  def openE(i:Int) = synchronized {
    nEscritores += 1
    while (hayEscritores || nLectores > 0) {
      wait()
    }
    hayEscritores = true
    log(s"Entra escritor $i")
  }

  def closeL(i:Int) = synchronized {
    nLectores -= 1
    if (nLectores == 0) { //si es el último lector en salir puedo dejar entrar a un escritor
      notifyAll()
    }
    log(s"Sale lector $i")
  }
  def closeE(i:Int) = synchronized{
    hayEscritores = false
    nEscritores -= 1
    notifyAll()
    //despierto a todos los lectores y escritores que estén esperando porque no tengo forma de saber a quien despierto y tengo que dar prioridad
    log(s"Sale escritor $i")
  }

  def main (args:Array[String]): Unit = {
    val NL = 10;
    val NE = 2
    val lector = new Array[Thread](NL)
    val escritor = new Array[Thread](NE)
    for (i <- 0 until (lector.length)) {
      lector(i) = thread {
        while (true) {
          openL(i)
          // lector i en la BD
          Thread.sleep(Random.nextInt(200))
          closeL(i)
        }
      }
    }
    for (i <- 0 until (escritor.length)) {
      lector(i) = thread {
        while (true) {
          openE(i)
          // escritor i en la BD
          Thread.sleep(Random.nextInt(200))
          closeE(i)
        }
      }
    }
  }
}
