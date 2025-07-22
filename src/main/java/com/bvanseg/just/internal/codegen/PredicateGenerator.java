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

        for (int i = 1; i <= 16; i++) {
            var n = i;
            var prev = n - 1;
            var thisName = n > 1 ? "Predicate" + n : "Predicate";
            var functionName = n > 1 ? "Function" + n : "Function";
            var precedingName = prev > 1 ? "Predicate" + prev : "Predicate";

            var codeGenerator = new CodeGenerator()
                .packageLine("com.bvanseg.just.functional.function.predicate")
                .apply(acg -> {
                    if (n == 1 || n == 2) {
                        acg.importLine("org.jetbrains.annotations.NotNull");
                        acg.newLine();
                    }
                })
                .importLine("com.bvanseg.just.functional.function." + functionName)
                .newLine()
                .appendAnnotation(FunctionalInterface.class, false)
                .append("public interface ", thisName)
                .appendTypeParams(n, true)
                .append(" extends ")
                .apply(acg -> {
                    acg.append(functionName)
                        .appendTypeParams(n, false)
                        .append(", Boolean>");

                    if (n == 1) {
                        acg.append(", java.util.function.Predicate")
                            .appendTypeParams(n, true);
                    } else if (n == 2) {
                        acg.append(", java.util.function.BiPredicate")
                            .appendTypeParams(n, true);
                    }
                })
                .body(
                    cg -> cg
                        .newLine()
                        .apply(acg -> {
                            if (n == 1 || n == 2) {
                                acg.appendAnnotation(Override.class, false);
                            }
                        })
                        .append("boolean test(")
                        .appendArgsWithNames(n)
                        .append(");")
                        .newLine()
                        .apply(acg -> {
                            appendApplyMethod(acg, n);
                            appendLogicalMethod(acg, thisName, n, "and");
                            appendLogicalMethod(acg, thisName, n, "or");
                            appendLogicalMethod(acg, thisName, n, "xor");
                            appendLogicalMethod(acg, thisName, n, "implies");
                            appendNegateMethod(acg, thisName, n);
                            appendLiftMethod(acg, thisName, n, precedingName);
                            appendPartialFirstAndLastMethods(acg, n, precedingName);
                            appendAlwaysMethod(acg, thisName, n, true);
                            appendAlwaysMethod(acg, thisName, n, false);
                            appendNotMethod(acg, thisName, n);
                            appendFromMethod(acg, thisName, n);
                            appendFromFunctionMethod(acg, thisName, functionName, n);
                            appendNamedMethod(acg, thisName, n);
                        })
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

    private static void appendNamedMethod(CodeGenerator acg, String thisName, int n) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" named(String name, ", thisName)
            .appendSuperTypeParams(n, true)
            .append(" delegate)")
            .body(
                cg2 -> cg2.append("return new ", thisName)
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
                            .body(cg4 -> cg4.append("return \"" + thisName + ".\" + name;"))
                    )
                    .append(";")
            );
    }

    private static void appendNotMethod(CodeGenerator acg, String thisName, int n) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" not(", thisName)
            .appendSuperTypeParams(n, true)
            .append(" predicate)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> !predicate.test(")
                    .appendNamesOnly(n)
                    .append(");")
            );
    }

    private static void appendAlwaysMethod(CodeGenerator acg, String thisName, int n, boolean always) {
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

    private static void appendLiftMethod(CodeGenerator acg, String thisName, int n, String precedingName) {
        if (n == 1) {
            return;
        }

        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" lift(", precedingName)
            .appendSuperTypeParams(n - 1, true)
            .append(" predicate)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n - 1)
                    .append(", _) -> predicate.test(")
                    .appendNamesOnly(n - 1)
                    .append(");")
            );
    }

    private static void appendPartialFirstAndLastMethods(CodeGenerator acg, int n, String precedingName) {
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
            if (i != n)
                acg.append(", ");
        }
        acg.append("> partialFirst(", firstType, " fixed)")
            .body(cg -> {
                cg.append("return (");
                for (int i = 2; i <= n; i++) {
                    cg.append("a").append(i);
                    if (i != n)
                        cg.append(", ");
                }
                cg.append(") -> this.test(fixed, ");
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
        acg.append("> partialLast(", lastType, " fixed)")
            .body(cg -> {
                cg.append("return (");
                for (int i = 1; i < n; i++) {
                    cg.append("a").append(i);
                    if (i != n - 1)
                        cg.append(", ");
                }
                cg.append(") -> this.test(");
                for (int i = 1; i < n; i++) {
                    cg.append("a").append(i).append(", ");
                }
                cg.append("fixed);");
            });
    }

    private static void appendNegateMethod(CodeGenerator acg, String thisName, int n) {
        acg.newLine(2)
            .apply(acg2 -> {
                if (n == 1 || n == 2) {
                    acg2.appendAnnotation(Override.class, false);
                }
            })
            .append("default " + ((n == 1 || n == 2) ? "@NotNull " : ""), thisName)
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

    private static void appendLogicalMethod(
        CodeGenerator acg,
        String thisName,
        int n,
        String methodName
    ) {
        var operator = switch (methodName) {
            case "and" -> "&&";
            case "or", "implies" -> "||";
            case "xor" -> "^";
            default -> throw new UnsupportedOperationException("Unsupported operation: '" + methodName + "'.");
        };

        acg.newLine(2)
            .append("default ", thisName)
            .appendTypeParams(n, true)
            .append(" " + methodName + "(", thisName)
            .appendSuperTypeParams(n, true)
            .append(" other)")
            .body(
                cg2 -> cg2.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> ", (methodName.equals("implies") ? "!" : ""), "this.test(")
                    .appendNamesOnly(n)
                    .append(") " + operator + " other.test(")
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

    private static void appendFromMethod(CodeGenerator acg, String thisName, int n) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" from(")
            .append("java.util.function.Function<? super A1, ")
            .apply(cg -> {
                for (var i = 2; i <= n; i++) {
                    cg.append("? extends java.util.function.Function<? super A" + i + ", ");
                }

                cg.append("Boolean");
                cg.append(">".repeat(n));
            })
            .append(" fn)")
            .body(cg -> {
                if (n == 1) {
                    cg.append("return fn::apply;");
                    return;
                }

                cg.append("return (")
                    .appendNamesOnly(n)
                    .append(") -> ");

                // Build nested calls: fn.apply(a1).apply(a2)...
                cg.append("fn");
                for (int i = 1; i <= n; i++) {
                    cg.append(".apply(a" + i + ")");
                }

                cg.append(";");
            });
    }

    private static void appendFromFunctionMethod(CodeGenerator acg, String thisName, String functionName, int n) {
        acg.newLine(2)
            .append("static ")
            .appendTypeParams(n, true)
            .append(" ", thisName)
            .appendTypeParams(n, true)
            .append(" from(", functionName)
            .appendSuperTypeParams(n, false)
            .append(", Boolean> fn)")
            .body(cg -> cg.append("return fn::apply;"));
    }
}
