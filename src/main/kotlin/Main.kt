import Juego.TipoJuego
import java.util.*

fun main(){
    val sc = Scanner (System.`in`)
    val gestor: GestorJuegos = GestorJuegos()
    var eleccion : String

    while (true){
        println("MENU DE JUEGOS DE MESA")
        println("1. Añadir juego")
        println("2. Listar juegos")
        println("3. Buscar juego por nombre")
        println("4. Salir")
        eleccion = sc.nextLine()
        when(eleccion){
            "1" -> {
                println("MENÚ INSERCIÓN DE JUEGO")

                var comprobado = false
                var nombreJuego = ""
                while (!comprobado){
                    println("Ingrese el nombre del juego:")
                    var nombreJuego = sc.nextLine()
                    if (nombreJuego.isNotEmpty()){
                        comprobado = true;
                    } else{
                        println("No puedes dejar el nombre vacío")
                    }
                }
                comprobado = false

                var nMin = 0
                while (!comprobado) {
                    println("Ingrese número mínimo de jugadores: ")
                    val input = sc.nextLine()

                    if (input.isEmpty()) {
                        System.err.println("No puedes dejar este campo vacío")
                        continue
                    }
                    try {
                        nMin = input.toInt()
                        if (nMin < 1) {
                            System.err.println("No puede haber menos de 1 jugador")
                        } else {
                            comprobado = true
                        }
                    } catch (e: NumberFormatException) {
                        System.err.println("Debe introducir un numero entero")
                    }
                }

                comprobado =  false

                var nMax: Int = 0
                while (!comprobado) {
                    println("Ingrese número máximo de jugadores: ")
                    val input = sc.nextLine()

                    if (input.isEmpty()) {
                        System.err.println("No puedes dejar este campo vacío")
                        continue
                    }
                    try {
                        nMax = input.toInt()
                        if (nMax < nMin) {
                            System.err.println("No puede haber menos jugadores que el minimo de jugadores")
                        } else {
                            comprobado = true
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        System.err.println("Debe introducir un numero entero")
                    }
                }

                comprobado = false

                var duracionMin = 0
                while (!comprobado) {
                    println("Ingrese la duración en minutos: ")
                    val input = sc.nextLine()

                    if (input.isEmpty()) {
                        System.err.println("No puedes dejar este campo vacío")
                        continue
                    }
                    try {
                        duracionMin = input.toInt()
                        if (duracionMin < 1) {
                            System.err.println("La partida no puede durar menos de 1 minuto")
                        } else {
                            comprobado = true
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        System.err.println("Debe introducir un numero entero")
                    }
                }
                comprobado = false

                var tipoJuego: TipoJuego? = null
                while (tipoJuego == null) {
                    println("Escriba el tipo de juego exactamente: ")
                    for (t in TipoJuego.entries) {
                        println(t)
                    }

                    val tipo = sc.nextLine().uppercase(Locale.getDefault())
                    try {
                        tipoJuego = TipoJuego.valueOf(tipo)
                    } catch (e: IllegalArgumentException) {
                        System.err.println("Opción no válida, inténtelo de nuevo")
                    }
                }

                var decTipo = 0
                while (!comprobado) {
                    println("Seleccione el tipo de juego: ")
                    println("1. Juego de cartas")
                    println("2. Juego de tablero")
                    val input = sc.nextLine()

                    if (input.isEmpty()) {
                        System.err.println("No puedes dejar este campo vacío")
                        continue
                    }
                    try {
                        decTipo = input.toInt()
                        if (decTipo != 1 && decTipo != 2) {
                            System.err.println("Debe introducir 1 o 2")
                        } else {
                            comprobado = true
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        System.err.println("Debe introducir un numero entero")
                    }
                }

                comprobado = false

                if (decTipo == 1) {
                    var nCartas: Int
                    while (!comprobado) {
                        println("¿Cuántas cartas tiene?")
                        val input = sc.nextLine()

                        if (input.isEmpty()) {
                            System.err.println("No puedes dejar este campo vacío")
                            continue
                        }

                        try {
                            nCartas = input.toInt()
                            if (nCartas >= 1) {
                                comprobado = true

                                gestor.anadirJuegos(
                                    JuegoCartas(nombreJuego, nMin, nMax, duracionMin, tipoJuego, nCartas))
                            } else {
                                System.err.println("No puede haber menos de 1 carta")
                            }
                        } catch (e: java.lang.NumberFormatException) {
                            System.err.println("Debe introducir un numero entero")
                        }
                    }
                } else {
                    var tablero: String
                    while (!comprobado) {
                        println("¿Cuanto mide el tablero? Ej: 20x20, 15x20, etc")
                        tablero = sc.nextLine()
                        if (tablero.isEmpty()) {
                            System.err.println("Debe introducir un tamaño de tablero")
                        } else {
                            comprobado = true
                            gestor.anadirJuegos(
                                JuegoTablero(nombreJuego, nMin, nMax, duracionMin, tipoJuego, tablero))
                        }
                    }
                }
                comprobado = false
            }
            "2" -> gestor.listarJuegos()
            "3" -> {
                println("MENÚ BUSQUEDA POR TITULO")
                print("Inserte el titulo del juego: ")
                val titulo = sc.nextLine()
                gestor.busquedaPorTitulo(titulo)
            }
            "4" -> break
        }
    }
}

