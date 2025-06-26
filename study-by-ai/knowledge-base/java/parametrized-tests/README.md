# 🧪 Parameterized Tests with JUnit and Mockito

## 📑 Index
1. [📖 Introduction](#-introduction)
2. [⚙️ Project Setup](#️-project-setup)
3. [🧪 Test Scenarios](#-test-scenarios)
4. [💻 Implementation](#-implementation)
   - [System Classes](#1-️-system-classes)
   - [Parameterized Tests](#2-parameterized-tests)
   - [Using @CsvFileSource](#3-using-csvfilesource)
   - [Testing Exceptions](#4-testing-exceptions)
5. [✨ Best Practices](#-best-practices)
6. [🎉 Conclusion](#-conclusion)
7. [📚 Additional Resources](#-additional-resources)

## 🎯 Introduction

This tutorial demonstrates how to use parameterized tests with JUnit 5 and Mockito in a credit card payment system.

## ⚙️ Project Setup

> ⚠️ **Important**  
> Make sure to add all required dependencies.

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🧪 Test Scenarios

We will implement tests for the following scenarios:

1. ✅ **Credit Card Validation**
   - Valid/Invalid card number
   - Valid/Invalid expiration date
   - Valid/Invalid CVV

2. 💳 **Payment Processing**
   - ✅ Approved payment
   - ❌ Payment declined due to insufficient funds
   - ❌ Payment declined due to blocked card
   - ⚠️ Payment declined due to fraud

## 💻 Implementation

### 1. 🏗️ System Classes

First, let's create the main classes of our system:

```java
public record CreditCard(
    String number,
    String expirationDate,
    String cvv
) {}

public record PaymentRequest(
    CreditCard creditCard,
    BigDecimal amount,
    String currency
) {}

public record PaymentResponse(
    boolean success,
    String message,
    String transactionId
) {}

public enum PaymentStatus {
    APPROVED,
    INSUFFICIENT_FUNDS,
    CARD_BLOCKED,
    FRAUD_SUSPECT,
    INVALID_CARD
}
```

### 2. 🧪 Parameterized Tests

Here are examples of parameterized tests for different scenarios:

```java
@ExtendWith(MockitoExtension.class)
class CreditCardPaymentTest {

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private PaymentService paymentService;

    @ParameterizedTest
    @CsvSource({
        "4111111111111111, 12/2025, 123, true",
        "4111111111111112, 12/2025, 123, false",
        "4111111111111111, 12/2020, 123, false",
        "4111111111111111, 12/2025, 12, false"
    })
    void shouldValidateCreditCard(String number, String expiration, String cvv, boolean expected) {
        CreditCard card = new CreditCard(number, expiration, cvv);
        boolean result = paymentService.validateCard(card);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("paymentScenarios")
    void shouldProcessPaymentWithDifferentScenarios(
            PaymentRequest request,
            PaymentStatus expectedStatus,
            String expectedMessage
    ) {
        when(paymentGateway.process(request)).thenReturn(new PaymentResponse(
            expectedStatus == PaymentStatus.APPROVED,
            expectedMessage,
            UUID.randomUUID().toString()
        ));

        PaymentResponse response = paymentService.processPayment(request);
        assertEquals(expectedStatus == PaymentStatus.APPROVED, response.success());
        assertEquals(expectedMessage, response.message());
    }

    static Stream<Arguments> paymentScenarios() {
        return Stream.of(
            // Success scenarios
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("100.00"),
                    "USD"
                ),
                PaymentStatus.APPROVED,
                "Payment processed successfully"
            ),
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("5555555555554444", "12/2025", "123"),
                    new BigDecimal("500.00"),
                    "EUR"
                ),
                PaymentStatus.APPROVED,
                "Payment processed successfully"
            ),
            
            // Failure - Insufficient Funds
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("10000.00"),
                    "USD"
                ),
                PaymentStatus.INSUFFICIENT_FUNDS,
                "Insufficient funds"
            ),
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("5555555555554444", "12/2025", "123"),
                    new BigDecimal("50000.00"),
                    "EUR"
                ),
                PaymentStatus.INSUFFICIENT_FUNDS,
                "Insufficient funds"
            ),
            
            // Card Blocked
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("50.00"),
                    "USD"
                ),
                PaymentStatus.CARD_BLOCKED,
                "Card is blocked"
            ),
            
            // Fraud Suspect
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("9999.99"),
                    "BTC"
                ),
                PaymentStatus.FRAUD_SUSPECT,
                "Transaction flagged for fraud"
            ),
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("5555555555554444", "12/2025", "123"),
                    new BigDecimal("5000.00"),
                    "USD"
                ),
                PaymentStatus.FRAUD_SUSPECT,
                "Unusual transaction pattern detected"
            ),
            
            // Different Currencies
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("1000.00"),
                    "JPY"
                ),
                PaymentStatus.APPROVED,
                "Payment processed successfully"
            ),
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("5555555555554444", "12/2025", "123"),
                    new BigDecimal("750.00"),
                    "GBP"
                ),
                PaymentStatus.APPROVED,
                "Payment processed successfully"
            ),
            
            // Edge Values
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("4111111111111111", "12/2025", "123"),
                    new BigDecimal("0.01"),
                    "USD"
                ),
                PaymentStatus.APPROVED,
                "Payment processed successfully"
            ),
            Arguments.of(
                new PaymentRequest(
                    new CreditCard("5555555555554444", "12/2025", "123"),
                    new BigDecimal("99999.99"),
                    "USD"
                ),
                PaymentStatus.FRAUD_SUSPECT,
                "Amount exceeds maximum limit"
            )
        );
    }
}
```

### 3. 📂 Using @CsvFileSource

You can also keep your test data in a CSV file:

```java
@ParameterizedTest
@CsvFileSource(resources = "/payment-scenarios.csv", numLinesToSkip = 1)
void shouldProcessPaymentFromCsvFile(
    String cardNumber,
    String expiration,
    String cvv,
    BigDecimal amount,
    String currency,
    PaymentStatus expectedStatus
) {
    PaymentRequest request = new PaymentRequest(
        new CreditCard(cardNumber, expiration, cvv),
        amount,
        currency
    );
    
    // ... mock setup and assertions
}
```

Contents of `payment-scenarios.csv`:
```csv
cardNumber,expiration,cvv,amount,currency,expectedStatus
4111111111111111,12/2025,123,100.00,USD,APPROVED
4111111111111111,12/2025,123,10000.00,USD,INSUFFICIENT_FUNDS
4111111111111111,12/2025,123,50.00,USD,CARD_BLOCKED
```

### 4. ❗ Testing Exceptions

```java
@ParameterizedTest
@ValueSource(strings = {
    "12/2020",  // Expired
    "13/2025",  // Invalid month
    "00/2025",  // Invalid month
    "12/2099"   // Year too far in the future
})
void shouldThrowExceptionForInvalidExpirationDate(String expirationDate) {
    CreditCard card = new CreditCard("4111111111111111", expirationDate, "123");
    
    assertThrows(InvalidCardException.class, () -> 
        paymentService.validateCard(card)
    );
}
```

## ✨ Best Practices

1. **🏗️ Test Organization**
   - 📁 Keep test data in separate files
   - 📝 Use descriptive method names
   - 🔍 Group related scenarios

2. **🎭 Mocking**
   - 🎯 Mock only what's necessary
   - ✅ Verify important interactions
   - 🔍 Use ArgumentCaptor to validate complex arguments

3. **📊 Test Data**
   - 💡 Use realistic data
   - 🎯 Cover edge cases
   - ✅ Include positive and negative scenarios

## 🎉 Conclusion

Parameterized tests are a powerful tool for testing multiple scenarios in an organized and efficient way.

## 📚 Additional Resources

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
