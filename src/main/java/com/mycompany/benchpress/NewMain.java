/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.benchpress;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Vikke
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        FileReader fr = new FileReader("benchpresspoll.json");
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(fr);
        JSONArray ja = (JSONArray) jo.get("BenchpressPoll");
        
        //Benching kvinnor
        int Oa =0;
        //Icke-Benching kvinnor
        int Ob =0;
        //Benching män
        int Oc =0;
        //Icke-Benching män
        int Od =0;
        //Kritiska X2-värdet för signifikansnivån 0.05
        double Calpha = 3.841;
        ArrayList<Person> persons;
        persons = new ArrayList();
        
        for(int i = 0; i < ja.size(); i++)
        {
            JSONObject jsonElement = (JSONObject) ja.get(i);
            String gender = jsonElement.get("gender").toString();
            String bench = jsonElement.get("bench").toString();
            
            Person person = new Person(gender, bench);  
            persons.add(person);
            switch (gender){
                case "woman":
                    if ("yes".equals(bench)){Oa +=1;}
                    if ("no".equals(bench)){Ob +=1;}
                    break;
                case "man":
                    if ("yes".equals(bench)){Oc +=1;}
                    if ("no".equals(bench)){Od +=1;}
                    break;
            }
        }
        //Antalet bänkare
        int Oac = Oa + Oc;
        //antalet icke-bänkare
        int Obd = Ob + Od;
        //antalet kvinnor
        int Oab = Oa + Ob;
        //antalet män
        int Ocd = Oc + Od;
        //N = antal samples 
        int N = Oab + Ocd;
        
        
        System.out.println("Antalet kvinnor som tränar bänkpress "+Oa);
        System.out.println("Antalet kvinnor som inte tränar bänkpress "+Ob);
        System.out.println("Antalet män som tränar bänkpress "+Oc);
        System.out.println("Antalet män som inte tränar bänkpress "+Od);
        
        double Ea = (Oab * Oac)/N;
        double Eb = (Oab * Obd)/N;
        double Ec = (Ocd * Oac)/N;
        double Ed = (Ocd * Obd)/N;
        
        double X2 = ((Oa - Ea)*(Oa - Ea))/Ea
                  + ((Ob - Eb)*(Ob - Eb))/Eb 
                  + ((Oc - Ec)*(Oc - Ec))/Ec
                  + ((Od - Ed)*(Od - Ed))/Ed;
        
        System.out.println("X2 = " + X2);
        
        if(X2 > Calpha)
        {
            System.out.println("Ja, bänkpress är faktiskt beroende av kön!");
        }
        
        else
            System.out.println("Nej, bänkpress är oberoende av kön!");


    }
    
}
