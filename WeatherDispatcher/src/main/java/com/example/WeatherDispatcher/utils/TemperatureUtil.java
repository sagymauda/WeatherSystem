package com.example.WeatherDispatcher.utils;

public class TemperatureUtil {

    public static boolean checkTemperatureDiff(Double num1, Double num2, Integer threshold) {
        Double diff;
        Double result;
        if (num1 > num2) {
            diff = num1 - num2;
        } else {
            diff = num2 - num1;
        }
        result = (diff / num1) * 100;

        if (result > threshold)
            return true;

        return false;
    }
}