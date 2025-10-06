package fabrica.lineaDemo.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CodigoProduccionGenerator {

    public static List<String> generarCodigos(String codigoProducto, int cantidad, int inicio){

        List<String> codigos = new ArrayList<>();

        String fecha = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        for(int i = 0; i< cantidad ; i++){
            String codigo = String.format("%s-%s-%4d",
                    codigoProducto,fecha,inicio + i); //indicamos que sea formato string-string-4digitos.
            codigos.add(codigo);

        }
        return codigos;
    }



}
