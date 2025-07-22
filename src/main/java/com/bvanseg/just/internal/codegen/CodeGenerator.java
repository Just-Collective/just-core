package com.bvanseg.just.internal.codegen;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodeGenerator {

    private final StringBuilder stringBuilder;

    private int depth;

    public CodeGenerator() {
        this.stringBuilder = new StringBuilder();
    }

    public CodeGenerator apply(Consumer<CodeGenerator> consumer) {
        consumer.accept(this);
        return this;
    }

    public CodeGenerator packageLine(String packageLocation) {
        append("package ").append(packageLocation).append(";");
        newLine();
        newLine();
        return this;
    }

    public CodeGenerator importLine(String importLocation) {
        append("import ");
        append(importLocation);
        append(";");
        newLine();
        return this;
    }

    public CodeGenerator newLine() {
        newLine(1);
        return this;
    }

    public CodeGenerator newLine(int count) {
        append("\n".repeat(count));
        append("    ".repeat(depth));
        return this;
    }

    public CodeGenerator append(String text) {
        stringBuilder.append(text);
        return this;
    }

    public CodeGenerator append(Object... text) {
        Arrays.stream(text).forEach(obj -> append(obj.toString()));
        return this;
    }

    public CodeGenerator append(int number) {
        return append(Integer.toString(number));
    }

    public CodeGenerator appendLine(String line) {
        append(line);
        newLine();
        return this;
    }

    public CodeGenerator body(Consumer<CodeGenerator> consumer) {
        append(" {");
        depth++;
        newLine();
        consumer.accept(this);
        depth--;
        newLine();
        append("}");

        return this;
    }

    public CodeGenerator appendAnnotation(Class<? extends Annotation> annotationClass, boolean inline) {
        var annotationName = "@" + annotationClass.getSimpleName();

        if (inline) {
            append(annotationName);
        } else {
            appendLine(annotationName);
        }

        return this;
    }

    public CodeGenerator appendTypeParams(int n, boolean closed) {
        appendTypeParams(n, 0, closed);
        return this;
    }

    public CodeGenerator appendTypeParams(int n, int offset, boolean closed) {
        var range = rangeJoin(n, offset, i -> "A" + i);
        append("<");
        append(range);

        if (closed) {
            append(">");
        }

        return this;
    }

    public CodeGenerator appendArgsWithNames(int n) {
        appendArgsWithNames(n, 0);
        return this;
    }

    public CodeGenerator appendArgsWithNames(int n, int offset) {
        var range = rangeJoin(n, offset, i -> "A" + i + " a" + i);
        append(range);
        return this;
    }

    public CodeGenerator appendNamesOnly(int n) {
        appendNamesOnly(n, 0);
        return this;
    }

    public CodeGenerator appendNamesOnly(int n, int offset) {
        var range = rangeJoin(n, offset, i -> "a" + i);
        append(range);
        return this;
    }

    public CodeGenerator appendWildcardArgs(int n) {
        appendWildcardArgs(n, 0);
        return this;
    }

    public CodeGenerator appendWildcardArgs(int n, int offset) {
        var range = rangeJoin(n, offset, _ -> "_");
        append(range);
        return this;
    }

    public CodeGenerator appendSuperTypeParams(int n, boolean closed) {
        appendSuperTypeParams(n, 0, closed);
        return this;
    }

    public CodeGenerator appendSuperTypeParams(int n, int offset, boolean closed) {
        var range = rangeJoin(n, offset, i -> "? super A" + i);
        append("<");
        append(range);

        if (closed) {
            append(">");
        }

        return this;
    }

    public String rangeJoin(int n, int offset, IntFunction<String> mapper) {
        return IntStream.rangeClosed(1 + offset, n)
            .mapToObj(mapper)
            .collect(Collectors.joining(", "));
    }

    public String build() {
        return stringBuilder.toString();
    }
}
