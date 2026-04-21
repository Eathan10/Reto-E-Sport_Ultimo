package Utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {


    public static boolean validarDatos(String var,String exprecion){

        Pattern pattern = Pattern.compile(exprecion);
        Matcher matcher = pattern.matcher(var);

        if(matcher.matches()){
            return true;
        }


        return false;
    }

}
