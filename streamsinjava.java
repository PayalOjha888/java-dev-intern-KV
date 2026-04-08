import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streampracticeinjava {
    public static void main(String[] args) {
        //Day 11 : 01/04/2026

        List<Students> students = Arrays.asList(
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Kanha", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Abhinav", "Finance", 57000, Arrays.asList("Accounting", "Excel")),
                new Students("Harsh", "Finance", 55000, Arrays.asList("Accounting", "Excel")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication"))
        );

        //1. Maan lijiye employees ki list Salary ke basis par sorted hai (Low to High).
        // Ek aisi list nikaalo jo tab tak employees le jab tak salary 60,000 se kam ho.
        // (Jaise hi pehla 60k wala mile, wahi ruk jao).

        List<Students> ans1 = students.stream().sorted((x, y) -> (int) (x.getSalary() - y.getSalary())).takeWhile(x -> x.getSalary() < 60000).toList();
        System.out.println(ans1);

        //2. Sorted list mein se wo saare employees hata do (skip karo)
        // jinki salary 50,000 se kam hai, aur baaki saari list return karo.

        List<Students> ans2 = students.stream().sorted((x, y) -> (int) (x.getSalary() - y.getSalary())).dropWhile(x -> x.getSalary() < 50000).toList();
        System.out.println(ans2);

        //3. Ek Map<String, Optional<Employee>> nikaalo jo bataye ki har
        // department mein sabse zyada salary kiski hai.

        Map<String, Optional<Students>> collect = students.stream().collect(Collectors.groupingBy(Students::getDept, Collectors.maxBy((x, y) -> (int) (x.getSalary() - y.getSalary()))));
        System.out.println(collect);

        //4. Puri company mein dusri (2nd) sabse zyada salary kaunsi hai?

        Students students1 = students.stream().sorted((x, y) -> (int) (y.getSalary() - x.getSalary())).toList().get(1);
        System.out.println(students1.getSalary());

        //5.Ek Map<String, String> banao jahan Key Dept Name ho
        // aur Value us dept ke saare employees ke Names hon, comma (,) se separated.

        Map<String, String> collect1 = students.stream().collect(Collectors.groupingBy(Students::getDept, Collectors.mapping(Students::getName, Collectors.joining(","))));
        System.out.println(collect1);

        //6. Maan lijiye ek List<String> rawData hai jisme kuch values null bhi ho sakti hain.
        // Stream.ofNullable ka concept use karke sirf non-null values ko uppercase mein print karo.

        List<String> rawNames = Arrays.asList(
                "Payal",
                null,
                "Kanha",
                "Sakshi",
                null,
                "Harsh",
                "  ", // Ye empty string hai, null nahi
                "Muskan"
        );

        List<String> list = rawNames.stream().filter(Objects::nonNull).filter(x -> !x.trim().isEmpty()).map(String::toUpperCase).toList();
        System.out.println(list);

        List<String> list1 = rawNames.stream().flatMap(Stream::ofNullable).filter(s -> !s.isBlank()).map(String::toUpperCase).toList();
        System.out.println(list1);
    }
}
