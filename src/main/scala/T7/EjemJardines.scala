package T7

object Jardin {
  private var n = 0
  def inc = {
    log("Ejemplo")
    this.synchronized{ //si no ponemos this entonces por defecto es el objeto receptor (irrelevante ponerlo)
      //wait () -> esto significaría que me quiero bloquear en el objeto this (aquí está mal en este ejemplo)
      n += 1
    }
  }
  def valor = n
}

object EjemJardines {
  def main(args:Array[String]): Unit = {
    val p0 = thread {
      for (i <- 0 until 10)
        Jardin.inc
    }

    val p1 = thread {
      for (i <- 0 until 10)
        Jardin.inc
    }
    p0.join
    p1.join
  }
}
