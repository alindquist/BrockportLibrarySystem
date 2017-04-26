# 429-Squad-2

The EOP Office at the College at Brockport provides a service to the students who are registered with them. This service includes allowing these students to borrow a set of textbooks that they need for the classes they have registered for that semester. These students may borrow these books for no charge from EOP, and they are allowed to keep them for the rest of the semester. There is no limit on the number of books they can check out. However, at, or before, the end of the semester (i.e., at or before the due date indicated on a book), the student must return the book(s) (s)he has borrowed to EOP. Failing to do this on time will result in the student being marked as “delinquent”. A delinquent student is not allowed to borrow any more books until their account is cleared. There is an exception to this rule – if the person at the desk lending the books out has “Administrator” credentials with the system, then they can over-ride the system’s restriction on borrowing books and let the student borrow the additional book. In addition, it is only an “Administrator” who can clear the students’ account of the “delinquency”. 

The system should also allow EOP personnel – those designated as “Administrator” – to see a list of all books that are currently rented out, all books that are currently in their “inventory”, and all users (i.e., borrowing students) who currently have at least one book borrowed (rented out). 

Thus, the basic functionality that the envisaged computerized system should provide includes: 

i.	Add a book to the system (Note: each book will have a barcode label pre-assigned – i.e., “stuck on” – to it. This will be a 5-digit barcode, with the first two digits indicating book type – e.g., “20” will be an English book, “30” a Math book, and so on).
ii.	Modify information about a book in the system
iii.	Delete a book from the system
iv.	Add a student borrower to the system
v.	Modify information about a student borrower in the system (NOTE: Only “Administrator” workers can mark a student borrower as “delinquent”, or remove the “delinquency” mark)
vi.	Delete a student borrower from the system
vii.	Add a worker to the system (this functionality should include the feature to set the worker’s credentials – “Ordinary” or “Administrator”)
viii.	Modify information about a worker in the system (this should allow the credentials to be changed – from “Administrator” to “Ordinary” and vice versa)
ix.	Delete a worker from the system
x.	Check out (i.e, rent out) a book
xi.	Check in (i.e., return) a book
xii.	Run delinquency check – this will go over all books that are currently “late” and mark their associated borrower’s account as “delinquent”
xiii.	List all books that are currently checked out
xiv.	List all books that are currently available for check out
xv.	List all student borrowers who have books checked out

These are the overall requirements – details of additional functionality will be provided later by your instructor.
 
DATA MODEL FOR THE EOP LIBRARY SYSTEM
Rolodex: StudentBorrower
Fields:
1.	BannerId (Text) (PK)
2.	First Name (Text)
3.	Last Name (Text)
4.	Contact Phone (Text)
5.	E-mail (Text)
6.	BorrowerStatus (Delinquent/Good Standing) (i.e., Blocked from borrowing, or not – Default: “Good Standing”)
7.	DateOfLatestBorrowerStatus (Text)
8.	DateOfRegistration (Text)
9.	Notes (Text)
10.	Status (Active/Inactive – Default: “Active”)

Rolodex: Book
Fields:
1.	Barcode (Text) (PK)
2.	Title (Text)
3.	Discipline (None/English/Mathematics/Physics/…. – Default: “None”)
4.	Author1 (Text)
5.	Author2 (Text)
6.	Author3 (Text)
7.	Author4 (Text)
8.	Publisher (Text)
9.	YearOfPublication (Integer)
10.	ISBN (Text)
11.	Condition (Good/Damaged – Default: “Good”)
12.	Suggested Price (Text)
13.	Notes (Text)
14.	Status (Active/Inactive – Default: “Active”)

Rolodex: BookBarcodePrefix
Fields:
1.	PrefixValue (Text) (PK)
2.	Discipline (None/English/Mathematics/Physics/….) (Unique)


Rolodex: Worker

Fields:

1.	BannerId (Text) (PK)
2.	Password (Text)
3.	First Name (Text)
4.	Last Name (Text)
5.	Contact Phone (Text)
6.	E-mail (Text)
7.	Credentials (Administrator/Ordinary – Default: “Ordinary”)
8.	DateOfLatestCredentialsStatus (Text)
9.	DateOfHire (Text)
10.	Status (Active/Inactive – Default: “Active”)

Rolodex: Rental

Fields:

1.	Id (Integer) – Auto-generated PK
2.	BorrowerId (Text) (FK to StudentBorrower)
3.	BookId (Text) (FK to Book)
4.	CheckoutDate (Text)
5.	CheckoutWorkerId (Text) (FK to Worker)
6.	DueDate (Text)
7.	CheckinDate (Text) (Will be NULL or “” for books currently checked out)
8.	CheckinWorkerId (Text) (FK to Worker) (Will be NULL or “” for books currently checked out)

Rolodex: MaxDueDate

Fields:

1.	CurrentMaxDueDate (Text)

