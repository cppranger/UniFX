package sample.uni;

import java.text.SimpleDateFormat;

public class Exam extends Attestation {

    public enum Marks {two("Два"), three("Три"), four("Четыре"), five("Пять");

        private String textmark;
        Marks(String textmark) {
            this.textmark = textmark;
        }

        public String getTextmark() {
            return textmark;
        }

        public Marks getMark(String textmark){
            for (Marks mark : Marks.values()) {
                if (textmark.equals(mark.getTextmark())){
                    return mark;
                }
            }
            return null;
        }
    }

    public Exam(String subject, String eduSpec, String date, Professor professor) {
        super(subject, eduSpec, date, professor);
    }

    public void setMark(Student student, Marks mark) {
        return;
    }

    @Override
    public String getAttestation() {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String result = "";
        result += "Предмет: " + subject + ".\n";
        result += "Преподаваьель: " + professor.getProfessor() + ".\n";
        result += "Дата: " + form.format(date) + ".\n";
        result += "Тип: экзамен";
        return result;
    }
}
