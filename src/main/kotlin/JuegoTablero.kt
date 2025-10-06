class JuegoTablero (
    titulo: String,
    nMin: Int,
    nMax: Int,
    duracionMinutos: Int,
    tipo: TipoJuego,
    var tamanoTablero: String
) : Juego(titulo, nMin, nMax, duracionMinutos, tipo){

    override fun getDescripcion(): String {
        return "Juego de tablero: $titulo " +
                "| Jugadores: $nMin - $nMax" +
                "| Duracion: $duracionMinutos min" +
                "| Tablero: $tamanoTablero"
    }
}