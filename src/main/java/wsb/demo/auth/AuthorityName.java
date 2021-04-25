package wsb.demo.auth;

public enum AuthorityName {
     ROLE_USERS_TAB("Dostęp do zakładki użytkownicy"),
     ROLE_CREATE_USER("Zarządzanie użytkownikami-towrzenie"),
     ROLE_DELETE_USER("Zarządzanie użytkownikami-usuwanie"),
     ROLE_MANAGE_PROJECT("Zarządzanie projektami");



     private String alternativeName;

     private AuthorityName(String alternativeName){
          this.alternativeName = alternativeName;
     }

     public String getAlternativeName() {
          return alternativeName;
     }



}
