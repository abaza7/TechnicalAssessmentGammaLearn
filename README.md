# SwiftAssess Signup – QA Automation & Load Testing

This repository contains a complete starter kit for functional UI tests (Selenium + TestNG + POM) and
load tests (JMeter) for the SwiftAssess Signup flow.

**Functional under test:** https://app.swiftassess.com/Signup  
**Performance (staging):** https://app-stg.swiftassess.com/Signup

## Structure

```
Technical_Assessment/
├─ README.md
├─ docs/
│  ├─ TestPlan.md
│  ├─ Functional-Test-Report.md
│  ├─ Load-Test-Report.md
│  ├─ Bug-Report.xlsx
│  └─ Final-Summary-Report.md
├─ functionalTestsProject/
│  ├─ pom.xml
│  ├─ src/main/java/com/swiftAssess/driver/DriverManager.java
│  ├─ src/main/java/com/swiftAssess/pages/SignupPage.java
│  ├─ src/main/java/com/swiftAssess/utils/Config.java
│  ├─ src/main/java/com/swiftAssess/utils/ScreenshotUtil.java
│  ├─ src/main/java/com/swiftAssess/utils/Retry.java
│  ├─ src/test/java/com/swiftAssess/tests/SignupTests.java
│  ├─ src/test/java/com/swiftAssess/tests/SignupMobileTests.java
│  └─ src/test/resources/testng.xml
└─ performanceTests/
   ├─ signup-baseline.jmx
   ├─ signup-stress.jmx
   └─ signup-spike.jmx
```

## Quick start

### Functional (Selenium + TestNG)

Prereqs: **JDK 17+**, **Maven**, **Chrome** installed.

```bash

cd selenium
mvn -Dheadless=true test
# Reports: target/surefire-reports/
# Screenshots on failure: target/screenshots/
```

### Performance (JMeter)

Prereqs: **JMeter 5.6+** on PATH (`jmeter -v`).

```bash

cd performanceTests
jmeter -n -t signup-<test-type>.jmx -l <test-type>_results.jtl -e -o <test-type>_html_report
# Reports: <test-type>_html_report/index.html

```
Where <test-type> can be:

`baseline, spike, stress`

**Please coordinate before Stress/Spike runs.**


