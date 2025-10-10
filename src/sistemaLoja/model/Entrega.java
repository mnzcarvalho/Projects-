package sistemaLoja.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Entrega {
    protected LocalDateTime dataEnvio;
    protected LocalDateTime dataPrevista;
    protected String enderecoDestino;

    public Entrega(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
        this.dataEnvio = LocalDateTime.now();
    }

    public abstract void calcularDataPrevista();

    public String getDataPrevistaFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataPrevista.format(formatter);
    }

    public String getEnderecoDestino(){
        return "Entrega para: " + enderecoDestino + " (prevista: " + getDataPrevistaFormatada() +")";
    }
}
