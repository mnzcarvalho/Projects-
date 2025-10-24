package miniifood.entities;

import java.time.LocalDateTime;

public class MAIN {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Loja loja = new Loja(13, localDateTime, localDateTime, "Loja 1");
        System.out.println(loja);
    }
}
