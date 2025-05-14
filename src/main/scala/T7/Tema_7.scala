package T7

class Tema_7 {

}
/*
Métodos sincronizados = monitor

PAG 8
Dentro del monitor a lo sumo hay una hebra
Conjunto de hebras que está en el conjunto de entrada = hebras que están esperando para poder ejecutar
algún método sincronizado.
Cuando una de las que está dentro sale entonces entra una cualquiera de las que esté esperando

PAG 15
Wait = esperar (acquire)
Notify = despertar (release)
IllegalMonitorStateException -> no es que esté mal el programa es que se me ha olvidado poner un syncronized en el código

PAG 16
Cuando una hebra hace wait si o sí se bloquea al contrario que los semáforos (si no es binario no tenía porqué bloquearse)
Para decidir donde bloquearme respecto al objeto tiene que estar anidado en un syncronized para saber en ejecución donde
ponemos la hebra que va a esperar.

PAG 18
notify = release() único
notifyAll = release() en broadcast -> despierta todos los que están esperando (a las hebras que están esperando en el
mismo objeto con el que yo he hecho el notify)
Lo que hace es despertar la(s) hebra(s) que están en el objeto respectivo.
Si en el conjunto de espera no hay nadie y hago un Notify no pasa nada -> es distinto al release
En el release se cambia el valor del semáforo y en el notify no cambia nada (no hay forma de saber si se ha hecho o no)

PAG 17
conjunto de entrada, conjunto de espera (asociado al objeto y contiene a todas las hebras que han hecho un wait)
Cuando se desbloquea una hebra -> wait se desbloquea automáticamente el lock

PAG 20
Todos los wait tienen que estar anidados en un while SIEMPRE (en el while ponemos la condición que bloquea)
Dificultad: entender la entrada y salida de las hebras

PAG 24
El problema de los lectores y escritores
- Escritores = exclusión mutua
- Lectores = pueden entrar todos los que quieran sin límite

Cuando hago un notify no sé a quien despierto, en CloseL puede entrar un escritor o no (podemos poner 
notify o notifyAll, da igual)
Cuando hago un notify all despierto a todos, en closeE tengo que dejar que entren todos los lectores
y por tanto hago un notiyAll
 */
