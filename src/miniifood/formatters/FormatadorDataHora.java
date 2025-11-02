package miniifood.formatters; //

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatadorDataHora {

    public static String formatarData(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatarHora (LocalDate hora){
        return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public static String formatarDataHora (LocalDate dataHora){
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
}
