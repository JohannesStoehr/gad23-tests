# Test Logging Documentation

This documentation if for the test logging file `loggingCases.txt` and for the executor `LoggingTest.java`.

## `loggingCases.txt`

```html
<numbers>
Log:
<log output>
```

Comments begin with `#` and the different test cases are seperated with four equal signs (`====`)

## `LoggingTest.java`

- `PATH : String` is the path of the test cases.
- `getTestData(String) : Arguments` is converting the data into a usefull formate which is used to call `loggingTest(args ...)` by junit
- `loggingTest() : Strram<Arguments>` is collecting the data from `loggingCases.txt` and than run them through `getTestData(String)`