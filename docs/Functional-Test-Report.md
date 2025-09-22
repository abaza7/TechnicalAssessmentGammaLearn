# Functional Test Report

**Run date:** 2025-09-19  
**Env:** https://app.swiftassess.com/Signup

## Summary
- Total: 9
- Passed: 7
- Failed: 2
- Blocked: 0

## Test Cases
| ID    | Title                    | Steps (brief)                                  | Expected                  | Actual                    | Status | Evidence   |
|-------|--------------------------|------------------------------------------------|---------------------------|---------------------------|--------|------------|
| TC-01 | Positive signup          | Fill required fields with valid data, submit   | Success banner            | Success banner            | Passed |            |
| TC-02 | Positive signup          | Fill all fields with valid data, submit        | Success banner            | Success banner            | Passed |            |
| TC-03 | Invalid email            | Enter invalid email, submit                    | Inline email error        | Inline email error        | Passed |            |
| TC-04 | Empty email              | Do not enter email, submit                     | Inline email error        | Inline email error        | Passed |            |
| TC-05 | Empty name               | Do not enter name, submit                      | Inline name error         | Inline name error         | Passed |            |
| TC-06 | Name with numbers        | Enter name with digits only, submit            | Inline name error         | Success banner            | Failed | screenshot |
| TC-07 | Account name with hyphen | Enter account name with a hyphen in it, submit | Success banner            | Inline account name error | Failed | screenshot |
| TC-08 | Account name with space  | Enter account name with a space in it, submit  | Inline account name error | Inline account name error | Passed |            |
| TC-09 | Mobile positive signup   | iPhone 14 Pro Max emulation with valid data    | Success banner            | Success banner            | Passed |            |

## Defects
[Bug-Report](./Bug-Report.xlsx)

## Notes
- Screenshots stored under `selenium/target/screenshots/` for failed tests.
