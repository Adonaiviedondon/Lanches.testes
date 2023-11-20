/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Formatacao;

import java.awt.TextField;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.scene.control.TextFormatter;


/**
 *
 * @author tyago
 */
public class FormatMasck extends Application{


    public static void aplicarMascaraTelefone(TextField textField) {
        // Cria um TextFormatter para formatar o número de telefone
        TextFormatter<Number> textFormatter = new TextFormatter<>(new NumberStringConverter());

        // Adiciona um filtro para aceitar apenas dígitos
        UnaryOperator<TextFormatter.Change> filtroNumerico = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                return null; // Não aceita a alteração (entrada não numérica)
            }
        };

        textFormatter.getFilter().accept(new TextFormatter.Change(0, 0, "", "0"));
        textFormatter.setFilter(filtroNumerico);

        // Aplica o TextFormatter ao TextField
        textField.setTextFormatter(textFormatter);
    

    }
}
