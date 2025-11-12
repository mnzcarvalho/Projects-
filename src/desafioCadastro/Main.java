package desafioCadastro;

import desafioCadastro.services.ManipuladorArquivo;

import static desafioCadastro.ui.MenuInicial.menuInicial;

public class Main {


        public static void main(String[] args) {

//            menuInicial();

            ManipuladorArquivo test = new ManipuladorArquivo();
            try {
                test.leitorArquivo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
