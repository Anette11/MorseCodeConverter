package com.example.morsecodeconverter;

import android.content.Context;
import android.content.ContextWrapper;

import java.util.HashMap;

public class MorseCode extends ContextWrapper {
    private final HashMap<String, String> hashMapSymbolsToMorseCodes;

    public MorseCode(Context base) {
        super(base);
        String[] symbols = getResources().getStringArray(R.array.symbols);
        String[] codes = getResources().getStringArray(R.array.codes);
        hashMapSymbolsToMorseCodes = populateHashMap(symbols, codes);
    }

    private HashMap<String, String> populateHashMap(
            String[] stringArray1,
            String[] stringArray2
    ) {
        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i < stringArray1.length; i++) {
            hashMap.put(stringArray1[i], stringArray2[i]);
        }
        return hashMap;
    }

    public String convertSymbolsToMorseCode(
            String textToMorseCode,
            HashMap<String, String> hashMap
    ) {
        String text = textToMorseCode.trim().toLowerCase();
        String[] stringArray = text.split(getString(R.string.empty));
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : stringArray) {
            stringBuilder.append(hashMap.get(s));
            stringBuilder.append(getResources().getString(R.string.two_spaces));
        }

        stringBuilder.delete(0, 6);

        return stringBuilder.toString()
                .replace("null", getResources().getString(R.string.two_spaces))
                .trim();
    }

    public HashMap<String, String> getHashMapSymbolsToMorseCodes() {
        return hashMapSymbolsToMorseCodes;
    }
}
