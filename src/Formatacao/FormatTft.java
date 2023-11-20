/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Formatacao;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;



public class FormatTft extends PlainDocument {
	
	
	public enum TipoEntrada {
    	
         CPF, NCARTAO, NOME, CVV, PRECO, TELEFONE, CLASSIFICACAO;
        	
        }

        private final int tamanhoMax;
        private final TipoEntrada tpEntrada;
       
        public FormatTft(int tamanhoMax, TipoEntrada tpEntrada){
            this.tamanhoMax = tamanhoMax;
            this.tpEntrada = tpEntrada; 
        }

        @Override
        public void insertString(int i, String string, AttributeSet as) throws BadLocationException  {
        	
        	if(string == null || getLength() == tamanhoMax) {
        		return;
        	}
        	
        	int totalCarac = getLength() + string.length();
        	
        	String regex = "";
            switch(tpEntrada) {
//            case EMAIL:	  regex = "[^\\p{IsLatin}@.\\-_][^0-9]"; break;
            case CPF:     regex = "[^0-9]"; break;
            case NCARTAO: regex = "[^0-9]"; break;
            case NOME:    regex = "[^\\p{IsLatin} ]"; break;
            case CVV:	  regex = "[^0-9]"; break;     
            case PRECO:   regex = "[^0-9]"; break;
            case TELEFONE : regex = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$\\p{isNumeric}"; break;
            case CLASSIFICACAO: regex = "[^0-9]"; break;
            
            }  
            
            string = string.replaceAll(regex, "");
            
            
            if(totalCarac <= tamanhoMax) {
            	super.insertString(i, string, as);
            }else {
            	String nova = string.substring(0, tamanhoMax);
            	super.insertString(i, nova, as);
            }
            
            
        }
        
        
       
      
 	}