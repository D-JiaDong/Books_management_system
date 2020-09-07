public class Person {
    private String PersonName;
    private String PersonSex;
    private int  PersonAge;
    private String Persontel;


    public Person() {
    }

    public Person(String personName, String personSex, int personAge, String persontel) {
        PersonName = personName;
        PersonSex = personSex;
        PersonAge = personAge;
        Persontel = persontel;
    }

    public String getPersontel() {
        return Persontel;
    }

    public void setPersontel(String persontel) {
        Persontel = persontel;
    }
    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonSex() {
        return PersonSex;
    }

    public void setPersonSex(String personSex) {
        PersonSex = personSex;
    }

    public int getPersonAge() {
        return PersonAge;
    }

    public void setPersonAge(int personAge) {
        PersonAge = personAge;
    }
}
