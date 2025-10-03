abstract class Juego (
    var titulo: String,
    var nMin: Int,
    var nMax: Int,
    var duracionMinutos: Int,
    var tipo: TipoJuego){
    enum class TipoJuego {
        ESTRATEGIA,
        PARTY,
        FAMILIAR
    }
    abstract fun getDescripcion(): String
}