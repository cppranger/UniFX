package sample.uni;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AcademicPlan {

    Group group;
    Map<String,String> plan = new HashMap<>();

    public AcademicPlan(Group group) {
        this.group = group;
        switch (group.getEduSpec()) {
            case "Лингвистика":
                this.plan = linguisticPlan;
                break;
            case "Математика":
                this.plan = mathsPlan;
                break;
            case "Педагогика":
                this.plan = pedagogicsPlan;
                break;
        }
    }

    public void printPlan() {
        Iterator<Map.Entry<String, String>> iterator = plan.entrySet().iterator();
        for (int i = 0; i < plan.size(); ++i) {
            while (iterator.hasNext())
            {
                Map.Entry<String, String> pair = iterator.next();
                String key = pair.getKey();
                String value = pair.getValue();
                System.out.println(key + ":" + value);
            }
        }
    }

    public Map <String,String> getPlan() {
        return this.plan;
    }

    static public ObservableList<String> setPlan(String eduSpec) {
        ObservableList<String> eduSubjects = FXCollections.observableArrayList();
        switch (eduSpec) {
            case "Лингвистика":
                for (Map.Entry<String, String> entry : linguisticPlan.entrySet()) {
                    eduSubjects.add(entry.getKey());
                }
                break;
            case "Математика":
                for (Map.Entry<String, String> entry : mathsPlan.entrySet()) {
                    eduSubjects.add(entry.getKey());
                }
                break;
            case "Педагогика":
                for (Map.Entry<String, String> entry : pedagogicsPlan.entrySet()) {
                    eduSubjects.add(entry.getKey());
                }
                break;
        }
        return eduSubjects;
    }

    public static Map<String,String> linguisticPlan = new HashMap<>();

    public static Map<String,String> mathsPlan = new HashMap<>();

    public static Map<String,String> pedagogicsPlan = new HashMap<>();

    public static ArrayList<Map<String,String>> plans = new ArrayList<Map<String,String>>(){{
        add(linguisticPlan);
        add(mathsPlan);
        add(pedagogicsPlan);
    }};
}