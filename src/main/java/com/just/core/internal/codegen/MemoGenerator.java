package com.just.core.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MemoGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: MemoGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0], "com/just/core/functional/function/memo");
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Could not create output directory: " + outputDir);
        }

        for (int n = 1; n <= 16; n++) {
            var sb = new StringBuilder();

            // --- Package and Imports ---
            sb.append("package com.bvanseg.just.functional.function.memo;\n\n");

            if (n == 1) {
                sb.append("import java.util.function.Function;\n");
                sb.append("import java.util.function.BiPredicate;\n\n");
            } else {
                sb.append("import com.bvanseg.just.functional.function.Function").append(n).append(";\n");
                sb.append("import java.util.function.BiPredicate;\n\n");
            }

            // --- Class declaration ---
            String className = (n == 1) ? "Memo" : "Memo" + n;
            String functionName = (n == 1) ? "Function" : "Function" + n;

            sb.append("public class ").append(className).append("<");
            for (int i = 1; i <= n; i++)
                sb.append("A").append(i).append(", ");
            sb.append("R> implements ").append(functionName).append("<");
            for (int i = 1; i <= n; i++)
                sb.append("A").append(i).append(", ");
            sb.append("R> {\n\n");

            // --- Field declarations ---
            for (int i = 1; i <= n; i++) {
                sb.append("    private A").append(i).append(" a").append(i).append("Ref;\n");
            }
            sb.append("    private R cachedResult;\n\n");

            sb.append("    private final ").append(functionName).append("<");
            for (int i = 1; i <= n; i++)
                sb.append("A").append(i).append(", ");
            sb.append("R> fn;\n");

            for (int i = 1; i <= n; i++) {
                sb.append("    private final BiPredicate<A")
                    .append(i)
                    .append(", A")
                    .append(i)
                    .append("> eq")
                    .append(i)
                    .append(";\n");
            }

            sb.append("\n");

            // --- Constructor (default eq) ---
            sb.append("    public ").append(className).append("(").append(functionName).append("<");
            for (int i = 1; i <= n; i++)
                sb.append("A").append(i).append(", ");
            sb.append("R> fn) {\n");
            sb.append("        this(fn,\n");
            for (int i = 1; i <= n; i++) {
                sb.append("            (oldRef, newRef) -> newRef == oldRef");
                if (i < n)
                    sb.append(",\n");
            }
            sb.append(");\n    }\n\n");

            // --- Constructor (custom comparators) ---
            sb.append("    public ").append(className).append("(").append(functionName).append("<");
            for (int i = 1; i <= n; i++)
                sb.append("A").append(i).append(", ");
            sb.append("R> fn, ");
            for (int i = 1; i <= n; i++) {
                sb.append("BiPredicate<A").append(i).append(", A").append(i).append("> eq").append(i);
                if (i < n)
                    sb.append(", ");
            }
            sb.append(") {\n");
            sb.append("        this.fn = fn;\n");
            for (int i = 1; i <= n; i++) {
                sb.append("        this.eq").append(i).append(" = eq").append(i).append(";\n");
            }
            sb.append("    }\n\n");

            // --- apply(...) method ---
            sb.append("    @Override\n");
            sb.append("    public R apply(");
            for (int i = 1; i <= n; i++) {
                sb.append("A").append(i).append(" a").append(i);
                if (i < n)
                    sb.append(", ");
            }
            sb.append(") {\n");

            sb.append("        if (");
            for (int i = 1; i <= n; i++) {
                sb.append("eq").append(i).append(".test(a").append(i).append("Ref, a").append(i).append(")");
                if (i < n)
                    sb.append(" && ");
            }
            sb.append(") {\n");
            sb.append("            return cachedResult;\n");
            sb.append("        }\n\n");

            sb.append("        this.cachedResult = fn.apply(");
            for (int i = 1; i <= n; i++) {
                sb.append("a").append(i);
                if (i < n)
                    sb.append(", ");
            }
            sb.append(");\n");

            for (int i = 1; i <= n; i++) {
                sb.append("        this.a").append(i).append("Ref = a").append(i).append(";\n");
            }

            sb.append("        return cachedResult;\n");
            sb.append("    }\n");

            sb.append("}\n");

            File file = new File(outputDir, className + ".java");
            try (var writer = new FileWriter(file)) {
                writer.write(sb.toString());
            }
        }

        System.out.println("Generated Memo to Memo16 in: " + outputDir.getAbsolutePath());
    }
}
