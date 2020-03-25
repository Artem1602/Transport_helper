package ua.study.transporthelper.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_Firabase {
    private LatLng location;
    private String name;
    private String user_address;
    String toStringParser(){
        String result;
        Double latitude = location.latitude;
        Double longitude = location.longitude;
        result = this.name + "/" + this.user_address + "/" + latitude.toString() + "/" + longitude.toString();
        return result;
    }

        public LatLng getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public User_Firabase(LatLng location, String name, String user_address) {
            this.location = location;
            this.name = name;
            this.user_address = user_address;
        }

        static User_Firabase toUserParser(String string){
            String[] parsing = string.split("/");
            double latitude ;
            double longitude;
            LatLng location  = new LatLng(Double.parseDouble(parsing[2]),Double.parseDouble(parsing[3]));
            User_Firabase userok = new User_Firabase(location,parsing[0],parsing[1]);
            return userok;
        }

        @Override
        public String toString() {
            return "User{" +
                    "location=" + location.latitude+"/"+location.longitude +
                    ", name='" + name + ", addres=" + user_address+ '}';
        }
    }


