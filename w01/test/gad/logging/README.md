# Test Logging Documentation

This test in `TestLogging.java` is testing the Logging of the maze whether the right way was chosen and has the right length.

## `TestLogging.java`

- `checkLogging() : Arguments` contains a Stream of Arguments which could be expanded if necessary. The arguments have the structure of `int width, int height, int seed, int[] solutionX, int[] solutionY, boolean expect`
- `checkLogging(args...)` checks the Logging Data of an `Walker` instance, and it's  behaviour