package co.edu.unbosque.ElecSys.Contrato.ArchivoContrato;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class MetodoCalculoFecha {

    public String mesEnLetras(LocalDate fecha) {
        String[] meses = {
                "ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO",
                "JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"
        };
        return meses[fecha.getMonthValue() - 1];
    }

    public String numeroALetras(int numero) {
        if (numero == 0) return "CERO";

        if (numero < 0) {
            return "MENOS " + numeroALetras(Math.abs(numero));
        }

        return convertir(numero).trim();
    }

    private String convertir(int numero) {

        String[] unidades = {
                "", "UNO", "DOS", "TRES", "CUATRO",
                "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE",
                "DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE",
                "QUINCE", "DIECISEIS", "DIECISIETE",
                "DIECIOCHO", "DIECINUEVE", "VEINTE"
        };

        String[] decenas = {
                "", "", "VEINTE", "TREINTA", "CUARENTA",
                "CINCUENTA", "SESENTA", "SETENTA",
                "OCHENTA", "NOVENTA"
        };

        String[] centenas = {
                "", "CIENTO", "DOSCIENTOS", "TRESCIENTOS",
                "CUATROCIENTOS", "QUINIENTOS",
                "SEISCIENTOS", "SETECIENTOS",
                "OCHOCIENTOS", "NOVECIENTOS"
        };

        if (numero == 100) return "CIEN";

        if (numero <= 20) return unidades[numero];

        if (numero < 30) return "VEINTI" + unidades[numero - 20];

        if (numero < 100) {
            int d = numero / 10;
            int r = numero % 10;
            return decenas[d] + (r > 0 ? " Y " + unidades[r] : "");
        }

        if (numero < 1000) {
            int c = numero / 100;
            int r = numero % 100;
            return centenas[c] + (r > 0 ? " " + convertir(r) : "");
        }

        if (numero < 1_000_000) {
            int m = numero / 1000;
            int r = numero % 1000;
            String miles = (m == 1) ? "MIL" : convertir(m) + " MIL";
            return miles + (r > 0 ? " " + convertir(r) : "");
        }

        if (numero < 1_000_000_000) {
            int m = numero / 1_000_000;
            int r = numero % 1_000_000;
            String millones = (m == 1) ? "UN MILLON" : convertir(m) + " MILLONES";
            return millones + (r > 0 ? " " + convertir(r) : "");
        }

        return String.valueOf(numero);
    }

    public String formatearMoneda(BigDecimal valor) {
        if (valor == null) return "0";

        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        formato.setMinimumFractionDigits(0);
        formato.setMaximumFractionDigits(0);

        return formato.format(valor);
    }

    public String salarioEnLetras(BigDecimal salario) {
        if (salario == null) return "";

        int valor = salario.intValue();
        return numeroALetras(valor);
    }
}
