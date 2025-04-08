-- Estudiantes
INSERT INTO Student VALUES (student_seq.NEXTVAL, 'Juan', 'Pérez', 'S1001', 'juan.perez@email.com', 'ENGINEERING');
INSERT INTO Student VALUES (student_seq.NEXTVAL, 'María', 'Gómez', 'S1002', 'maria.gomez@email.com', 'ENGINEERING');
INSERT INTO Student VALUES (student_seq.NEXTVAL, 'Carlos', 'López', 'S1003', 'carlos.lopez@email.com', 'ADMINISTRATION');
INSERT INTO Student VALUES (student_seq.NEXTVAL, 'Ana', 'Martínez', 'S1004', 'ana.martinez@email.com', 'ENGINEERING');
INSERT INTO Student VALUES (student_seq.NEXTVAL, 'Pedro', 'Rodríguez', 'S1005', 'pedro.rodriguez@email.com', 'LITERATURE');

-- Copias de libros
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC001', 'AVAILABLE', 1);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC002', 'AVAILABLE', 1);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC003', 'AVAILABLE', 2);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC004', 'AVAILABLE', 2);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC005', 'AVAILABLE', 2);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC006', 'AVAILABLE', 3);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC007', 'AVAILABLE', 4);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC008', 'AVAILABLE', 5);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC009', 'AVAILABLE', 6);
INSERT INTO BookCopy VALUES (bookcopy_seq.NEXTVAL, 'BC010', 'AVAILABLE', 6);

-- Préstamos
INSERT INTO Loan VALUES (loan_seq.NEXTVAL, 1, 1, TO_DATE('2023-10-01', 'YYYY-MM-DD'), TO_DATE('2023-10-15', 'YYYY-MM-DD'), TO_DATE('2023-10-14', 'YYYY-MM-DD'), 'RETURNED');
INSERT INTO Loan VALUES (loan_seq.NEXTVAL, 3, 2, TO_DATE('2023-10-05', 'YYYY-MM-DD'), TO_DATE('2023-10-19', 'YYYY-MM-DD'), NULL, 'ACTIVE');
INSERT INTO Loan VALUES (loan_seq.NEXTVAL, 6, 3, TO_DATE('2023-10-10', 'YYYY-MM-DD'), TO_DATE('2023-10-24', 'YYYY-MM-DD'), TO_DATE('2023-10-20', 'YYYY-MM-DD'), 'RETURNED');
INSERT INTO Loan VALUES (loan_seq.NEXTVAL, 7, 4, TO_DATE('2023-10-15', 'YYYY-MM-DD'), TO_DATE('2023-10-29', 'YYYY-MM-DD'), NULL, 'ACTIVE');
INSERT INTO Loan VALUES (loan_seq.NEXTVAL, 9, 5, TO_DATE('2023-09-20', 'YYYY-MM-DD'), TO_DATE('2023-10-04', 'YYYY-MM-DD'), TO_DATE('2023-10-03', 'YYYY-MM-DD'), 'RETURNED');

-- Actualizar estado de copias prestadas
UPDATE BookCopy SET status = 'LOANED' WHERE id IN (1, 3, 6, 7, 9);