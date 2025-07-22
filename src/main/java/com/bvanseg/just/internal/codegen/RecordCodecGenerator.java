package com.bvanseg.just.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecordCodecGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: RecordCodecGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0], "com/bvanseg/just/serialization/codec");
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Could not create output directory: " + outputDir);
        }

        var cg = new CodeGenerator();

        cg.packageLine("com.bvanseg.just.serialization.codec")
            .newLine()
            .importLine("java.util.Map")
            .newLine()
            .importLine("com.bvanseg.just.functional.result.Result")
            .importLine("com.bvanseg.just.serialization.codec.schema.CodecSchema")
            .importLine("com.bvanseg.just.functional.function.*")
            .newLine()
            .append("public class RecordCodec")
            .body(cg2 -> {
                cg2.newLine();
                cg2.append(
                    "private static <R, A, T> T encodeField(CodecSchema<T> schema, T map, FieldCodec<R, A> field, R value) "
                )
                    .body(
                        b -> b.append(
                            "return schema.createField(map, field.fieldName(), field.codec().encode(schema, field.getter().apply(value)));"
                        )
                    )
                    .newLine(2);

                cg2.append(
                    "private static <R, A, T> Result<A, T> decodeField(CodecSchema<T> schema, T input, FieldCodec<R, A> field) "
                )
                    .body(
                        b -> b.append(
                            "return schema.getField(input, field.fieldName()).andThen(val -> field.codec().decode(schema, val));"
                        )
                    );

                for (var i = 1; i <= 16; i++) {
                    var n = i;
                    var functionName = n > 1 ? "Function" + n : "Function";

                    cg2.newLine(2)
                        .append(
                            "public static <",
                            cg2.rangeJoin(n, 0, j -> "A" + j),
                            ", R> Codec<R> of(",
                            cg2.rangeJoin(n, 0, j -> "FieldCodec<R, A" + j + "> f" + j),
                            ", ",
                            functionName,
                            "<",
                            cg2.rangeJoin(n, 0, j -> "A" + j),
                            ", R> constructor) "
                        )
                        .body(
                            cg3 -> cg3.append("return new Codec<>()")
                                .body(cg4 -> {
                                    // encode
                                    cg4.newLine()
                                        .appendAnnotation(Override.class, false)
                                        .append("public <T> T encode(CodecSchema<T> codecSchema, R value)")
                                        .body(cg5 -> {
                                            cg5.appendLine("var map = codecSchema.emptyMap();");

                                            for (var j = 1; j <= n; j++) {
                                                cg5.appendLine(
                                                    "map = encodeField(codecSchema, map, f" + j + ", value);"
                                                );
                                            }

                                            cg5.append("return map;");
                                        });

                                    // decode
                                    cg4.newLine(2)
                                        .appendAnnotation(Override.class, false)
                                        .append("public <T> Result<R, T> decode(CodecSchema<T> codecSchema, T input)")
                                        .body(cg5 -> {
                                            for (int j = 1; j <= n; j++) {
                                                if (j == 1) {
                                                    cg5.append("return decodeField(codecSchema, input, f1)");
                                                } else {
                                                    cg5.newLine()
                                                        .append(
                                                            "    .andThen(a" + (j - 1)
                                                                + " -> decodeField(codecSchema, input, f" + j + ")"
                                                        );
                                                }
                                            }

                                            cg5.newLine()
                                                .append("        .map(")
                                                .apply(cg6 -> {
                                                    if (n == 1) {
                                                        cg6.append("constructor");
                                                    } else {
                                                        cg5.append("a" + n + " -> constructor.apply(")
                                                            .append(cg5.rangeJoin(n, 0, j -> "a" + j))
                                                            .append(")".repeat(n));
                                                    }

                                                    cg6.append(");");
                                                });
                                        });
                                })
                                .append(";")
                        );
                }
            });

        File file = new File(outputDir, "RecordCodec.java");
        try (var writer = new FileWriter(file)) {
            writer.write(cg.build());
        }
    }
}
