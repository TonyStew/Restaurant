# Restaurant Take Home Assessment
## Assumptions made:
- Table capacity does not change (this would be easy to fix if false).
- I assumed that a table being "blocked for availability" was the same as that table being "reserved".
  - This would be harder to change if false but I couldn't imagine a situation that would require a table to be blocked but also unreserved.

## Class structure:
- [Table](https://github.com/TonyStew/Restaurant/blob/main/RestaurantTracking/src/models/Table.java)
  - Model that holds capacity, reservation status and its own id.
- [Restaurant](https://github.com/TonyStew/Restaurant/blob/main/RestaurantTracking/src/app/Restaurant.java)
  - Effectively a table manager, keeps track of an array of tables and provides method for finding available tables.
- [TableFactory](https://github.com/TonyStew/Restaurant/blob/main/RestaurantTracking/src/factories/TableFactory.java)
  - Very simple factory that creates tables, this is so future changes to the table class don't also require changes to restaurant.
- [RestaurantTest](https://github.com/TonyStew/Restaurant/blob/main/RestaurantTracking/src/tests/RestaurantTest.java)
  - Tests.  Mostly for myself, I know it was written that you wouldn't compile the code.
- [Example](https://github.com/TonyStew/Restaurant/blob/main/RestaurantTracking/src/Example.java)
  - An example run of the program.

## Potential upgrades:
- Provide a `Restaurant` method that takes a `Function<Table, Integer>` as a parameter, allowing users to sort by custom orderings.
  - Such sorting functions could also be provided by the restaurant itself as static objects.

## Docs
(there's additional details is the code and comments)
![alt text](https://github.com/TonyStew/Restaurant/blob/main/TableDocs.png)
![alt-text](https://github.com/TonyStew/Restaurant/blob/main/RestaurantDocs.png)
