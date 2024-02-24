

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * La clase contiene m�odos est�ticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 */
public class FestivalesIO {

    
    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.
                    getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
    }

    /**
     * se parsea la l�nea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        String nombre;
        String lugar;
        LocalDate fechaIni = null;
        int duracion = 0;
        HashSet<Estilo> estilos = new HashSet<>();

        String[] festival = lineaFestival.split(":");

        nombre = festival[0].trim();
        lugar = festival[1].trim();
        fechaIni = LocalDate.parse(festival[2].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        duracion = Integer.parseInt(festival[3].trim());

                String[] estilosTexto = festival[4].split(":");
                for (String estilotexto : estilosTexto) {
                    Estilo miEstilo = Estilo.valueOf(estilotexto.trim().toUpperCase());
                    estilos.add(miEstilo);
                }
        return new Festival(nombre, lugar, fechaIni, duracion, estilos);
    }
}
