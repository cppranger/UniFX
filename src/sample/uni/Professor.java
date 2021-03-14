package sample.uni;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Professor extends Human{

    String faculty;
    String position;
    String specialisation;

    public Professor(String name, String surname, String patronymic, String birthday, String email, String faculty, String position, String specialisation) throws ParseException {
        super(name, surname, patronymic, birthday, email);
        this.faculty = faculty;
        this.position = position;
        this.specialisation = specialisation;
    }

    @Override
    public void printHuman() {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("FIO: " + surname + " " + name + " " + patronymic);
        System.out.println("Faculty: " + faculty + ". Position: " + position + ". Specialisation: " + specialisation);
        System.out.println("Birthday: " + form.format(birthday));
        System.out.println("Age: " + getAge(birthday));
        System.out.println("Email: " + email);
    }

    public String getFaculty() {
        return this.faculty;
    }

    public String getSpecialisation() {
        return this.specialisation;
    }

    public String getPosition() {
        return this.position;
    }

    public String getProfessor() {
        return surname + " " + name + " " + patronymic;
    }
}