package se.lu.student.borglund;

import org.tempuri.CronusWebService.*;


public class MyKeyValuePairCronus {
    private String key;
    private String value;

    public MyKeyValuePairCronus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static MyKeyValuePairCronus fromKeyValuePair(KeyValuePairCronus kvp) {
        return new MyKeyValuePairCronus(kvp.getKey(), kvp.getValue());
    }

    public KeyValuePairCronus toKeyValuePair() {
        return new KeyValuePairCronus(key, value);
    }
	
}
