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

        for (int i = 1; i <= 16; i++) {
            var n = i;
            var prev = n - 1;
            var thisName = n == 1 ? "Function" : "Function" + n;
            var precedingName = prev > 1 ? "Function" + prev : "Function";
            var memoName = n == 1 ? "Memo" : "Memo" + n;

            var cg = new CodeGenerator()
                .packageLine("com.bvanseg.just.functional.function")
                .importLine("com.bvanseg.just.functional.function.memo." + memoName)
                .apply(acg -> {
                    if (n == 2) {
                        acg.importLine("java.util.function.BiFunction");
                    }
                })
                .newLine()
                .appendAnnotation(FunctionalInterface.class, false)
                .apply(acg -> {
                    acg.append("public interface ", thisName)
                        .appendTypeParams(n, false)
                        .append(", R>");

                    if (n == 1) {
                        acg.append("extends java.util.function.Function<A1, R>");
                    } else if (n == 2) {
                        acg.append("extends BiFunction<A1, A2, R>");
                    }
                })
                .body(cg2 -> {
                    appendApplyMethod(cg2, n);
                    appendAndThenMethod(cg2, thisName, n);
                    appendMemoizeMethod(cg2, memoName, n);
                    appendCurriedMethod(cg2, n);
                    appendPartialFirstAndLastMethods(cg2, precedingName, n);
                    appendFromMethod(cg2, thisName, n);
                });

            var file = new File(outputDir, thisName + ".java");

            try (var writer = new FileWriter(file)) {
                writer.write(cg.build());
            }
        }

        System.out.println("Generated Function to Function16 in: " + outputDir.getAbsolutePath());
    }

    private static void appendApplyMethod(CodeGenerator cg, int n) {
        cg.newLine();

        if (n == 1 || n == 2) {
            cg.appendAnnotation(Override.class, false);
        }

        cg.append("R apply(")
            .appendArgsWithNames(n)
            .append(");")
            .newLine();
    }

    private static void appendAndThenMethod(CodeGenerator cg, String thisName, int n) {
        cg.newLine()
            .append("default <Z> ")
            .append(thisName)
            .appendTypeParams(n, false)
            .append(", Z>")
            .append(" andThen(Function<? super R, ? extends Z> after)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> after.apply(this.apply(")
                    .appendNamesOnly(n)
                    .append("));")
            );
    }

    private static void appendMemoizeMethod(CodeGenerator cg, String memoName, int n) {
        cg.newLine(2)
            .append("default ", memoName)
            .appendTypeParams(n, false)
            .append(", R> memoize()")
            .body(cg2 -> cg2.append("return new ", memoName, "<>(this);"));
    }

    private static void appendCurriedMethod(CodeGenerator cg, int n) {
        if (n <= 1) {
            return;
        }

        cg.newLine(2)
            .append("default Function<A1, ");

        for (int i = 2; i < n; i++) {
            cg.append("Function<A" + i + ", ");
        }

        cg.append("Function<A" + n + ", R>");

        for (int i = 2; i < n; i++) {
            cg.append(">");
        }

        cg.append("> curried()")
            .body(cg2 -> {
                cg2.append("return ");

                for (int i = 1; i <= n; i++) {
                    cg2.append("a" + i + " -> ");
                }

                cg2.append("this.apply(").appendNamesOnly(n).append(");");
            });
    }

    private static void appendFromMethod(CodeGenerator cg, String thisName, int n) {
        if (n <= 1) {
            return;
        }

        cg.newLine(2)
            .append("static ")
            .appendTypeParams(n, false)
            .append(", R> ")
            .append(thisName)
            .appendTypeParams(n, false)
            .append(", R> from(");

        // nested curried Function
        cg.append("Function<");

        for (int i = 1; i < n; i++) {
            cg.append("? super A" + i).append(", ? extends Function<");
        }

        cg.append("? super A" + n).append(", ? extends R>");

        for (int i = 1; i < n; i++) {
            cg.append(">");
        }

        cg.append(" curried")
            .append(")")
            .body(cg2 -> {
                cg2.append("return (").appendArgsWithNames(n).append(") -> curried");

                for (int i = 1; i <= n; i++) {
                    cg2.append(".apply(a" + i + ")");
                }

                cg2.append(";");
            });
    }

    private static void appendPartialFirstAndLastMethods(CodeGenerator acg, String precedingName, int n) {
        if (n == 1) {
            return;
        }

        var firstType = "A1";
        var lastType = "A" + n;

        // partialFirst
        acg.newLine(2)
            .append("default ", precedingName)
            .append("<");
        for (int i = 2; i <= n; i++) {
            acg.append("A").append(i);
            if (i != n) {
                acg.append(", ");
            }
        }
        acg.append(", R> partialFirst(", firstType, " fixed)")
            .body(cg -> {
                cg.append("return (");
                for (int i = 2; i <= n; i++) {
                    cg.append("a").append(i);
                    if (i != n)
                        cg.append(", ");
                }
                cg.append(") -> this.apply(fixed, ");
                for (int i = 2; i <= n; i++) {
                    cg.append("a").append(i);
                    if (i != n)
                        cg.append(", ");
                }
                cg.append(");");
            });

        // partialLast
        acg.newLine(2)
            .append("default ", precedingName)
            .append("<");
        for (int i = 1; i < n; i++) {
            acg.append("A").append(i);
            if (i != n - 1)
                acg.append(", ");
        }
        acg.append(", R> partialLast(", lastType, " fixed)")
            .body(cg -> {
                cg.append("return (");
                for (int i = 1; i < n; i++) {
                    cg.append("a").append(i);
                    if (i != n - 1)
                        cg.append(", ");
                }
                cg.append(") -> this.apply(");
                for (int i = 1; i < n; i++) {
                    cg.append("a").append(i).append(", ");
                }
                cg.append("fixed);");
            });
    }
}
