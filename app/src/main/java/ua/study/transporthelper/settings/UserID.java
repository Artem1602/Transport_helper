package ua.study.transporthelper.settings;

public class UserID {
    private String number;
    public String name;

    public boolean NumberCheck(String number){
        if(number.length()==13){
            if(number.toCharArray()[0]=='+'||number.toCharArray()[1]=='3'||number.toCharArray()[2]=='8'||number.toCharArray()[3]=='0'){
                return true;
            }
        }
        return false;
    }

    UserID(String number, String name){
        this.name=name;
        this.number=number;
    }
}
