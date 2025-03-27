```java
import java.util.List;
import java.util.function.Predicate;

// Assume we have an object and a list of predicates
CardEvent cardEvent = /* some card event */;
List<Predicate<CardEvent>> predicates = List.of(
    event -> event.cardEventType() == CardEventType.CARD_REFUND,
    event -> event.amount() > 1000.0,
    event -> event.accountId() > 5000
);

// 1. Check if the object satisfies ANY predicate (OR)
boolean matchesAny = predicates.stream().anyMatch(p -> p.test(cardEvent));

// 2. Check if the object satisfies ALL predicates (AND)
boolean matchesAll = predicates.stream().allMatch(p -> p.test(cardEvent));

// 3. Find the first matching predicate
Optional<Predicate<CardEvent>> firstMatch = predicates.stream()
    .filter(p -> p.test(cardEvent))
    .findFirst();

// 4. Count how many predicates match
long matchCount = predicates.stream().filter(p -> p.test(cardEvent)).count();

// 5. Get all predicates that match
List<Predicate<CardEvent>> matchingPredicates = predicates.stream()
    .filter(p -> p.test(cardEvent))
    .toList();
```

- Creating composite predicates
```java
// Combine all predicates with AND
Predicate<CardEvent> allConditions = predicates.stream()
    .reduce(e -> true, Predicate::and);
    
// Combine all predicates with OR
Predicate<CardEvent> anyCondition = predicates.stream()
    .reduce(e -> false, Predicate::or);

// Then use the composite predicate
boolean matches = allConditions.test(cardEvent);
```