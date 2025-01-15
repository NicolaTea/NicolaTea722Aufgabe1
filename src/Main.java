import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
       System.out.println("Enter initial: ");
       char initial=sc.nextLine().charAt(0);
       Set<Log> log=sortedByinitial(initial);
       for(Log l: log){
           System.out.println(l);
       }

       System.out.println("Enter Symptom:");
       String s=sc.nextLine();
       List<Log> log1=sortByFieber(s);
       System.out.println(log1);



    }

    private static Set<Log> sortedByinitial(char intial) throws IOException {
        List<Log>logentries = parse("fallakten.json");
        return logentries.stream().filter(log -> log.getPatient().charAt(0)==intial)
                .collect(Collectors.toSet());
    }

    private static List<Log> sortByFieber(String Sympton) throws IOException {
        List<Log>logentries = parse("fallakten.json");
        return logentries.stream().filter(log -> log.getSymptom().equalsIgnoreCase(Sympton))
                .sorted(Comparator.comparing(Log::getDatum).reversed()).
                collect(Collectors.toList());
    }

    private static List<Log> parse(String filePath) throws IOException {
        List<Log> logEntries = new ArrayList<>();
        Path file = Path.of(filePath);

        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line.trim());
            }
        }

        // Remove the brackets at the start and end
        String content = json.toString().replace("[", "").replace("]", "").trim();
        String[] entries = content.split("},\\s*\\{");

        for (String entry : entries) {
            entry = entry.replace("{", "").replace("}", "").trim();
            String[] fields = entry.split(",\\s*");
            int id = 0;
            String Patient=null,Sympton=null,Diagnose=null,Hospital=null;
            LocalDate Datum=null;

            for (String field : fields) {
                String[] keyValue = field.split(":");
                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();

                switch (key) {
                    case "id":
                        id = Integer.parseInt(value);
                        break;
                    case "Patient":
                        Patient = value;
                        break;
                    case "Symptom":
                       Sympton=value;
                        break;
                    case "Diagnose":
                      Diagnose=value;
                        break;
                    case "Datum":
                       Datum=LocalDate.parse(value, java.time.format.DateTimeFormatter.ofPattern("YYYY-MM-DD"));
                        break;
                    case "Krankenhaus":
                        Hospital=value;
                        break;
                }
            }
            logEntries.add(new Log(id, Patient, Sympton, Diagnose, Datum,Hospital));
        }
        return logEntries;
    }
}