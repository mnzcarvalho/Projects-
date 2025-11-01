public class FormatadorTelefone {
  
  public static String formatar(String telefone) {
      if(telefone.length() != 10 && telefone.length() != 11){
          return "Número inválido";
      } else if (telefone.length() == 10) {
         telefone = telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
         return telefone;
      }else if (telefone.length() == 11) {
         telefone = telefone.replaceAll("(\\d{2})(\\d)(\\d{4})(\\d{4})", "($1) $2 $3-$4");
         return telefone;
  }
  return "Número inválido";
  }
  
  public static String removerFormatacao(String telefone){
    telefone =  telefone.replaceAll("\\D", "");
    return telefone;
  }
}
