package com.bvanseg.just.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PredicateGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: PredicateGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0], "com/bvanseg/just/functional/function/predicate");
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Could not create output directory: " + outputDir);
        }

        for (int n = 2; n <= 16; n++) {
            var sb = new StringBuilder();
            generateHeader(sb, n);
            generateTestMethod(sb, n);
            generateAndMethod(sb, n);
            generateOrMethod(sb, n);
            generateNegateMethod(sb, n);
            generateToFunctionMethod(sb, n);
            generateCurriedMethod(sb, n);
            generateLiftMethod(sb, n);
            generateAlwaysMethods(sb, n);
            generateNotMethod(sb, n);
            generateNamedMethod(sb, n);
            sb.append("}\n");

            File file = new File(outputDir, "Predicate" + n + ".java");
            try (var writer = new FileWriter(file)) {
                writer.write(sb.toString());
            }
        }

        System.out.println("Generated Predicate3 to Predicate16 in: " + outputDir.getAbsolutePath());
    }

    private static void generateHeader(StringBuilder sb, int n) {
        sb.append("package com.bvanseg.just.functional.function.predicate;\n\n");
        sb.append("import com.bvanseg.just.functional.function.Function;\n");

        if (n > 1) {
            sb.append("import com.bvanseg.just.functional.function.Function").append(n).append(";\n");
        }

        sb.append("\n");

        sb.append("@FunctionalInterface\n");
        sb.append("public interface Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> {\n\n");
    }

    private static void generateTestMethod(StringBuilder sb, int n) {
        sb.append("    boolean test(");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(" a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n\n");
    }

    private static void generateAndMethod(StringBuilder sb, int n) {
        sb.append("    default Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> and(Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("? super A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> other) {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> this.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") && other.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n    }\n\n");
    }

    private static void generateOrMethod(StringBuilder sb, int n) {
        sb.append("    default Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> or(Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("? super A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> other) {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> this.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") || other.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n    }\n\n");
    }

    private static void generateNegateMethod(StringBuilder sb, int n) {
        sb.append("    default Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> negate() {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> !this.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n    }\n\n");
    }

    private static void generateToFunctionMethod(StringBuilder sb, int n) {
        sb.append("    default Function").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(", ");
        }
        sb.append("Boolean> toFunction() {\n");
        sb.append("        return this::test;\n");
        sb.append("    }\n\n");
    }

    private static void generateCurriedMethod(StringBuilder sb, int n) {
        sb.append("    default ");
        for (int i = 1; i <= n; i++) {
            sb.append("Function<A").append(i).append(", ");
        }
        sb.append("Boolean");
        for (int i = 0; i < n; i++) {
            sb.append(">");
        }
        sb.append(" curried() {\n");

        sb.append("        return ");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i).append(" -> ");
        }

        sb.append("this.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append(");\n");

        sb.append("    }\n\n");
    }

    private static void generateNamedMethod(StringBuilder sb, int n) {
        sb.append("\n    static <");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> named(String name, Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> delegate) {\n");
        sb.append("        return new Predicate").append(n).append("<>() {\n");
        sb.append("            @Override\n");
        sb.append("            public boolean test(");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(" a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") {\n");
        sb.append("                return delegate.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n");
        sb.append("            }\n\n");
        sb.append("            @Override\n");
        sb.append("            public String toString() {\n");
        sb.append("                return name;\n");
        sb.append("            }\n");
        sb.append("        };\n");
        sb.append("    }\n");
    }

    private static void generateLiftMethod(StringBuilder sb, int n) {
        if (n <= 1)
            return;

        sb.append("    static <");
        for (int i = 1; i <= n - 1; i++) {
            sb.append("A").append(i).append(", ");
        }
        sb.append("A").append(n).append("> Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> lift(Predicate").append(n - 1).append("<");
        for (int i = 1; i <= n - 1; i++) {
            sb.append("A").append(i);
            if (i < n - 1)
                sb.append(", ");
        }
        sb.append("> predicate) {\n");

        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            if (i == n) {
                sb.append("_");
            } else {
                sb.append("a").append(i);
            }

            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> predicate.test(");
        for (int i = 1; i <= n - 1; i++) {
            sb.append("a").append(i);
            if (i < n - 1)
                sb.append(", ");
        }
        sb.append(");\n    }\n\n");
    }

    private static void generateAlwaysMethods(StringBuilder sb, int n) {
        // Always TRUE
        sb.append("    static <");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> alwaysTrue() {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("_");
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> true;\n");
        sb.append("    }\n");

        // Always FALSE
        sb.append("\n    static <");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> alwaysFalse() {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("_");
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> false;\n");
        sb.append("    }\n");
    }

    private static void generateNotMethod(StringBuilder sb, int n) {
        sb.append("\n    static <");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> not(Predicate").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append("> predicate) {\n");

        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> !predicate.test(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(");\n");

        sb.append("    }\n");
    }

}
