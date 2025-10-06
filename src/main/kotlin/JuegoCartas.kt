import Juego.TipoJuego

class JuegoCartas(
    titulo: String,
    nMin: Int,
    nMax: Int,
    duracionMinutos: Int,
    tipo: TipoJuego,
    var nCartas: Int
):Juego(titulo,nMin,nMax,duracionMinutos,tipo) {
    override fun getDescripcion(): String {
        return "Juego de cartas: $titulo " +
                "| Jugadores: $nMin - $nMax" +
                "| Duracion: $duracionMinutos min" +
                "| Nº de cartas: $nCartas"
    }
}