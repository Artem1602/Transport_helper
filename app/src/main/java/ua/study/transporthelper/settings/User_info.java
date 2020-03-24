package ua.study.transporthelper.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_info {

    private static final User_info user_info = new User_info();
    private String user_name;
    private String user_number;

    private LatLng user_location;

    public String getUser_name() {
        return user_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public LatLng getUser_location() {
        return user_location;
    }

    private User_info(){}

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

    public boolean NumberCheck(String number){
        if(number.length()==13){
            if(number.toCharArray()[0]=='+'||number.toCharArray()[1]=='3'||number.toCharArray()[2]=='8'||number.toCharArray()[3]=='0'){
                return true;
            }
        }
        return false;
    }

    public void write_on_server()
    {
        //TODO Firebase or JSON
    }

}
