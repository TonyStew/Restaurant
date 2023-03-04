Assumptions made:
- Table capacity does not change (this would be easy to change if false)
- I assumed that a table being "blocked for availability" was the same as that table being "reserved"
  - This would be harder to change if false but I couldn't imagine a situation that would require a table to be blocked but also unreserved

Class structure:
- Table
  - Model that holds capacity, reservation status and its own id
- Restaurant
  - Effectively a table manager, keeps track of an array of tables and provides method for finding available tables
- TableFactory
  - Very simple factory that creates tables, this is so future changes to the table class don't also require changes to restaurant
- RestaurantTest
  - Tests.  Mostly for myself, I know it was written that you wouldn't compile the code.
