package sample.uni;

import sample.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Student extends Human {

    public String faculty;
    String eduSpec;
    public String group;
    int startYear;
    Map<String, Exam.Marks> AttestationBookExams;
    Map<String, Test.Marks> AttestationBookTests;

    public Student(String name, String surname, String patronymic, String birthday, String email, String faculty, String eduSpec, String group, int startYear) throws ParseException {
        super(name, surname, patronymic, birthday, email);
        this.faculty = faculty;
        this.eduSpec = eduSpec;
        this.group = group;
        this.startYear = startYear;
        this.AttestationBookExams = initExams();
        this.AttestationBookTests = initTests();
    }

    public int getAcademicYear() {
        return Calendar.getInstance().get(Calendar.YEAR) - startYear;
    }

    @Override
    public void printHuman(){
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("FIO: " + surname + " " + name + " " + patronymic);
        System.out.println("Faculty: " + faculty + ". Specialisation: " + eduSpec);
        System.out.println("Group: " + group);
        System.out.println("Birthday: " + form.format(birthday));
        System.out.println("Age: " + getAge(birthday));
        System.out.println("Email: " + email);
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFaculty(){
        return this.faculty;
    }

    public String getGroup(){
        return this.group;
    }

    public String getEduSpec() {
        return eduSpec;
    }

    public Map<String, Exam.Marks> initExams(){
        Map<String, Exam.Marks> Exams = new HashMap<>();
        for (Exam exam : Controller.ExamList) {
            if (exam.eduSpec.equals(eduSpec)) {
                Exams.put(exam.subject, null);
            }
        }
        
        return Exams;
    }

    public Map<String, Test.Marks> initTests(){
        Map<String, Test.Marks> Tests = new HashMap<>();
        for (Test test : Controller.TestList) {
            if (test.eduSpec.equals(eduSpec)) {
                Tests.put(test.subject, null);
            }
        }
        return Tests;
    }

    public String getAttestationBook(){
        String attestation = "";
        for (Map.Entry<String, Exam.Marks> entry : AttestationBookExams.entrySet()) {
            if (entry.getValue() != null) {
                attestation += "Экзамен: " + entry.getKey() + ", оценка: " + entry.getValue().getTextmark() + ".\n";
            }
            else {
                attestation += "Экзамен: " + entry.getKey() + ", оценка еще не выставлена!\n";
            }
        }

        for (Map.Entry<String, Test.Marks> entry : AttestationBookTests.entrySet()) {
            if (entry.getValue() != null) {
                attestation += "Зачет: " + entry.getKey() + ", оценка: " + entry.getValue().getTextmark() + ".\n";
            }
            else {
                attestation += "Зачет: " + entry.getKey() + ", оценка еще не выставлена!\n";
            }
        }
        return attestation;
    }

    public void setExamMark(String subject, Exam.Marks mark) {
        for (Map.Entry<String, Exam.Marks> entry : AttestationBookExams.entrySet()) {
            if (subject.equals(entry.getKey())) {
                entry.setValue(mark);
            }
        }
    }

    public void setTestMark(String subject, Test.Marks mark) {
        for (Map.Entry<String, Test.Marks> entry : AttestationBookTests.entrySet()) {
            if (subject.equals(entry.getKey())) {
                entry.setValue(mark);
            }
        }
    }
}
