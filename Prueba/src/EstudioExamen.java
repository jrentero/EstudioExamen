import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EstudioExamen {

    public static void main(String[] args) {
        leeFicheroFiltraPorPitoYGuardaEn2Archivos();
    }

    public static void leeFicheroFiltraPorPitoYGuardaEn2Archivos(){
        String ficheroEntrada = "nombresOscar.txt";
        String regex = "([AEIOU])[^aeiou][aeiou][^aeiou]?";

        File fileEntrada, fileSalidaBien, fileSalidaMal;
        Scanner entrada = null;
        FileWriter escribirBien = null, escribirMal = null;


        StringBuilder builder = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList<>();

        try{
            fileEntrada = new File(ficheroEntrada);
            entrada = new Scanner(fileEntrada);

            fileSalidaBien = new File("bien.txt");
            fileSalidaMal = new File("mal.txt");

            escribirBien = new FileWriter(fileSalidaBien);
            escribirMal = new FileWriter(fileSalidaMal);


            while(entrada.hasNextLine()){
                String nombre = entrada.nextLine();
                builder.append(nombre);
                builder.append(' ');
                arrayList.add(nombre);

                if(Pattern.matches(regex, nombre)) {
                    escribirBien.write(nombre + '\n');
                }else
                    escribirMal.write(nombre+'\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(escribirBien!=null)
                    escribirBien.close();
                if(escribirMal!=null)
                    escribirMal.close();
                if(entrada!=null)
                    entrada.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(builder.toString());

        int a=0, e=0,i=0,o=0,u=0;
        while (matcher.find()){
            String inicial = matcher.group(1);
            switch (inicial){
                case "A" -> a++;
                case "E" -> e++;
                case "I" -> i++;
                case "O" -> o++;
                case "U" -> u++;
            }
        }
        System.out.printf("a = %d, e = %d, i = %d, o = %d, u = %d",a,e,i,o,u);

        for (String nombre: arrayList) {

            if(nombre.matches("Kao\\w*")){
                System.out.println(nombre);
            }
        }

    }
}
