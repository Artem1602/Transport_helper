package ua.study.behome.settings;


import com.google.android.gms.maps.model.LatLng;

public class User_Firebase {
    private String user_name;
    private String user_number;
    private String user_address;
    private String user_location;
    private String record_time;
    private boolean key;

    public User_Firebase() {}
    public User_Firebase(String user_name, String user_number, String user_address, String user_location, boolean key, String record_time) {
        this.user_name = user_name;
        this.user_number = user_number;
        this.user_address = user_address;
        this.user_location = user_location;
        this.key = key;
        this.record_time = record_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public LatLng toLatLngParser(String string){
        String[] loc = string.split("/");
        LatLng location  = new LatLng(Double.parseDouble(loc[0]),Double.parseDouble(loc[1]));
        return location;
    }

}


