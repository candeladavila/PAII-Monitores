package T7

import scala.util.Random

object EjemLectoresEscritores {
  //VARIABLES
  private var hayEscritores = false
  private var nLectores = 0

  def openL(i:Int) = synchronized {
    while (hayEscritores) {
      wait()
    }
    nLectores += 1
    log(s"Entra lector $i")
  }

  def openE(i:Int) = synchronized {
    while (hayEscritores || nLectores > 0) {
      wait()
    }
    hayEscritores = true
    log(s"Entra escritor $i")
  }

  def closeL(i:Int) = synchronized {
    nLectores -= 1
    if (nLectores == 0) { //si es el último lector en salir puedo dejar entrar a un escritor
      notify()
    }
    log(s"Sale lector $i")
  }
  def closeE(i:Int) = synchronized{
    hayEscritores = false
    notifyAll() //despierto a todos los lectores y escritores que estén esperando
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
