package T7

import scala.util.Random
import java.util.concurrent._

class Buffer(N:Int) {
  private var b = new Array[Int](N) //lo ponemos de enteros porque el tipo de entrada de datos es irrelevante
  private var i = 0 //Indice producto
  private var j = 0 //Indice consumidor
  private var numElem = 0
  //CS-Productor
  //CS-Consumidor
  /*
  La suma de hayDatos y hayEspacio tiene que ser SIEMPRE igual a N
   */

  def almacenar(dato:Int) = synchronized {
    //Para el productor -> dejar un nuevo elemento en el buffer
    while (numElem == N){ //está lleno
      wait()
    }
    b(i) = dato
    i = (i+1)%N
    numElem += 1
    notify()
  }

  def extraer():Int = synchronized{
    //Para el consumidor -> cuando quiera sacar un elemento del buffer
    while (numElem == 0){
      wait()
    }
    val dato = b(j)
    j = (j+1)%N
    numElem -=1
    notify()
    dato
  }
}

/*
CONDICIONES DE SINCRONIZACIÓN
conProductor = el productor espera si el buffer está leno
conConsumidor = el consumidor espera si el buffer está vacío

Lo que vamos a hacer es considerar el buffer como un círculo -> van dando vueltas el consumidor
y productor
 */
object ProdCons{
  def main(array: Array[String]) = {

  }
}