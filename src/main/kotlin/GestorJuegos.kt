import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.String
import java.util.*
import kotlin.Boolean
import kotlin.text.lowercase

class GestorJuegos {

    //Coleccion ordenada por nombre, case-insensitive
    val coleccionJuegos: MutableSet<Juego> =
        TreeSet (compareBy (String.CASE_INSENSITIVE_ORDER){ it.titulo })

    //Metodo añadir juego
    fun anadirJuegos(juego: Juego){
        coleccionJuegos.add(juego)
    }

    //Metodo para listar la coleccion
    fun listarJuegos(){
        for (temp in coleccionJuegos) {
            println(temp.getDescripcion())
        }
    }

    //Metodo para buscar por titulo
    fun busquedaPorTitulo(titulo : kotlin.String){
        var encontrado : Boolean = false
        for(temp in coleccionJuegos){
            val tituloJuego : kotlin.String = temp.titulo.lowercase()
            if (tituloJuego.startsWith(titulo)){
                println(temp)
                encontrado = true
            }
        }
        if (!encontrado) {
            println("No se ha encontrado")
        }
    }
    //Metodo para guardar datos en un fichero
    fun guardarDatos(){
        val f = File ("Recursos/juegosdemesa.txt")
        try {
            f.bufferedWriter().use { bw ->
                for (temp in coleccionJuegos){
                    val linea = when (temp) {
                        is JuegoCartas -> "CARTAS,${temp.titulo},${temp.nMin},${temp.nMax},${temp.duracionMinutos},${temp.tipo},${temp.nCartas}"
                        is JuegoTablero -> "TABLERO,${temp.titulo},${temp.nMin},${temp.nMax},${temp.duracionMinutos},${temp.tipo},${temp.tamanoTablero}"
                        else -> continue //Esto nunca va a pasar, por seguridad se ignora
                    }
                    bw.write(linea)
                    bw.newLine()
                }
            }
        } catch (e: FileNotFoundException){
            println("No se ha podido encontrar el archivo ${e.message}")
        } catch (e: IOException) {
            println(e.message)
        }
    }

    //Metodo para cargar datos
    fun cargarDatos(){
        val f = File ("Recursos/juegosdemesa.txt")
        if (f.exists()){
            try {
                f.bufferedReader().use() { br ->
                    br.forEachLine { linea ->
                        val datos = linea.split(",")

                        val tipo = datos[0].trim()
                        val titulo = datos[1].trim()
                        val nMin = datos [2].toInt()
                        val nMax = datos[3].toInt()
                        val duracion = datos[4].toInt()
                        val tipoJuego = Juego.TipoJuego.valueOf(datos[5])

                        when (tipo) {
                            "CARTAS" -> {
                                val numCartas = datos[6].toInt()
                                coleccionJuegos.add(JuegoCartas(titulo,nMin,nMax,duracion, tipoJuego,numCartas))
                            }
                            "TABLERO" -> {
                                val tablero = datos[6]
                                coleccionJuegos.add(JuegoTablero(titulo,nMin,nMax,duracion,tipoJuego,tablero))
                            }
                        }
                    }
                }
            } catch (e: FileNotFoundException){
                println("Archivo no encontrado: ${e.cause}")
            }
        } else {
            f.createNewFile()
        }

    }
}
