# Load Test Report

**Run date:** 2025-09-21  
**Env:** https://app-stg.swiftassess.com/Signup

## Scenarios
- Baseline: 10 users, ramp-up 20s
- Stress: 500 users, ramp-up 5m
- Spike: 0 to 1000 users within 5s

## KPIs
| Scenario | Avg RT (ms) |   p90 |   p95 |   p99 | Throughput (req/s) |  Error % |
|----------|------------:|------:|------:|------:|-------------------:|---------:|
| Baseline |         820 |  1184 |  1399 |  1572 |               0.56 |    0.00% |
| Stress   |         696 |   703 |   740 |  1252 |               1.67 |    0.00% |
| Spike    |        7183 | 10845 | 11999 | 14447 |             204.67 |    0.00% |

## Findings
- **Baseline:** Stable. Avg 820 ms, p95 1399 ms, 0.00% errors.
- **Stress:** Still stable. Avg 696 ms, p95 740 ms, 0.00% errors.
- **Spike:** High latency under burst: Avg 7183 ms, p95 11999 ms, p99 14447 ms, throughput 204.67 req/s, 0.00% errors.

## System/Resource Bottlenecks (observable)
- Baseline & Stress: None evident from results (no errors, low p95).
- Spike: Elevated response times without errors indicate server saturation/queueing during burst traffic.

## Recommendations
- Optimizations should be considered to improve resilience to sudden traffic surges.
- Run a soak test to check for resource leaks and a step-load test to find the exact saturation point.

## Attachments
- Baseline HTML report: `baseline_html_report`
- Stress HTML report: `stress_html_report`
- Spike HTML report: `spike_html_report`