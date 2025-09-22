# Test Plan – SwiftAssess Signup

**Functional URL:** https://app.swiftassess.com/Signup  
**Performance URL (staging):** https://app-stg.swiftassess.com/Signup  
**Date:** 2025-09-18

## 1. Scope
- Functional automation of Signup page: positive/negative validation, including testing on an emulation of iPhone 14 Pro Max.
- Performance testing on staging: Baseline (10 users), Stress (500), Spike (1000 sudden).

## 2. Strategy
- Functional: Selenium (Java 17+), TestNG, POM, headless CI option, screenshots on failure.
- Performance: JMeter 5.6+ HTTP plans, CLI runs, HTML dashboards.

## 3. Assumptions
- Test data and environment are stable.
- CAPTCHA/WAF disabled or whitelisted for testing; or test keys provided.
- Email confirmation not required to assert success; success verified by confirmation banner.

## 4. Entry / Exit
- Entry: URLs reachable, test data prepared, access approved.
- Exit: Planned tests executed, tests results report created, critical defects logged, load KPIs produced and analyzed.


## 5. Environment
- Browser: Chrome (latest)
- OS: Any
- JDK: 17+, Maven
- JMeter: 5.6+

## 6. Risks / Constraints
- Captcha can block automation or load tests.
- Rate limits may skew throughput.

## 7. Deliverables
- Source code.
- Functional test report.
- Load test report (HTML dashboards).
- Bug report (Excel).
- Final summary with findings & recommendations.
