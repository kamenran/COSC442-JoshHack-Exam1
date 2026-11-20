# JoshHack repository instructions

- Use Java 17, the Maven Wrapper (`./mvnw`), and JUnit 5.
- Before considering work complete, run the relevant JUnit regression tests, PIT mutation testing, and SpotBugs static analysis.
- Preserve existing production behavior unless a change is explicitly requested.
- Never remove, disable, or weaken existing tests simply to make the build pass.
- Clearly identify all production-code changes for human review.
- Never automatically commit, push, or merge changes.
- Keep modifications scoped to the requested task and report the test results.
