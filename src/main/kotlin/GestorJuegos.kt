import java.lang.String
import java.util.*
import kotlin.Any
import kotlin.Comparator

class GestorJuegos {
    private val sc = Scanner(System.`in`)

    //Coleccion ordenada por nombre, case-insensitive
    val coleccionJuegos: MutableSet<Juego> =
        TreeSet (compareBy (String.CASE_INSENSITIVE_ORDER){ it.titulo })

}