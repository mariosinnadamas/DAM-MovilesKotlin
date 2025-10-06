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
        var encontrado = Boolean
        for(temp in coleccionJuegos){
            val tituloJuego : kotlin.String = temp.titulo.lowercase()
            if (tituloJuego.startsWith(titulo)){
                println(temp)
            } else{
                println("No se ha encontrado")
            }
        }
    }
}
