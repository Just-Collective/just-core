package com.bvanseg.just.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FunctionGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: FunctionGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0], "com/bvanseg/just/functional/function");
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Could not create output directory: " + outputDir.getAbsolutePath());
        }

        for (int n = 1; n <= 16; n++) {
            var sb = new StringBuilder();
            generateHeader(sb, n);
            generateApplyMethod(sb, n);
            generateAndThenMethod(sb, n);
            generateMemoizeMethod(sb, n);
            generateCurriedMethod(sb, n);
            generateFromCurriedMethod(sb, n);
            sb.append("}\n");

            String filename = (n == 1) ? "Function.java" : "Function" + n + ".java";
            File file = new File(outputDir, filename);
            try (var writer = new FileWriter(file)) {
                writer.write(sb.toString());
            }
        }

        System.out.println("Generated Function to Function16 in: " + outputDir.getAbsolutePath());
    }

    private static void generateHeader(StringBuilder sb, int n) {
        sb.append("package com.bvanseg.just.functional.function;\n\n");

        sb.append("import com.bvanseg.just.functional.function.memo.Memo");

        if (n > 1) {
            sb.append(n);
        }

        sb.append(";\n");

        if (n == 2) {
            sb.append("import java.util.function.BiFunction;\n");
        }

        sb.append("\n@FunctionalInterface\n");

        if (n == 1) {
            sb.append("public interface Function<A1, R> extends java.util.function.Function<A1, R> {\n\n");
        } else if (n == 2) {
            sb.append("public interface Function2<A1, A2, R> extends BiFunction<A1, A2, R> {\n\n");
        } else {
            sb.append("public interface Function").append(n).append("<");
            for (int i = 1; i <= n; i++) {
                sb.append("A").append(i).append(", ");
            }
            sb.append("R> {\n\n");
        }
    }

    private static void generateApplyMethod(StringBuilder sb, int n) {
        if (n == 1 || n == 2) {
            sb.append("    @Override\n");
        }

        sb.append("    R apply(");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(" a").append(i);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append(");\n");
    }

    private static void generateAndThenMethod(StringBuilder sb, int n) {
        sb.append("\n    default <Z> ");
        if (n == 1) {
            sb.append("Function<A1, Z>");
        } else {
            sb.append("Function").append(n).append("<");
            for (int i = 1; i <= n; i++) {
                sb.append("A").append(i).append(", ");
            }
            sb.append("Z>");
        }

        sb.append(" andThen(Function<? super R, ? extends Z> after) {\n");
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append(") -> after.apply(this.apply(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("));\n");
        sb.append("    }\n");
    }

    private static void generateMemoizeMethod(StringBuilder sb, int n) {
        var typeName = n > 1 ? "Memo" + n : "Memo";

        sb.append("\n    default ").append(typeName).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(", ");
        }
        sb.append("R> memoize() {\n");
        sb.append("        return new ").append(typeName).append("<>(").append("this);\n");
        sb.append("    }\n");
    }

    private static void generateCurriedMethod(StringBuilder sb, int n) {
        if (n <= 1)
            return;

        sb.append("\n    default Function<A1, ");

        for (int i = 2; i < n; i++) {
            sb.append("Function<A").append(i).append(", ");
        }
        sb.append("Function<A").append(n).append(", R>");
        for (int i = 2; i < n; i++) {
            sb.append(">");
        }
        sb.append("> curried() {\n");

        // Begin body
        sb.append("        return ");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i).append(" -> ");
        }
        sb.append("this.apply(");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append(");\n");
        sb.append("    }\n");
    }

    private static void generateFromCurriedMethod(StringBuilder sb, int n) {
        if (n <= 1)
            return;

        sb.append("\n    static <");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(", ");
        }
        sb.append("R> Function").append(n).append("<");
        for (int i = 1; i <= n; i++) {
            sb.append("A").append(i).append(", ");
        }
        sb.append("R> fromCurried(");

        // curried parameter type
        sb.append("Function<");
        for (int i = 1; i < n; i++) {
            sb.append("A").append(i).append(", Function<");
        }
        sb.append("A").append(n).append(", R>");
        for (int i = 1; i < n; i++) {
            sb.append(">");
        }

        sb.append(" curried) {\n");

        // lambda body
        sb.append("        return (");
        for (int i = 1; i <= n; i++) {
            sb.append("a").append(i);
            if (i < n)
                sb.append(", ");
        }
        sb.append(") -> ");
        sb.append("curried");
        for (int i = 1; i <= n; i++) {
            sb.append(".apply(a").append(i).append(")");
        }
        sb.append(";\n    }\n");
    }

}
