package ua.study.transporthelper.settings;

import com.google.android.gms.maps.model.LatLng;

public class User_Firabase {
        LatLng location;
        String name;

    String toStringParser(){
        String result;
        Double latitude = location.latitude;
        Double longitude = location.longitude;
        result = this.name + "/" + latitude.toString() + "/" + longitude.toString();
        return result;
    }

        public LatLng getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public User_Firabase(LatLng location, String name) {
            this.location = location;
            this.name = name;

        }
        static User_Firabase toUserParser(String string){
            String[] parsing = string.split("/");
            double latitude ;
            double longitude;
            LatLng location  = new LatLng(Double.parseDouble(parsing[1]),Double.parseDouble(parsing[2]));
            User_Firabase userok = new User_Firabase(location,parsing[0]);
            return userok;
        }

        @Override
        public String toString() {
            return "User{" +
                    "location=" + location.latitude+"/"+location.longitude +
                    ", name='" + name + '}';
        }
    }


