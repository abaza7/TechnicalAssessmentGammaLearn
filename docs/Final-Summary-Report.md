# Final Summary Report

### Executive Summary
**Functional:** 7/9 tests passed, 2 need fixes (name validation + account name with hyphen).

**Performance:** No errors recorded in any run. Baseline & Stress are within target; Spike shows heavy latency (p95 ≈ 12.0 s), user experience degrades under sudden surges (though the system stays up).

## Functional
- Total tests: **9**, Passed: **7**, Failed: **2**.
- Notable failures:
    - **TC-06 – Name with numbers:** Expected inline name error but received success banner.
    - **TC-07 – Account name with hyphen:** Expected success but received inline error.

**Recommendations:** 
- Fix client and server-side validators to:
  - Reject numeric only names.
  - Accept hyphens in account names.
  - Add unit tests for these edge cases.


## Performance
**Tests Summary:**
- Baseline (10 users): Average response time ~820 ms, max 1.6 s, throughput at (~0.56 req/s), no errors. The system is stable
  under light load.
- Stress (500 users): Average response time ~696 ms, max 2.4 s. System scales well, throughput
  stable (~1.7 req/s), no errors.
- Spike (1000 users suddenly): Average response time ~7.2 s, max 16 s. Throughput jumped (~205
  req/s) but response times degraded heavily. No errors observed.


**Conclusion:** 
- The application handles gradual load increases well and remains stable under stress
  conditions. However, it suffers significant latency under sudden spikes, which may impact user
  experience.
- The extremely short spike window (~5 s) with very high p95 is consistent with unwarmed caches which could be the bottleneck.

**Recommendations:**
- Optimizations should be considered to improve resilience to sudden traffic surges.

## Appendices
- Functional Defects
  [Bug-Report](./Bug-Report.xlsx)
- Performance Reports are stored under `performanceTests/<test-type>_html_report/index.html`
