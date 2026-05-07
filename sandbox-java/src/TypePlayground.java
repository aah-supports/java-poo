import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TypePlayground {
    private TypePlayground() {
    }

    public static void run() {
        primitiveTypes();
        referenceTypes();
        conversions();
        collections();
    }

    private static void primitiveTypes() {
        int age = 21;
        long bigNumber = 2_500_000_000L;
        double average = 14.75;
        char grade = 'A';
        boolean valid = true;

        System.out.println("Primitives:");
        System.out.println("age=" + age + ", bigNumber=" + bigNumber);
        System.out.println("average=" + average + ", grade=" + grade + ", valid=" + valid);
    }

    private static void referenceTypes() {
        String name = "Alice";
        int[] values = {1, 2, 3};

        System.out.println("References:");
        System.out.println("name=" + name + ", valuesLength=" + values.length);
    }

    private static void conversions() {
        int sourceInt = 42;
        long asLong = sourceInt;
        double asDouble = asLong;

        double sourceDouble = 9.8;
        int truncated = (int) sourceDouble;

        Integer boxed = sourceInt;
        int unboxed = boxed;

        System.out.println("Conversions:");
        System.out.println("int->long->double: " + sourceInt + " -> " + asLong + " -> " + asDouble);
        System.out.println("double->int cast: " + sourceDouble + " -> " + truncated);
        System.out.println("boxing/unboxing: " + boxed + " -> " + unboxed);
    }

    private static void collections() {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 15);
        scores.put("Bob", 12);

        System.out.println("Collections:");
        System.out.println("names=" + names);
        System.out.println("scores=" + scores);
    }
}
