   
                                                                                                                     
## Examples of lambdas with functional interfaces                                                                    
| Use Case                     | Example of lambda                                     | Matching functional interface               | 
| ---------------------------- |:------------------------------------------------------| :-------------------------------------------| 
| A boolean expression	       |  ```(List<String> list) -> list.isEmpty()```	       |  ```Predicate<List<String>>             ``` | 
|                              |                                                       |                                             | 
| Creating objects	           |  ```() -> new Apple(10)```	                           |  ```Supplier<Apple>                     ``` | 
|                              |                                                       |                                             | 
| Consuming from an object	   |  ```(Apple a) -> System.out.println(a.getWeight())``` |  ```Consumer<Apple>                     ``` | 
|                              |                                                       |                                             | 
| Select/extract from an object|  ```(String s) -> s.length()```	                   |  ```Function<String, Integer> or        ``` | 
|                              |                                                       |  ```ToIntFunction<String>               ``` | 
|                              |                                                       |                                             | 
| Combine two values	       |  ```(int a, int b) -> a * b```	                       |  ```IntBinaryOperator                   ``` | 
|                              |                                                       |                                             | 
| Compare two objects      	   |  ```(Apple a1, Apple a2) ->    ```                    |  ```Comparator<Apple> or                ``` | 
|                              |   ```a1.getWeight().compareTo(a2.getWeight ())```     |  ```BiFunction<Apple, Apple, Integer> or``` | 
|                              |                                                       |  ```ToIntBiFunction<Apple, Apple>       ``` | 

                             

## Common Functions (Functional Interfaces (Table 3.2 Modern Java in Action) )

| Functional Interface   |   Predicate<T>           |  Consumer <T>                                                    |
| ---------------------- |:-------------------------|:-----------------------------------------------------------------|
| Predicate<T>           | ```T -> boolean```       | ```IntPredicate, LongPredicate, DoublePredicate              ``` |           
| Consumer<T>            | ```T -> void```          | ```IntConsumer, LongConsumer, DoubleConsumer                 ``` |           
| Function<T, R>         | ```T -> R```             | ```IntFunction<R>,                                           ``` |           
|                        |                          | ```IntToDoubleFunction,                                      ``` |           
|                        |                          | ```IntToLongFunction,                                        ``` |           
|                        |                          | ```LongFunction<R>,                                          ``` |           
|                        |                          | ```LongToDoubleFunction,                                     ``` |           
|                        |                          | ```LongToIntFunction,                                        ``` |           
|                        |                          | ```DoubleFunction<R>,                                        ``` |           
|                        |                          | ```DoubleToIntFunction,                                      ``` |           
|                        |                          | ```DoubleToLongFunction,                                     ``` |           
|                        |                          | ```ToIntFunction<T>,                                         ``` |           
|                        |                          | ```ToDoubleFunction<T>,                                      ``` |           
|                        |                          | ```ToLongFunction<T>                                         ``` |           
|                        |                          |                                                                  |           
| Supplier<T>            | ```() -> T```            | ```BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier``` |          
|                        |                          |                                                                  |           
| UnaryOperator<T>       | ```T -> T```             | ```IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator```   |           
|                        |                          |                                                                  |           
| BinaryOperator<T>      | ```(T, T) -> T```        | ```IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator```|           
|                        |                          |                                                                  |           
| BiPredicate<T, U>      | ```(T, U) -> boolean```  |                                                                  |           
|                        |                          |                                                                  |           
| BiConsumer<T, U>       | ```(T, U) -> void```     | ```ObjIntConsumer<T>,   ```                                      |           
|                        |                          | ```ObjLongConsumer<T>,  ```                                      |           
|                        |                          | ```ObjDoubleConsumer<T> ```                                      |           
|                        |                          |                                                                  |           
| BiFunction<T, U, R>    | ```(T, U) -> R```        | ```ToIntBiFunction<T, U>,   ```                                  |           
|                        |                          | ```ToLongBiFunction<T, U>,  ```                                  |           
|                        |                          | ```ToDoubleBiFunction<T, U> ```                                  |           



## Intermediate vs. Terminal operations
| Operation        | Type                    | Return type        | Type/functional interface used (arg)       | Function descriptor  |
| -----------------|:------------------------|:-------------------|:-------------------------------------------|:---------------------|  
| ```filter   ```  | Intermediate            | ```Stream<T>  ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```distinct ```  | Intermediate            | ```           ```  | ```                       ```              | ```              ``` |
  ```         ```  | (stateful-unbounded)    | ```Stream<R>  ```  | ```                       ```              | ```              ``` | 
| ```takeWhile```  | Intermediate            | ```Stream<T>  ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```dropWhile```  | Intermediate            | ```Stream<T>  ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```skip     ```  | Intermediate            | ```           ```  | ```                       ```              | ```              ``` |
  ```         ```  | (stateful-unbounded)    | ```Stream<R>  ```  | ```                       ```              | ```              ``` | 
| ```limit    ```  | Intermediate            | ```Stream<T>  ```  | ```                       ```              | ```              ``` |                     
| ```map      ```  | Intermediate            | ```Stream<R>  ```  | ```Function<T, R>         ```              | ```T -> R        ``` | 
| ```flatMap  ```  | Intermediate            | ```Stream<R>  ```  | ```Function<T, <Stream<R>>```              | ```T -> Stream<R>``` | 
| ```sorted   ```  | Intermediate            | ```Stream<T>  ```  | ```Comparator<T>          ```              | ```(T, T) -> int ``` | 
| ```anyMatch ```  | Terminal                | ```Stream<T>  ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```noneMatch```  | Terminal                | ```Stream<T>  ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```allMatch ```  | Terminal                | ```boolean    ```  | ```Predicate<T>           ```              | ```T -> boolean  ``` | 
| ```findAny  ```  | Terminal                | ```Optional<T>```  | ```                       ```              | ```T -> boolean  ``` | 
| ```findFirst```  | Terminal                | ```Optional<T>```  | ```                       ```              | ```T -> boolean  ``` | 
| ```forEach  ```  | Terminal                | ```void       ```  | ```Consumer<T>            ```              | ```T -> void     ``` | 
| ```collect  ```  | Terminal                | ```R          ```  | ```Collector<T, A, R>     ```              | ```              ``` |
| ```reduce   ```  | Terminal                | ```void       ```  | ```Consumer<T>            ```              | ```T -> void     ``` |  
| ```         ```  | (stateful-unbounded)    | ```Optional<T>```  | ```BinaryOperator<T>      ```              | ```(T, T) -> T   ``` |
| ```count    ```  | Terminal                | ```long       ```  |                                            |                      |   



































