package ua.study.behome.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_info {

    private static final User_info user_info = new User_info();
    private String user_name;
    private String user_number;
    private String user_address;
    private LatLng user_location;
    private boolean key;

    private User_info(){}

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public String getUser_address() {
        return user_address;
    }

    public LatLng getUser_location() {
        return user_location;
    }

    public static User_info getInstance(){
        return user_info;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public void setUser_location(LatLng user_location) {
        this.user_location = user_location;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    Double roundNumbers(Double number){
            number=number*10000;
            number=Math.ceil(number);
            number=number/10000;
        return number;
    }

    public String LatlngStringParser() {
        String result;
        Double latitude = user_location.latitude;
        Double longitude = user_location.longitude;
        result = roundNumbers(latitude).toString() + "/" + roundNumbers(longitude).toString();
        return result;
    }
}
