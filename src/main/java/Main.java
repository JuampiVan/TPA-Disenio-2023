import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_Caracteres=8;
        final int MIN_Mayusculas=1;
        final int MIN_Minusculas=1;
        final int MIN_Numeros=1;
        final int Special=1;
        int mayusCounter=0;
        int minusCounter=0;
        int numberCounter=0;
        int specialCounter=0;

        Scanner input = new Scanner(System.in);

        System.out.println("Ingresa el usuario");
        String usuario = input.nextLine();

        System.out.println("Ingresa la contrasenia");
        String password = input.nextLine();

        for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c))
                mayusCounter++;
            else if(Character.isLowerCase(c))
                minusCounter++;
            else if(Character.isDigit(c))
                numberCounter++;
            if(c>=33&&c<=46||c==64){
                specialCounter++;
            }

        }

        if (password.length() >= MAX_Caracteres && mayusCounter >= MIN_Mayusculas
                && minusCounter >= MIN_Minusculas && numberCounter >= MIN_Numeros && specialCounter >= Special
                && !esContraseniaMala(password)) {
            System.out.println("Contrasenia Valida");
            Usuario usuario1 = new Usuario(usuario,password);
        }
        else {
            System.out.println("Tu contrasenia es incorrecta por los siguientes motivos:");
            if(esContraseniaMala(password))
                System.out.println("Pertenece al top 10000 peores contrasenias");
            if(password.length() < MAX_Caracteres)
                System.out.println("Debe poseer al menos 8 caracteres");
            if (minusCounter < MIN_Minusculas)
                System.out.println("Debe poseer al menos una minuscula");
            if (mayusCounter < MIN_Mayusculas)
                System.out.println("Debe poseer al menos una mayuscula");
            if(numberCounter < MIN_Numeros)
                System.out.println("Debe poseer al menos una numero");
            if(specialCounter < Special)
                System.out.println("Debe poseer al menos un caracter especial");

        }



//        Scanner lectura = new Scanner(System.in);
//        System.out.println("VALIDADOR DE CONTRASENIAS");
//        System.out.println("Ingrese Usuario: ");
//        String usuario = lectura.next();
//        System.out.println("Ingrese Contrasenia: ");
//        String contrasenia = lectura.next();
//        if (esContraseniaMala(contrasenia)){
//            System.out.println("La contrasenia pertenece al top 10000 peores contrasenias");
//        } else if (esContraseniaCorta(contrasenia)){
//            System.out.println("La contrasenia debe ser superior a 8 caracteres");
//        } else if (!poseeNumeros(contrasenia)){
//            System.out.println("La contrasenia debe contener numeros");
//        } else if (!poseeMayusculas(contrasenia)){
//            System.out.println("La contrasenia debe poseer mayusculas");
//        } else if (!poseeMinusculas(contrasenia)){
//            System.out.println("La contrasenia debe poseer minusculas");
//        } else if (!poseeCaracteresEspeciales(contrasenia)) {
//            System.out.println("La contrasenia debe poseer algun caracter especial");
//        } else {
//            System.out.println("Contrasenia correcta");
//        }

//        List<String> peoresContrasenias;
//        Usuario usuario = new Usuario();
//        peoresContrasenias = usuario.listaPeoresContrasenias();
//        System.out.println(peoresContrasenias);

//        String passwd = "aaAaaaa";
//        String pattern = "(?=.*[@#$%^&+=_]).*$";
//        System.out.println(passwd.matches(pattern));
    }
    static public boolean validaContrasenia(String contrasenia){
        if (esContraseniaCompleja(contrasenia) && !esContraseniaMala(contrasenia)){
            return true;
        } else {
            return false;
        }
    }

    static public boolean esContraseniaMala(String contrasenia){
        List<String> peoresContrasenias;
        peoresContrasenias = listaPeoresContrasenias();
        return peoresContrasenias.contains(contrasenia);
    }

    static public boolean poseeNumeros(String contrasenia){
        String pattern = "(?=..*[0-9]).*$";
        return contrasenia.matches(pattern);
    }
    static public boolean poseeMinusculas(String contrasenia){
        String pattern = "(?=.*[a-z]).*$";
        return contrasenia.matches(pattern);
    }
    static public boolean poseeMayusculas(String contrasenia){
        String pattern = "(?=.*[A-Z]).*$";
        return contrasenia.matches(pattern);
    }
    static public boolean poseeCaracteresEspeciales(String contrasenia){
        String pattern = "(?=.*[@#$%^&+=_]).*$";
        return contrasenia.matches(pattern);
    }

    static public boolean esContraseniaCompleja(String contrasenia){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return contrasenia.matches(pattern);
    }

    static public boolean esContraseniaCorta(String contrasenia){
        return contrasenia.length()<8;
    }

    static public List<String> listaPeoresContrasenias(){
        List<String> peoresContrasenias = new ArrayList<>();
        String path = "10000PeoresContrasenias.txt";

        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String contrasenia = scanner.nextLine();
                peoresContrasenias.add(contrasenia);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return peoresContrasenias;
    }
}
