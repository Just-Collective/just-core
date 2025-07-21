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

        for (int i = 2; i <= 16; i++) {
            var n = i;
            var prev = n - 1;
            var thisName = "Predicate" + n;
            var functionName = "Function" + n;
            var precedingName = prev > 1 ? "Predicate" + prev : "Predicate";

            var codeGenerator = new CodeGenerator()
                .packageLine("com.bvanseg.just.functional.function.predicate")
                .apply(acg -> {
                    if (n == 2) {
                        acg.importLine("java.util.function.Predicate");
                        acg.newLine();
                    }
                })
                .importLine("com.bvanseg.just.functional.function.Function" + n)
                .newLine()
                .appendAnnotation(FunctionalInterface.class, false)
                .append("public interface ", thisName)
                .appendTypeParams(n, true)
                .append(" extends ", functionName)
                .appendTypeParams(n, false)
                .append(", Boolean>")
                .body(
                    cg -> cg
                        .newLine()
                        .append("boolean test(")
                        .appendArgsWithNames(n)
                        .append(");")
                        .newLine()
                        // 'apply' method
                        .apply(acg -> appendApplyMethod(acg, n))
                        // 'and' method
                        .apply(acg -> appendConditionMethod(acg, thisName, n, "and", "&&"))
                        // 'or' method
                        .apply(acg -> appendConditionMethod(acg, thisName, n, "or", "||"))
                        // 'negate' method
                        .apply(acg -> appendNotMethod(acg, thisName, n))
                        // 'lift' method
                        .apply(acg -> appendLiftMethod(acg, n, thisName, precedingName))
                        // 'alwaysTrue' method
                        .apply(acg -> appendAlwaysMethod(acg, n, thisName, true))
                        // 'alwaysFalse' method
                        .apply(acg -> appendAlwaysMethod(acg, n, thisName, false))
                        // 'not' method
                        .apply(acg -> appendNotMethod(acg, n, thisName))
                        // 'named' method
                        .apply(acg -> appendNamedMethod(acg, n, thisName))
                        .newLine()
                );

            var file = new File(outputDir, thisName + ".java");

            try (var writer = new FileWriter(file)) {
                var content = codeGenerator.build();
                writer.write(content);
            }
        }

        System.out.println("Generated Predicate3 to Predicate16 in: " + outputDir.getAbsolutePath());
    }

    private static void appendNamedMethod(CodeGenerator acg, int n, String thisName) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" named(String name, ", thisName)
            .appendTypeParams(n, true)
            .append(" delegate)")
            .body(
                cg2 -> cg2.append("return new Predicate")
                    .append(n)
                    .appendTypeParams(0, true)
                    .append("()")
                    .body(
                        cg3 -> cg3
                            // 'test' method
                            .newLine()
                            .appendAnnotation(Override.class, false)
                            .append("public boolean test(")
                            .appendArgsWithNames(n)
                            .append(")")
                            .body(
                                cg4 -> cg4.append("return delegate.test(").appendNamesOnly(n).append(");")
                            )
                            // 'toString' method
                            .newLine(2)
                            .appendAnnotation(Override.class, false)
                            .append("public String toString()")
                            .body(cg4 -> cg4.append("return name;"))
                    )
                    .append(";")
            );
    }

    private static void appendNotMethod(CodeGenerator acg, int n, String thisName) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" not(", thisName)
            .appendTypeParams(n, true)
            .append(" predicate)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> !predicate.test(")
                    .appendNamesOnly(n)
                    .append(");")
            );
    }

    private static void appendAlwaysMethod(CodeGenerator acg, int n, String thisName, boolean always) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" always" + (always ? "True" : "False") + "()")
            .body(
                cg2 -> cg2.append("return (")
                    .appendWildcardArgs(n)
                    .append(") -> " + (always ? "true" : "false") + ";")
            );
    }

    private static void appendLiftMethod(CodeGenerator acg, int n, String thisName, String precedingName) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" lift(", precedingName)
            .appendTypeParams(n - 1, true)
            .append(" predicate)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n - 1)
                    .append(", _) -> predicate.test(")
                    .appendNamesOnly(n - 1)
                    .append(");")
            );
    }

    private static void appendNotMethod(CodeGenerator acg, String thisName, int n) {
        acg.newLine(2)
            .append("default ", thisName)
            .appendTypeParams(n, true)
            .append(" negate()")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> !this.test(")
                    .appendNamesOnly(n)
                    .append(");")
            );
    }

    private static void appendConditionMethod(
        CodeGenerator acg,
        String thisName,
        int n,
        String methodName,
        String condition
    ) {
        acg.newLine(2)
            .append("default ", thisName)
            .appendTypeParams(n, true)
            .append(" " + methodName + "(", thisName, "<")
            .appendSuperTypeParams(n)
            .append("> other)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> this.test(")
                    .appendNamesOnly(n)
                    .append(") " + condition + " other.test(")
                    .appendNamesOnly(n)
                    .append(");")
            );
    }

    private static void appendApplyMethod(CodeGenerator acg, int n) {
        acg.newLine()
            .appendAnnotation(Override.class, false)
            .append("default Boolean apply(")
            .appendArgsWithNames(n)
            .append(")")
            .body(cg2 -> cg2.append("return test(").appendNamesOnly(n).append(");"));
    }
}
