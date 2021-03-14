package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.uni.*;

import java.text.ParseException;
import java.util.Map;

public class Controller {

    public static ObservableList<Exam> ExamList = FXCollections.observableArrayList();
    public static ObservableList<Test> TestList = FXCollections.observableArrayList();

    ObservableList<Student> StudentList = FXCollections.observableArrayList();
    ObservableList<Professor> ProfessorList = FXCollections.observableArrayList();

    ObservableList<String> subjects = FXCollections.observableArrayList("Лингвистика", "Математика", "Педагогика");
    ObservableList<String> attTypes = FXCollections.observableArrayList("Экзамен", "Зачет");
    ObservableList<String> ProffChoise = FXCollections.observableArrayList();


    @FXML
    private ChoiceBox<String> chooseGroup;

    @FXML
    private ChoiceBox<String> chooseGroup1;

    @FXML
    private ChoiceBox<String> chooseStudent;

    @FXML
    private ChoiceBox<String> chooseStudent1;

    @FXML
    private ChoiceBox<String> chooseSubject;

    @FXML
    private TextFlow attestationInfo;

    @FXML
    private TextFlow attestationBook;

    @FXML
    private ChoiceBox<String> chooseMark;

    @FXML
    private Button buttonConfirm;

    @FXML
    private Button buttonStudentsDelete;

    @FXML
    private Button buttonStudentsAdd;


    @FXML
    private TableView<Student> tableStudents;

    @FXML
    TableColumn<Student, String> SurnameColumn = new TableColumn<Student, String>("Фамилия");

    @FXML
    TableColumn<Student, String> NameColumn = new TableColumn<Student, String>("Имя");

    @FXML
    TableColumn<Student, String> PatronymicColumn = new TableColumn<Student, String>("Отчество");

    @FXML
    TableColumn<Student, String> FacultyColumn = new TableColumn<Student, String>("Факультет");

    @FXML
    TableColumn<Student, String> GroupColumn = new TableColumn<Student, String>("Группа");

    @FXML
    TableColumn<Student, String> EmailColumn = new TableColumn<Student, String>("Эл.почта");

    @FXML
    private Tab textProfessors;

    @FXML
    private Button buttonProfessorsDelete;

    @FXML
    private Button buttonProfessorsAdd;

    @FXML
    private TableView<Professor> tableProfessors;

    @FXML
    TableColumn<Professor, String> SurnameProfessorColumn = new TableColumn<Professor, String>("Фамилия");

    @FXML
    TableColumn<Professor, String> NameProfessorColumn = new TableColumn<Professor, String>("Имя");

    @FXML
    TableColumn<Professor, String> PatronymicProfessorColumn = new TableColumn<Professor, String>("Отчество");

    @FXML
    TableColumn<Professor, String> FacultyProfessorColumn = new TableColumn<Professor, String>("Факультет");

    @FXML
    TableColumn<Professor, String> PositionProfessorColumn = new TableColumn<Professor, String>("Должность");

    @FXML
    TableColumn<Professor, String> SpecialisationProfessorColumn = new TableColumn<Professor, String>("Предмет");

    @FXML
    TableColumn<Professor, String> EmailProfessorColumn = new TableColumn<Professor, String>("Эл.почта");

    @FXML
    private Tab textPlan;

    @FXML
    private Button buttonPlanDelete;

    @FXML
    private Button buttonPlanAdd;


    @FXML
    private TextFlow edPlan;

    // Добавление студента

    @FXML
    private Button studSave;

    @FXML
    private TextField studName;

    @FXML
    private TextField studSurname;

    @FXML
    private TextField studPatronymic;

    @FXML
    private TextField studBirthday;

    @FXML
    private TextField studEmail;

    @FXML
    private TextField studFackulty;

    @FXML
    private TextField studSpecialty;

    @FXML
    private TextField studSubject;

    // добавление профессора

    @FXML
    private Button proffSave;

    @FXML
    private TextField proffName;

    @FXML
    private TextField proffSurname;

    @FXML
    private TextField proffPatronymic;

    @FXML
    private TextField proffBirthday;

    @FXML
    private TextField proffEmail;

    @FXML
    private TextField proffFackulty;

    @FXML
    private TextField proffSpecialty;

    @FXML
    private TextField proffSubject;

    // Добавление предмета

    @FXML
    private TextField attName;

    @FXML
    private ChoiceBox<String> attestationType;

    @FXML
    private ChoiceBox<String> proff;

    @FXML
    private ChoiceBox<String> subj;

    @FXML
    private TextField attDate;

    @FXML
    private Button attSave;


    @FXML
    private void initialize() throws ParseException {
        final Student[] currentStudent = {null};
        final Student[] currentStudent1 = {null};
        final Student[] currentTableStudent = {null};
        final Professor[] currentTableProfessor = {null};

        initProfessorData();
        initTestData();
        initExamData();
        initStudentData();
        initProffChoiseBoxData();
        initAcademicPlan();

        getGeneralChoiseboxes();
        getAttestationChoiseboxes();
        getNewAttestationChoiseboxes();
        setDefaultText();

        refreshPlan();

        chooseGroup.setOnAction(actionEvent -> {
            currentStudent[0] = actionChoseGroup();
        });

        chooseGroup1.setOnAction(actionEvent -> {
            currentStudent1[0] = actionChoseGroup1();
        });

        chooseSubject.setOnAction(actionEvent -> {
            actionChoseSubject();
        });

        chooseStudent.setOnAction(actionEvent -> {
            actionChoseStudent(currentStudent[0]);
        });

        chooseStudent1.setOnAction(actionEvent -> {
            actionChoseStudent(currentStudent1[0]);
        });


        buttonConfirm.setOnAction(actionEvent -> {
            actionConfirm(currentStudent1[0]);
        });

        buttonStudentsDelete.setOnAction(actionEvent -> {
            actionStudentDelete(currentTableStudent[0]);
        });

        buttonProfessorsDelete.setOnAction(actionEvent -> {
            actionProfessorDelete(currentTableProfessor[0]);
        });


        studSave.setOnAction(actionEvent -> {
            try {
                actionAddNewStudent();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        proffSave.setOnAction(actionEvent -> {
            try {
                actionAddNewProfessor();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        attSave.setOnAction(actionEvent -> {
            actionAddNewSubject();
        });

        // Таблица студентов

        setStudentsTable();

        // Таблица преподавателей

        setProfessorsTable();

        TableView.TableViewSelectionModel<Professor> selectedProfessor = tableProfessors.getSelectionModel();
        selectedProfessor.selectedItemProperty().addListener(new ChangeListener<Professor>() {
            @Override
            public void changed(ObservableValue<? extends Professor> observableValue, Professor professor, Professor t1) {
                if (t1 != null) {
                    currentTableProfessor[0] = t1;
                }
            }
        });

        TableView.TableViewSelectionModel<Student> selectedStudent = tableStudents.getSelectionModel();
        selectedStudent.selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observableValue, Student student, Student t1) {
                if (t1 != null) {
                    currentTableStudent[0] = t1;
                }
            }
        });
    }


    public Controller() throws ParseException {

    }

    private void initStudentData() throws ParseException {
         StudentList.add(new Student("Антон", "Заболотский", "Игоревич", "19/06/1995",
                "stud099839@vyatsu.ru", "ФЛ", "Лингвистика", "ЛВб-4202",
                2017));
         StudentList.add (new Student("Лора", "Зыкина", "Романовна", "26/02/1999",
                 "stud099254@vyatsu.ru", "ФЛ", "Педагогика", "ПОДб-4302",
                 2017));
        StudentList.add (new Student("Евгений", "Ломов", "Васильевич", "01/11/1996",
                "stud001256@vyatsu.ru", "ИМФ", "Математика", "МАП-2102",
                2017));
    }

    private void initProfessorData() throws ParseException {
        ProfessorList.add(new Professor("Василий", "Самьянов", "Эдуардович", "01/02/1971",
                "prof001372@vyatsu.ru", "ФЛ", "Декан", "Германистика"));
        ProfessorList.add(new Professor("Анастасия", "Лукина", "Аркадьевна", "30/08/1989",
                "prof001487@vyatsu.ru", "ИМФ", "Доцент", "Высшая математика"));
        ProfessorList.add(new Professor("Андрей", "Березин", "Петрович", "14/11/1984",
                "prof000011@vyatsu.ru", "Психология", "Старший преподаватель", "Возрастная психология"));
    }

    private void initProffChoiseBoxData() {
        for (Professor professor : ProfessorList) {
            ProffChoise.add(professor.getFIO());
        }
    }

    private String initPlanData() {
        StringBuilder plan = new StringBuilder();
        for (int i = 0; i < AcademicPlan.plans.size(); i++) {
            for (Map.Entry<String, String> entry : AcademicPlan.plans.get(i).entrySet()) {
                String subject = entry.getKey() + " - " + entry.getValue();
                plan.append(subject);
                plan.append("\n");
            }
            plan.append("\n");
        }
        return plan.toString();
    }

    private void initExamData() {
        ExamList.add(new Exam("Германистика", "Лингвистика", "17/05/2021", ProfessorList.get(0)));
        ExamList.add(new Exam("Лингвокультурология", "Лингвистика", "21/05/2021", ProfessorList.get(0)));
        ExamList.add(new Exam("Графовые алгоритмы", "Математика", "17/05/2021", ProfessorList.get(1)));
        ExamList.add(new Exam("Дифференциальные уравнения", "Математика", "21/05/2021", ProfessorList.get(1)));
        ExamList.add(new Exam("Теоритическая грамматика", "Педагогика", "18/05/2021", ProfessorList.get(0)));
        ExamList.add(new Exam("Педагогика", "Педагогика", "22/05/2021", ProfessorList.get(0)));
    }

    private void initTestData() {
        TestList.add(new Test("Фонетика", "Лингвистика", "11/05/2021", ProfessorList.get(0)));
        TestList.add(new Test("Устный перевод", "Лингвистика", "14/05/2021", ProfessorList.get(0)));
        TestList.add(new Test("Численные методы", "Математика", "11/05/2021", ProfessorList.get(1)));
        TestList.add(new Test("Теория вероятности", "Математика", "214/05/2021", ProfessorList.get(1)));
        TestList.add(new Test("Стилистика", "Педагогика", "11/05/2021", ProfessorList.get(0)));
        TestList.add(new Test("Возрастная психология", "Педагогика", "14/05/2021", ProfessorList.get(0)));
    }

    private Exam findExam(String subject) {
        for (Exam exam : ExamList) {
            if (subject.equals(exam.getSubject())) {
                return exam;
            }
        }
        return null;
    }

    private Test findTest(String subject) {
        for (Test test : TestList) {
            if (subject.equals(test.getSubject())) {
                return test;
            }
        }
        return null;
    }


    private void getGeneralChoiseboxes() {
        chooseGroup.setItems(subjects);
    }

    private void getAttestationChoiseboxes() {
        chooseGroup1.setItems(subjects);
    }

    private void getNewAttestationChoiseboxes() {
        attestationType.setItems(attTypes);
        proff.setItems(ProffChoise);
        subj.setItems(subjects);
    }

    private ObservableList<Student> getStudents(String eduSpec) {
        ObservableList<Student> specStudents = FXCollections.observableArrayList();
        for (Student student : StudentList) {
            if (student.getEduSpec().equals(eduSpec)) {
                specStudents.add(student);
            }
        }
        return specStudents;
    }

    private ObservableList<String> getExamMarks() {
        ObservableList<String> marks = FXCollections.observableArrayList();
        for (Exam.Marks mark : Exam.Marks.values()) {
            marks.add(mark.getTextmark());
        }
        return marks;
    }

    private ObservableList<String> getTestMarks() {
        ObservableList<String> marks = FXCollections.observableArrayList();
        for (Test.Marks mark : Test.Marks.values()) {
            marks.add(mark.getTextmark());
        }
        return marks;
    }

    private Student actionChoseGroup() {
        attestationBook.getChildren().clear();
        ObservableList<String> studentsNames = FXCollections.observableArrayList();
        ObservableList<Student> specStudents = getStudents(chooseGroup.getValue());
        Student current = null;
        for (Student student : specStudents){
            current = student;
            studentsNames.add(student.getFIO());
        }
        chooseStudent.setItems(studentsNames);
        return current;
    }

    private Student actionChoseGroup1() {
        chooseSubject.setItems(AcademicPlan.setPlan(chooseGroup1.getValue()));
        ObservableList<String> studentsNames = FXCollections.observableArrayList();
        ObservableList<Student> specStudents = getStudents(chooseGroup1.getValue());
        Student current = null;
        for (Student student : specStudents){
            current = student;
            studentsNames.add(student.getFIO());
        }
        chooseStudent1.setItems(studentsNames);
        return current;
    }

    private void actionChoseSubject(){
        attestationInfo.getChildren().clear();
        String info = "";
        String subject = chooseSubject.getValue();
        if (findExam(subject) != null) {
            info = findExam(subject).getAttestation();
            chooseMark.setItems(getExamMarks());
        }
        else if (findTest(subject) != null) {
            info = findTest(subject).getAttestation();
            chooseMark.setItems(getTestMarks());
        }
        else {
            info = "null";
        }
        attestationInfo.getChildren().add(new Text(info));
    }

    private void actionChoseStudent(Student student) {
        attestationBook.getChildren().clear();
        if (chooseStudent != null) {
            String attestation = student.getAttestationBook();
            attestationBook.getChildren().add(new Text(attestation));
        }
    }

    private void actionConfirm(Student student) {
        if (chooseMark.getValue() != null && chooseStudent1 != null && chooseGroup1 != null) {
            String cur_mark = chooseMark.getValue();
            String cur_subject = chooseSubject.getValue();
            if (cur_mark.equals("Зачет") || cur_mark.equals("Незачет")) {
                for (Test.Marks mark : Test.Marks.values()) {
                    if (mark.getTextmark().equals(cur_mark)) {
                        student.setTestMark(cur_subject, mark);
                    }
                }
            } else {
                for (Exam.Marks mark : Exam.Marks.values()) {
                    if (mark.getTextmark().equals(cur_mark)) {
                        student.setExamMark(cur_subject, mark);
                    }
                }
            }
            attestationBook.getChildren().clear();
            String attestation = student.getAttestationBook();
            attestationBook.getChildren().add(new Text(attestation));
        }
    }

    private void actionStudentDelete(Student student) {
        if (student != null) {
            for (Student stud : StudentList) {
                if (stud.getEmail().equals(student.email)) {
                    StudentList.remove(stud);
                }
            }
            setStudentsTable();
        }
    }

    private void actionProfessorDelete(Professor professor) {
        if (professor != null) {
            for (Professor prof : ProfessorList) {
                if (prof.getEmail().equals(professor.email)) {
                    ProfessorList.remove(prof);
                }
            }
            setProfessorsTable();
        }
    }

    private void setStudentsTable() {
        tableStudents.getColumns().clear();
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PatronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        FacultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        GroupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableStudents.setItems(StudentList);
        tableStudents.getColumns().addAll(SurnameColumn, NameColumn, PatronymicColumn, FacultyColumn, GroupColumn, EmailColumn);
    }

    private void setProfessorsTable() {
        tableProfessors.getColumns().clear();
        SurnameProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        NameProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PatronymicProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        FacultyProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        PositionProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        SpecialisationProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("specialisation"));
        EmailProfessorColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableProfessors.setItems(ProfessorList);
        tableProfessors.getColumns().addAll(SurnameProfessorColumn, NameProfessorColumn, PatronymicProfessorColumn, FacultyProfessorColumn, PositionProfessorColumn, SpecialisationProfessorColumn, EmailProfessorColumn);
    }


    private void actionAddNewStudent() throws ParseException {
        if (studName != null && studSurname != null && studPatronymic != null && studBirthday != null
                && studEmail != null && studFackulty != null && studSpecialty != null && studSubject != null) {
            StudentList.add(new Student(studName.getText(), studSurname.getText(), studPatronymic.getText(), studBirthday.getText(),
                    studEmail.getText(), studFackulty.getText(), studSpecialty.getText(), studSubject.getText(),
                    2017));
            setStudentsTable();
        }
    }

    private void actionAddNewProfessor() throws ParseException {
        if (proffName != null && proffSurname != null && proffPatronymic != null && proffBirthday != null
        && proffEmail != null && proffFackulty != null && proffSpecialty != null && proffSubject != null) {
            ProfessorList.add(new Professor(proffName.getText(), proffSurname.getText(), proffPatronymic.getText(), proffBirthday.getText(),
                    proffEmail.getText(), proffFackulty.getText(), proffSpecialty.getText(), proffSubject.getText()));
            setProfessorsTable();
        }
    }

    private void actionAddNewSubject() {
        if (attestationType.getValue().equals("Зачет")) {
            TestList.add(new Test(attName.getText(), subj.getValue(), attDate.getText(), findProff(proff.getValue())));
        }
        else {
            ExamList.add(new Exam(attName.getText(), subj.getValue(), attDate.getText(), findProff(proff.getValue())));
        }
        sendToAcademicPlan();
        refreshPlan();
    }

    private void sendToAcademicPlan() {
        switch (subj.getValue()) {
            case "Лингвистика" -> AcademicPlan.linguisticPlan.put(attName.getText(), attestationType.getValue());
            case "Математика" -> AcademicPlan.mathsPlan.put(attName.getText(), attestationType.getValue());
            case "Педагогика" -> AcademicPlan.pedagogicsPlan.put(attName.getText(), attestationType.getValue());
        }
    }

    private void setDefaultText(){
        studName.setText("Андрей");
        studSurname.setText("Иванов");
        studPatronymic.setText("Васильевич");
        studBirthday.setText("01/01/2001");
        studEmail.setText("stud019234@vyatsu.ru");
        studFackulty.setText("ФЛ");
        studSpecialty.setText("Лингвистика");
        studSubject.setText("ЛВб-1202");

        proffName.setText("Афанасий");
        proffSurname.setText("Фоминых");
        proffPatronymic.setText("Эдуардович");
        proffBirthday.setText("05/12/31");
        proffEmail.setText("proff910213@vyatsu.ru");
        proffFackulty.setText("ИМФ");
        proffSpecialty.setText("Декан");
        proffSubject.setText("Теория графов");

        attName.setText("Древняя философия");
        attDate.setText("01/03/2021");
    }

    private Professor findProff(String proffFio) {
        for (Professor proff : ProfessorList) {
            if (proff.getFIO().equals(proffFio)) {
                return proff;
            }
        }
        return null;
    }

    private void refreshPlan(){
        edPlan.getChildren().clear();
        Text ed_text = new Text(initPlanData());
        ed_text.setStyle("-fx-font-size: 14px;");
        edPlan.getChildren().add(ed_text);
    }

    private void initAcademicPlan(){
        AcademicPlan.linguisticPlan.put("Лингвокультурология", "Экзамен");
        AcademicPlan.linguisticPlan.put("Германистика", "Экзамен");
        AcademicPlan.linguisticPlan.put("Устный перевод", "Зачет");
        AcademicPlan.linguisticPlan.put("Фонетика", "Тест");

        AcademicPlan.mathsPlan.put("Дифференциальные уравнения", "Экзамен");
        AcademicPlan.mathsPlan.put("Графовые алгоритмы", "Экзамен");
        AcademicPlan.mathsPlan.put("Численные методы", "Зачет");
        AcademicPlan.mathsPlan.put("Теория вероятности", "Зачет");

        AcademicPlan.pedagogicsPlan.put("Теоритическая грамматика", "Экзамен");
        AcademicPlan.pedagogicsPlan.put("Педагогика", "Экзамен");
        AcademicPlan.pedagogicsPlan.put("Возрастная психология", "Зачет");
        AcademicPlan.pedagogicsPlan.put("Стилистика", "Зачет");
    }
}
