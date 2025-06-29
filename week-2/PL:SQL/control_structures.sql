
-- PL/SQL Exercise 1: Control Structures

-- customers(customer_id, name, age, balance, isVIP)
-- loans(loan_id, customer_id, due_date, interest_rate)

-- Scenario 1: Interest Discount for Seniors
BEGIN
  FOR cust IN (SELECT customer_id FROM customers WHERE age > 60) LOOP
    UPDATE loans
    SET interest_rate = interest_rate - 1
    WHERE customer_id = cust.customer_id;
  END LOOP;
  COMMIT;
END;
/

-- Scenario 2: Promote to VIP
BEGIN
  FOR cust IN (SELECT customer_id FROM customers WHERE balance > 10000) LOOP
    UPDATE customers
    SET isVIP = 'TRUE'
    WHERE customer_id = cust.customer_id;
  END LOOP;
  COMMIT;
END;
/

-- Scenario 3: Loan Due Reminders
DECLARE
  v_name customers.name%TYPE;
  v_due_date loans.due_date%TYPE;
BEGIN
  FOR loan_rec IN (
    SELECT c.name, l.due_date
    FROM loans l
    JOIN customers c ON l.customer_id = c.customer_id
    WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || loan_rec.name || ', your loan is due on ' || TO_CHAR(loan_rec.due_date, 'DD-MON-YYYY'));
  END LOOP;
END;
