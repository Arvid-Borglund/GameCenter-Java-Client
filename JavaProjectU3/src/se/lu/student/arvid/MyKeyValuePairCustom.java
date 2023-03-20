package se.lu.student.arvid;

import org.tempuri.KeyValuePairCustom;

public class MyKeyValuePairCustom {
    private String key;
    private String value;

    public MyKeyValuePairCustom(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static MyKeyValuePairCustom fromKeyValuePair(KeyValuePairCustom kvp) {
        return new MyKeyValuePairCustom(kvp.getKey(), kvp.getValue());
    }

    public KeyValuePairCustom toKeyValuePair() {
        return new KeyValuePairCustom(key, value);
    }
}
