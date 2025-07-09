public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public String getUpperCaseName() {
        return name.toUpperCase();
    }

    public int getAgeInMonths() {
        return age * 12;
    }
}
