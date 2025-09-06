package com.just.core.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecordStreamCodecGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: RecordStreamCodecGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0], "com/just/core/serialization/codec/stream");

        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Could not create output directory: " + outputDir.getAbsolutePath());
        }

        var cg = new CodeGenerator()
            .packageLine("com.bvanseg.just.serialization.codec.stream")
            .importLine("org.jetbrains.annotations.NotNull")
            .newLine()
            .importLine("com.bvanseg.just.serialization.codec.stream.schema.StreamCodecSchema")
            .newLine()
            .importLine("com.bvanseg.just.functional.function.*")
            .newLine()
            .append("public final class RecordStreamCodec")
            .body(g -> {
                for (int n = 1; n <= 16; n++) {
                    appendOfMethod(g, n);
                }
            });

        File file = new File(outputDir, "RecordStreamCodec.java");

        try (var writer = new FileWriter(file)) {
            writer.write(cg.build());
        }

        System.out.println("Generated RecordStreamCodec with arities 1–16 in: " + outputDir.getAbsolutePath());
    }

    private static void appendOfMethod(CodeGenerator g, int n) {
        var functionName = n > 1 ? "Function" + n : "Function";

        g.newLine(1)
            .append("public static ")
            .appendTypeParams(n, false);

        g.append(", R> StreamCodec<R> of(").newLine();

        for (int i = 1; i <= n; i++) {
            g.append("    StreamCodec<A", i, "> codec", i, ",")
                .newLine()
                .append("    Function<R, A", i, "> getter", i, ",")
                .newLine();
        }

        g.append("    ", functionName)
            .appendTypeParams(n, false)
            .append(", R> constructor")
            .newLine()
            .append(")")
            .body(
                g2 -> g2.append("return new StreamCodec<>()")
                    .body(g3 -> {
                        g3.appendAnnotation(Override.class, false)
                            .append(
                                "public <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull R value)"
                            )
                            .body(g4 -> {
                                for (int i = 1; i <= n; i++) {
                                    g4.append("codec")
                                        .append(i)
                                        .append(".encode(streamCodecSchema, input, getter")
                                        .append(i)
                                        .append(".apply(value));");

                                    if (i < n) {
                                        g4.newLine();
                                    }
                                }
                            });

                        g3.newLine(2)
                            .appendAnnotation(Override.class, false)
                            .append(
                                "public <T> @NotNull R decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input)"
                            )
                            .body(g4 -> {
                                for (int i = 1; i <= n; i++) {
                                    g4.append("var a", i, " = codec", i, ".decode(streamCodecSchema, input);")
                                        .newLine();
                                }

                                g4.append("return constructor.apply(");

                                for (int i = 1; i <= n; i++) {
                                    g4.append("a").append(i);

                                    if (i != n) {
                                        g4.append(", ");
                                    }
                                }

                                g4.append(");");
                            });
                    })
                    .append(";")
            );
    }
}
