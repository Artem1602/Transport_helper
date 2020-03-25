package ua.study.transporthelper.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_info {

    private static final User_info user_info = new User_info();
    private String user_name;
    private String user_number;
    private String user_address;
    private LatLng user_location;

    private User_info(){}

    public String getUser_name() {
        return user_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public LatLng getUser_location() {
        return user_location;
    }

    public String getUser_address() {
        return user_address;
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


    public void write_on_server()
    {
        //TODO Firebase or JSON
    }

}
