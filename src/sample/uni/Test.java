package sample.uni;

import java.text.SimpleDateFormat;

public class Test extends Attestation{

    public enum Marks {passed("Зачет"), not_passed("Незачет");

        private String textmark;

        Marks(String textmark) {
            this.textmark = textmark;
        }

        public String getTextmark() {
            return textmark;
        }

        public Exam.Marks getMark(String textmark){
            for (Exam.Marks mark : Exam.Marks.values()) {
                if (textmark.equals(mark.getTextmark())){
                    return mark;
                }
            }
            return null;
        }

    }

    public Test(String subject, String eduSpec, String date, Professor professor) {
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
        result += "Тип: зачет";
        return result;
    }
}
