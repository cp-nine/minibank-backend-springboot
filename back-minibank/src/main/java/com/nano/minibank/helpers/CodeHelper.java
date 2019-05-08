package com.nano.minibank.helpers;

public class CodeHelper {

    public static String makeCode(String x, String text, int panjang){
        String kode = text;
        int panjangX = x.length();
        int panjangText = text.length();
        int sisa = panjangText - panjang;

        if (panjangText > (panjang-panjangX)){
            kode = kode.substring(sisa + panjangX, panjangText);
        }

        return x + kode;
    }

}
