# TP-SF-LOGIN-001: Salesforce Login Test Plan

**Status:** Draft for review  
**Plan ID:** TP-SF-LOGIN-001 (locally assigned)  
**Application:** Salesforce CRM login  
**Feature:** Username/password authentication  
**Requested entry URL:** `https://login.salesforce.com/?locale=in`

## 1. Test Plan ID and Title

TP-SF-LOGIN-001: Salesforce Login Test Plan

## 2. Objective and References

### Objective

Define a reproducible, risk-aware functional test approach for Salesforce username/password login. The plan covers successful login, rejected credentials, and required-field/input validation, with observable checks that confirm whether the user remains unauthenticated or reaches an authenticated Salesforce landing page.

### References

| Reference | Use |
| --- | --- |
| [Generic QA template](../04_RICE_POT_Generic_QA_Template.md) | Test Plan profile and workflow |
| [Salesforce login automation prompt](../01_RICE_POT_Prompt.md) | Supplied login controls, URL, and valid/invalid scenario context |
| [Maven project configuration](../selenium_framework/pom.xml) | Existing proposed Selenium, Java, and TestNG execution stack |

The source prompt describes username, password, submit, and Remember Me controls. A live public-page inspection during planning showed a username-first variant that reveals the password field after selecting Log In. This observation is a planning input, not a guarantee that the same variant will appear in every environment or session.

### Confirmed Inputs

- The requested starting URL is the public Salesforce login URL above.
- Planned browser target is Chrome on Windows desktop.
- In-scope scenarios are successful login, rejected login, and required-field/input validation.
- Successful login means reaching an authenticated Salesforce landing page. The valid test account is expected not to require MFA.

### Unresolved Inputs

- Authorized non-production environment URL and environment owner.
- Provisioned test account and confirmation that it can authenticate without MFA.
- Approved invalid-input policy and any exact expected validation/error text.
- Browser and operating system versions, test schedule, and business-approved exit thresholds.

## 3. In Scope and Out of Scope

### In Scope

- Username/password login through the Salesforce UI.
- Successful authentication with an authorized, provisioned test account.
- Rejection of invalid credentials without creating an authenticated session.
- Empty required fields and malformed username input, with no authenticated session.
- The observed username-first login variant and a combined username/password variant if the approved test environment presents it.
- Functional system-level checks in Chrome on a Windows desktop.

### Out of Scope

- Remember Me behavior, despite its presence in the source prompt; it was not selected for this plan's coverage.
- MFA setup or completion, SSO/federation, password reset, account recovery, account lockout policy, and registration.
- Mobile devices, responsive layout certification, and browsers other than Chrome.
- Performance, load, penetration, and broad security testing.
- API or backend authentication testing.

Authentication attempts against the public production login endpoint are not authorized by this plan. Execution requires an approved non-production environment or explicit written authorization and security approval.

## 4. Requirements and Planned Coverage

No business requirement IDs were supplied. The following IDs are locally assigned for this plan and require stakeholder confirmation.

| Local ID | Requirement / expected behavior | Planned coverage | Observable result |
| --- | --- | --- | --- |
| LREQ-01 | An active, authorized user with valid credentials can authenticate. | Positive functional test using the provisioned non-MFA test account. | The authenticated landing page is reached; the login form is no longer the active state. |
| LREQ-02 | Invalid credentials do not authenticate a user. | Negative functional test using a synthetic, non-account username and invalid password. | A login failure is communicated; no authenticated landing page or authenticated session is present. Exact error copy is not specified. |
| LREQ-03 | Empty required fields and malformed username input do not authenticate a user. | Negative/input-validation checks for empty username, empty password, both empty, and malformed username. | Submission is blocked or a validation/login failure is shown; no authenticated session is created. Exact client/server behavior requires confirmation. |

Coverage is limited to these three locally assigned requirements. This plan does not claim comprehensive Salesforce UI, security, or authentication coverage.

## 5. Test Approach, Levels, and Types

- **Level:** Black-box system/end-to-end testing of the browser login flow.
- **Types:** Positive functional, negative functional, and input-validation testing.
- **Method:** Start each scenario in a clean browser session. Navigate to the approved environment URL, perform one scenario, and verify the observable result. Do not reuse authenticated state between cases.
- **Login variants:** Identify which login form variant is presented. For username-first flow, submit the username to reveal the password step before entering the password. Record unexpected differences as defects or environment changes; do not retry unconditionally.
- **Negative testing controls:** Use synthetic invalid data and limit failed attempts to avoid account lockout or service protections. Never use another person's credentials.
- **Assertions:** Do not assert undocumented exact error text. Confirm the error/validation state and absence of authenticated state until product-approved copy and behavior are supplied.
- **Regression:** Repeat the in-scope cases after changes to the login UI, authentication configuration, or browser automation code.
- **Not planned:** MFA, SSO, accessibility, compatibility beyond the selected target, performance, and security assurance are excluded as listed above.

## 6. Environment, Tools, Access, and Test Data

| Item | Plan / status |
| --- | --- |
| Environment | Not provided. A non-production Salesforce environment is required for routine execution. |
| Requested URL | `https://login.salesforce.com/?locale=in`; this is a public production login endpoint and is not approved for test authentication by this plan. |
| Browser / OS | Chrome on Windows desktop; exact versions to be recorded before execution. |
| Automation | Proposed: Selenium WebDriver 4, Java 17, Maven, and TestNG, using the existing project configuration. |
| Access | Environment owner must provide authorized access and confirm the target is safe for automated testing. |
| Valid test data | One provisioned, active, non-MFA test account. Credentials must be supplied through an approved secret mechanism and must not be stored in source control, logs, or evidence. Not provided. |
| Invalid test data | Synthetic username under the reserved `.invalid` domain and a synthetic invalid password. Do not use a real user's identifier. |
| Input-validation data | Empty username, empty password, both empty, and a malformed synthetic username. Exact approved data values may be set by the test owner. |
| Network / browser state | Stable network access; clean browser profile/session per test; no saved credentials or pre-existing authentication cookies. |

## 7. Entry and Exit Criteria

### Proposed Entry Criteria

- Written approval identifies the test environment and authorizes UI automation there.
- A working non-MFA test account and required test data are provisioned and verified by the environment owner.
- Product or QA owner confirms the authenticated landing-page oracle and the intended handling of empty/malformed input.
- Chrome and Windows versions, access prerequisites, and test execution window are agreed.
- Automation dependencies are installed and the test runner can launch Chrome successfully.

### Proposed Exit Criteria

- All planned in-scope cases have an execution result; target execution is 100% of approved cases.
- No unresolved Severity 1 or Severity 2 defects remain for the in-scope login behavior; severity definitions must follow the owning team's policy.
- Any blocked cases, environment instability, unexpected login variants, and lower-severity defects are documented with owners or follow-up decisions.
- QA and the designated product/security approver review the results and record acceptance. Names and approval process are not provided.

These thresholds are proposals for agreement, not supplied release criteria.

## 8. Roles, Responsibilities, Estimates, and Schedule

| Role | Proposed responsibility | Assignment / timing |
| --- | --- | --- |
| QA / test owner | Maintain cases, execute or supervise the suite, record results, and report defects. | Not provided |
| Salesforce environment owner | Provide the authorized test URL, access, test account, and environment availability. | Not provided |
| Product owner | Confirm intended login and validation outcomes and approve scope/exit criteria. | Not provided |
| Security or identity owner | Approve environment and authentication testing constraints, including account and MFA configuration. | Not provided |

Estimates, named owners, and schedule are not provided and should be agreed after environment access and test data are available.

## 9. Defect Management and Reporting

- Record reproducible steps, expected and actual outcomes, environment URL, browser/OS versions, timestamp, login variant, and relevant sanitized evidence.
- Do not attach passwords, session cookies, tokens, full credential-bearing URLs, or unredacted personal data to defects or logs.
- Use the organization's severity, priority, triage, and reporting conventions; these conventions were not provided.
- Proposed triage: review login-blocking defects with the QA, product, and environment owners before further execution. Cadence and ownership require agreement.
- Report executed, passed, failed, blocked, and not-run case counts separately. Do not treat skipped or blocked cases as passes.

## 10. Risks, Dependencies, Assumptions, and Open Questions

### Risks and Dependencies

- The requested URL is the production Salesforce login endpoint; unauthorized automated attempts could affect accounts, trigger defenses, or violate policy.
- Salesforce may vary the login UI through A/B experiments, locale, cookies, or rollout changes. Locators and flow must be validated in the approved environment.
- CAPTCHA, rate limiting, identity challenges, or account lockout controls can block execution and must not be bypassed.
- The positive case depends on a provisioned account that reaches the authenticated landing page without MFA. If this prerequisite is unavailable, the positive case is blocked; do not redefine an MFA prompt as successful authentication without approval.
- Exact error messages and input-validation behavior are not documented in the supplied requirements.

### Assumptions

- A non-production Salesforce environment can be supplied for routine execution.
- The test account is synthetic/approved, active, and configured for the agreed no-MFA test path.
- Salesforce login errors are verified by visible failure state and unauthenticated status, not by unapproved exact wording.
- Proposed entry/exit thresholds are subject to stakeholder approval.

### Open Questions

- What is the approved non-production URL, and who authorizes test execution?
- Who provisions and owns the valid non-MFA test account?
- What precise authenticated page or element is the accepted success oracle?
- Should malformed usernames be rejected by client-side validation, or is a server-side login failure acceptable?
- What severity definitions, release thresholds, reporting cadence, and sign-off roles apply?

## 11. Suspension and Resumption Criteria

### Suspend Testing When

- The target is production or its authorization is unclear.
- The environment is unavailable, unstable, or does not match the approved target.
- CAPTCHA, rate limiting, account lockout warnings, or unexpected identity challenges appear.
- A test risks using a real user's account or exposing credentials/session data.
- The observed authentication flow materially differs from the approved test oracle.

### Resume Testing When

- The environment owner confirms service availability and target URL.
- The test owner has authorization, safe test data, and a reset/recovery path for the account.
- Security/product owners resolve the blocking behavior or approve a revised scope and expected result.
- The suite is confirmed to use the agreed browser, account, and clean-session setup.

## 12. Test Deliverables and Approval

### Deliverables

- This test plan and its approved revisions.
- An execution record mapping results to LREQ-01 through LREQ-03.
- Defect reports and sanitized evidence for failures.
- A completion summary stating coverage, blocked items, residual risks, and approval status.

### Approval

Plan approval is required from the QA/test owner, product owner, and environment/security owner before execution. Approver names and approval date are not provided. Approval of this plan does not authorize production login attempts or waive the stated environment and account prerequisites.