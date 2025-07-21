package com.bvanseg.just.internal.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TupleGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: TupleGenerator <output directory>");
            System.exit(1);
        }

        File outputDir = new File(args[0]);
        File packageDir = new File(outputDir, "com/bvanseg/just/functional/tuple");
        if (!packageDir.mkdirs() && !packageDir.exists()) {
            throw new IOException("Failed to create output directory: " + packageDir);
        }

        for (int i = 2; i <= 16; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("package com.bvanseg.just.functional.tuple;\n\n");

            sb.append("public record Tuple").append(i).append("<");
            for (int j = 1; j <= i; j++) {
                if (j > 1)
                    sb.append(", ");
                sb.append("T").append(j);
            }
            sb.append(">(");
            for (int j = 1; j <= i; j++) {
                if (j > 1)
                    sb.append(", ");
                sb.append("T").append(j).append(" v").append(j);
            }
            sb.append(") {}\n");

            File outputFile = new File(packageDir, "Tuple" + i + ".java");
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(sb.toString());
            }
        }

        System.out.println("Generated tuples into: " + packageDir.getAbsolutePath());
    }
}
