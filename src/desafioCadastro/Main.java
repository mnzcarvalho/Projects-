package desafioCadastro;

public class Main {


        public static void main(String[] args) {
            ManipuladorArquivo test = new ManipuladorArquivo();
            try {
                test.leitorArquivo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
