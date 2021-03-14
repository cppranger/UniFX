package sample.uni;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Attestation {
    String subject;
    String eduSpec;
    Date date;
    Professor professor;

    public Attestation(String subject, String eduSpec, String date, Professor professor) {
        this.subject = subject;
        this.eduSpec = eduSpec;
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.professor = professor;
    }

    public void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getAttestation() {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String result = "";
        result += "Предмет: " + subject + ".\n";
        result += "Преподаваьель: " + professor.getProfessor() + ".\n";
        result += "Дата: " + form.format(date) + ".\n";
        return result;
    }

    public String getSubject(){
        return subject;
    }
}
