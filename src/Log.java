import java.time.LocalDate;

public class Log {
    private int id;
    private String Patient;
    private String Symptom;
    private String Diagnose;
    private LocalDate Datum;
    private String Hospital;

    public Log(int id,String p,String s,String d,LocalDate da,String h){
        this.id=id;
        this.Patient=p;
        this.Symptom=s;
        this.Diagnose=d;
        this.Datum=da;
        this.Hospital=h;
    }

    public String getDiagnose() {
        return Diagnose;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDatum() {
        return Datum;
    }

    public String getHospital() {
        return Hospital;
    }

    public String getPatient() {
        return Patient;
    }


    public String getSymptom() {
        return Symptom;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", Patient='" + Patient + '\'' +
                ", Symptom='" + Symptom + '\'' +
                ", Diagnose='" + Diagnose + '\'' +
                ", Datum=" + Datum +
                ", Hospital='" + Hospital + '\'' +
                '}';
    }
}
