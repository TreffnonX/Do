# Do
Utility for Java 8 and above to shorthand common code snippets.

This utility package is intended to shorthand common code snippets like
```java
T val = def;
if (a != null) {
  val = a;
} else if (b != null) {
  val = b;
} ...

// abbreviated to

T val = Either.of(a).or(b)...orElse(def).get();

// also possible:

T val = Either.of(a).or(() -> something()).orElse(() -> somethingElse()).get();
```
and provides helper classes to shorthand if constructs into single expressions,
reducing the outline of certain lambda constellations:
```java
Runnable r = () -> {
  if (condition) {
    operation_a;
  } else {
    operation_b;
  }
};

// abbreviated to

Runnable r = Do.on(condition, () -> operation_a, () -> operation_b);
```
New input for additional shorthands or optimization of existing ones is very welcome.
