package desafioCadastro;

import java.util.Scanner;

public class MenuInicial {
    
    public static void menuInicial(){
       Scanner opcao = new Scanner(System.in);
       if (opcao != "\\d+")
        switch (opcao){
            case 1:
                System.out.println("1. Cadastrar novo pet");
            case 2:

            case 3:

            case 4:

            case 5:

            case 6:
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        }
    }
}
