package sample.uni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Human {
    public String name;
    public String surname;
    public String patronymic;
    public Date birthday;
    public String email;

    public int getAge(Date birthday) {
        Date curDate = new Date();
        try {
            int days = (int) ((curDate.getTime() - birthday.getTime()) / (24 * 60 * 60 * 1000));
            return days / 365;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Human(String name, String surname, String patronymic, String birthday, String email) throws ParseException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        try {
            this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFIO(){
        return this.surname + this.name.charAt(0) + this.patronymic.charAt(0);
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getPatronymic(){
        return this.patronymic;
    }

    public String getBirthday(){
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        return form.format(birthday);
    }

    public String getEmail(){
        return this.email;
    }

    public void printHuman() {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("FIO: " + surname + " " + name + " " + patronymic);
        System.out.println("Birthday: " + form.format(birthday));
        System.out.println("Age: " + getAge(birthday));
        System.out.println("Email: " + email);
    }

}