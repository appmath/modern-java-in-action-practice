# Common Functions

| Functional Interface   |   Predicate<T>           |  Consumer <T>                                              |
| ---------------------- |:-------------------------|:-----------------------------------------------------------|
| Predicate<T>           |       T -> boolean       | IntPredicate, LongPredicate, DoublePredicate               |           
| Consumer<T>            |       T -> void          | IntConsumer, LongConsumer, DoubleConsumer                  |           
| Function<T, R>         |       T -> R             | IntFunction<R>,                                            |           
|                        |                          | IntToDoubleFunction,                                       |           
|                        |                          | IntToLongFunction,                                         |           
|                        |                          | LongFunction<R>,                                           |           
|                        |                          | LongToDoubleFunction,                                      |           
|                        |                          | LongToIntFunction,                                         |           
|                        |                          | DoubleFunction<R>,                                         |           
|                        |                          | DoubleToIntFunction,                                       |           
|                        |                          | DoubleToLongFunction,                                      |           
|                        |                          | ToIntFunction<T>,                                          |           
|                        |                          | ToDoubleFunction<T>,                                       |           
|                        |                          | ToLongFunction<T>                                          |           
|                        |                          |                                                            |           
| Supplier<T>            |       () -> T            | BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier |          
|                        |                          |                                                            |           
| UnaryOperator<T>       |       T -> T             | IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator   |           
|                        |                          |                                                            |           
| BinaryOperator<T>      |       (T, T) -> T        | IntBinaryOperator,                                         |           
|                        |                          |                                                            |           
| LongBinaryOperator,    |                          |                                                            |           
|                        |                          |                                                            |           
| DoubleBinaryOperator   |                          |                                                            |           
|                        |                          |                                                            |           
| BiPredicate<T, U>      |      (T, U) -> boolean   |                                                            |           
|                        |                          |                                                            |           
| BiConsumer<T, U>       |      (T, U) -> void      | ObjIntConsumer<T>,                                         |           
|                        |                          | ObjLongConsumer<T>,                                        |           
|                        |                          | ObjDoubleConsumer<T>                                       |           
|                        |                          |                                                            |           
| BiFunction<T, U, R>    |      (T, U) -> R         | ToIntBiFunction<T, U>,                                     |           
|                        |                          | ToLongBiFunction<T, U>,                                    |           
|                        |                          | ToDoubleBiFunction<T, U>                                   |           


# Examples of lambdas with functional interfaces
| Use Case                     | Example of lambda                                | Matching functional interface  |
| ---------------------------- |:-------------------------------------------------| :------------------------------|
| A boolean expression	       |  (List<String> list) -> list.isEmpty()	          |  Predicate<List<String>>       |
| Creating objects	           |  () -> new Apple(10)	                          |  Supplier<Apple>               |
| Consuming from an object	   |  (Apple a) -> System.out.println(a.getWeight())  |	 Consumer<Apple>               |
| Select/extract from an object| 	(String s) -> s.length()	                  |  Function<String,              |
|                              |                                                  |  Integer> or                   |
|                              |                                                  |  ToIntFunction<String>         |
| Combine two values	       |  (int a, int b) -> a * b	                      |  IntBinaryOperator             |
| Compare two objects      	   |  (Apple a1, Apple a2) ->                         |  Comparator<Apple> or          |
|                              |    a1.getWeight().compareTo(a2.getWeight ())     |  BiFunction<Apple, Apple,      |
|                              |                                                  |  Integer> or                   |
|                              |                                                  |  ToIntBiFunction<Apple, Apple> |


# Intermediate vs. Terminal operations
## Intermediate
| Operation  | Type            | Return type  | Argument of the operation   | Function descriptor  | 
| -----------|:----------------|:-------------|:----------------------------|:---------------------|  
| filter     | Intermediate    | Stream<T>    | Predicate<T>                | T -> boolean         | 
| map        | Intermediate    | Stream<R>    | Function<T, R>              | T -> R               | 
| limit      | Intermediate    | Stream<T>    |                             |                      | 
| sorted     | Intermediate    | Stream<T>    | Comparator<T>               | (T, T) -> int        | 
| distinct   | Intermediate    | Stream<T>    |                             |                      | 

## Terminal
| Operation  | Type          | Return type  | Purpose                                                                                 |
| -----------|:--------------|:-------------|:----------------------------------------------------------------------------------------|
| forEach    | Terminal      | void         | Consumes each element from a stream and applies a lambda to each of them.               |
| count      | Terminal      | long         | Returns the number of elements in a stream.                                             |
| collect    | Terminal      | (generic)    | Reduces the stream to create a collection such as a List, a Map, or even an Integer.    |


































