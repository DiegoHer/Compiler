/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import Interface.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Olayo
 */
public class sintactic_Analysis {
private Interface Interface;
private data data;
private String patron = ("(include|stdio.h|stdlib.h|main|for|while|double|if|int|float|do|bool|char|String|cout|printf)|"//palabras reservadas
                            + "([a-zA-Z]+)|"//variables
                            + "([#|(|)|<|>|[|]|{|}|+|-|*|/|;|=]+)|"//simbolos
                            + "([0-9]+)");//numeros
private String codigo;
private Pattern pattern;
private Matcher matcher;

    public void set_sintactic_Analysis(Interface Interface, data data){
        this.Interface = Interface;
        this.data = data;
    }
    
    public void proccess(){
        Interface.errors.setText("");//limpia la ventana de errores
    }
    
    public void cabeceras(){
        int indice = 0;
        int full = 5;
        boolean cabecera = false;
        String automata [] = {"1", "16", "4", "18", "5"};
        
        for(int i = 0; i < 30; i ++)
        {
            try{
                 if(data.SymbolsTable[i][2].equals("1"))
                    {
                        indice = i;
                        cabecera = true;
                        break;
                    }
            }catch(NullPointerException ex){}
        }
        if(cabecera == false)//error
        {
            Interface.errors.setText("No hay cabecera o librerias");
        }
        else
        {
            for(int j = indice; j <= indice + 4; j ++)
            {
                try
                {
                    if(data.SymbolsTable[j][2].equals(automata[j]))
                    {
                        full = full - 1;
                    }
                }catch(NullPointerException Ex){Interface.errors.setText("Error en cabeceras");}
            }
            if(full != 0)
            {
                Interface.errors.setText("Error en cabeceras");
            } 
        }
    }
    
}
