package org.example.petshop.utils;

import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;

public class MultiMaskTextFormatter extends TextFormatter<String> {

    public enum MaskType {
        CPF, TELEFONE
    }

    public MultiMaskTextFormatter(MaskType type) {
        super(getTextFormatterFilter(type));
    }

    private static UnaryOperator<Change> getTextFormatterFilter(MaskType type) {
        switch (type) {
            case CPF:
                return cpfFilter();
            case TELEFONE:
                return telefoneFilter();
            default:
                throw new IllegalArgumentException("Tipo de máscara não suportado.");
        }
    }

    private static UnaryOperator<TextFormatter.Change> cpfFilter() {
        return change -> {
            String newText = change.getControlNewText();

            newText = newText.replaceAll("[^\\d]", "");

            if (newText.length() > 11) {
                return null;
            }

            StringBuilder formattedText = new StringBuilder();

            int length = newText.length();
            if (length > 0) {
                formattedText.append(newText.substring(0, Math.min(3, length)));
                if (length > 3) {
                    formattedText.append('.').append(newText.substring(3, Math.min(6, length)));
                }
                if (length > 6) {
                    formattedText.append('.').append(newText.substring(6, Math.min(9, length)));
                }
                if (length > 9) {
                    formattedText.append('-').append(newText.substring(9, length));
                }
            }

            int oldCaretPosition = change.getCaretPosition();
            int newCaretPosition = oldCaretPosition;

            if (change.isDeleted()) {
                if (oldCaretPosition > 0 && (oldCaretPosition < formattedText.length())) {
                    if (formattedText.charAt(oldCaretPosition - 1) == '.' ||
                            formattedText.charAt(oldCaretPosition - 1) == '-') {
                        newCaretPosition--;
                    }
                }
            } else {
                if (oldCaretPosition > 0 && (oldCaretPosition < formattedText.length())) {
                    if (formattedText.charAt(oldCaretPosition - 1) == '.' ||
                            formattedText.charAt(oldCaretPosition - 1) == '-') {
                        newCaretPosition++;
                    }
                }
            }

            change.setText(formattedText.toString());
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(Math.min(newCaretPosition, formattedText.length()));
            change.setAnchor(Math.min(newCaretPosition, formattedText.length()));

            return change;
        };
    }


    private static UnaryOperator<TextFormatter.Change> telefoneFilter() {
        return change -> {
            String newText = change.getControlNewText();

            newText = newText.replaceAll("[^\\d]", "");

            if (newText.length() > 11) {
                return null;
            }

            StringBuilder formattedText = new StringBuilder(newText);

            if (newText.length() >= 2) {
                formattedText.insert(0, '(');
                formattedText.insert(3, ')');
            }
            if (newText.length() >= 7) {
                formattedText.insert(9, '-');
            }

            int caretPosition = change.getCaretPosition();
            int newCaretPosition = caretPosition;

            if (change.isDeleted()) {
                if (caretPosition > 0 && caretPosition <= formattedText.length()) {
                    if (formattedText.charAt(caretPosition - 1) == '(' ||
                            formattedText.charAt(caretPosition - 1) == ')' ||
                            formattedText.charAt(caretPosition - 1) == '-') {
                        newCaretPosition--;
                    }
                }
            } else {
                if (caretPosition == 2) {
                    newCaretPosition += 2 ;
                } else if (caretPosition == 9) {
                    newCaretPosition++;
                }
            }

            change.setText(formattedText.toString());
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(Math.min(newCaretPosition, formattedText.length()));
            change.setAnchor(Math.min(newCaretPosition, formattedText.length()));

            return change;
        };
    }
}