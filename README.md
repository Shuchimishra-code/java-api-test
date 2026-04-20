# Java API + SQL Assignment Solution

## Overview
This assignment involves:
- Generating webhook
- Solving SQL problem
- Sending final query via API


## Step 1: Webhook Generation

POST:
https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA

Body:
```json
{
  "name": "Shuchi Mishra",
  "regNo": "REG12347", 
  "email": "shuchi@gmail.com"
}
```

## Step 2: SQL Solution

```sql
SELECT p.AMOUNT AS SALARY,
       CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
       TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE,
       d.DEPARTMENT_NAME
FROM PAYMENTS p
JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
WHERE DAY(p.PAYMENT_TIME) != 1
ORDER BY p.AMOUNT DESC
LIMIT 1;
```


## Step 3: Final Submission

POST:
https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA

Headers:
Authorization: Bearer <accessToken>

Body:
```json
{

  "finalQuery": "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE DAY(p.PAYMENT_TIME) != 1 ORDER BY p.AMOUNT DESC LIMIT 1;"

}
```

```markdown
Note: This endpoint is a POST API and cannot be accessed directly via browser.

## Result

```json
{
  "success": true,
  "message": "Webhook processed successfully"
}
## Note

The API workflow was successfully tested using Postman.
Spring Boot setup was initiated, but due to environment constraints, execution was validated via API testing.
