# Test Code Documentation

The following test consists of test case file `cases.txt` and test executor file `TestExecutor.java`. It is assumed that in the future exercises the tasks will consist more of 'many cases'. This is a **reusable** test case generator.

## `cases.txt`

```html
<width> <height> <seed>
exit: <true|false>
```

Comments are annotated with `#`, test cases are separated with five equal signs (`=====`)

## `TestExecutor.java`

- `PATH : String` is the path location of the text cases.
- `greenTest()` verification that tests are running for non-mainstream Java IDEs such as Visual Studio Code
- `testMaze(args...)` the verifier of a `Walker` instance behaviour
- `getTestData(String) : Arguments` converts test data into an argument instance, which is used to call `testMaze(args...)` by junit
- `testMaze() : Stream<Arguments>` is the core of the parameterized tests and collects the case files, which is then ran through `getTestData(String)`
