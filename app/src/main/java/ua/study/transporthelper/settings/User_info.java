package ua.study.transporthelper.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_info {

    private static final User_info user_info = new User_info();
    public String user_number;
    public String user_telephone;
    public LatLng user_location;

    private User_info(){}

    public static User_info getInstance(){
        return user_info;
    }

    public void setter(String user_number, String user_telephone, LatLng user_location) {
        this.user_number = user_number;
        this.user_telephone = user_telephone;
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
