# 🚀 Cache-Aside (Lazy Loading) Pattern with Redis in Java/Spring

## Table of Contents
1. [🌟 Introduction](#-introduction)
2. [🧩 What is the Cache-Aside Pattern?](#-what-is-the-cache-aside-pattern)
3. [🔄 Step-by-Step Flow](#-step-by-step-flow)
4. [💡 Code Example with @Async](#-code-example-with-async)
5. [💡 Code Example with CompletableFuture (No @Async)](#-code-example-with-completablefuture-no-async)
6. [💡 Code Example Using Only Optional (No ifs)](#-code-example-using-only-optional-no-ifs)
7. [🤔 CompletableFuture vs @Async: What’s the Difference?](#-completablefuture-vs-async-whats-the-difference)
8. [⚠️ Best Practices](#-best-practices)
9. [📚 References](#-references)

---

## 🌟 Introduction

The Cache-Aside (also known as Lazy Loading) Pattern is a widely used approach to optimize data retrieval in high-performance applications. It reduces database load and improves response times by leveraging Redis as a fast, in-memory cache.

---

## 🧩 What is the Cache-Aside Pattern?
- Check if the data exists in Redis cache
- If found, return the cached data
- If not found, fetch from the database
- Asynchronously update Redis with the new data using Spring's `@Async`

---

## 🔄 Step-by-Step Flow
1. 🔍 Try to get data from Redis
2. ❌ If not present, query the database
3. ✅ Return the data to the user
4. ⚡ Asynchronously store the data in Redis for future requests

---

## 💡 Code Example with @Async

```java
@Service
public class ProductService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CacheUpdateService cacheUpdateService;
    
    public Product getProductById(String id) {
        Product product = redisTemplate.opsForValue().get(id);
        if (product != null) {
            return product; // 🎯 Cache hit
        }
        product = productRepository.findById(id).orElse(null); // 🗄️ DB lookup
        if (product != null) {
            // 🧵 Async cache update
            cacheUpdateService.updateCacheAsync(id, product);
        }
        return product;
    }
}

@Service
public class CacheUpdateService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    @Async
    public void updateCacheAsync(String id, Product product) {
        redisTemplate.opsForValue().set(id, product);
    }
}
```

> 💡 Note: To enable `@Async`, annotate a configuration class with `@EnableAsync`.

---

## 💡 Code Example with CompletableFuture (No @Async)

```java
@Service
public class ProductService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(String id) {
        Product product = redisTemplate.opsForValue().get(id);
        if (product != null) {
            return product; // 🎯 Cache hit
        }
        product = productRepository.findById(id).orElse(null); // 🗄️ DB lookup
        if (product != null) {
            // ⚡ Async cache update using CompletableFuture
            CompletableFuture.runAsync(() -> redisTemplate.opsForValue().set(id, product));
        }
        return product;
    }
}
```

> 💡 Note: `CompletableFuture.runAsync` uses the common ForkJoinPool by default. For production, consider providing a custom Executor for better control.

---

## 💡 Code Example Using Only Optional (No ifs)

```java
@Service
public class ProductService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(String id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(id))
            .or(() -> productRepository.findById(id)
                .map(product -> {
                    // ⚡ Async cache update using CompletableFuture
                    CompletableFuture.runAsync(() -> redisTemplate.opsForValue().set(id, product));
                    return product;
                })
            )
            .orElse(null);
    }
}
```

> 💡 Note: This approach leverages Optional's or() and map() to avoid explicit if statements, making the code more functional and concise.

---

## ⚠️ Best Practices
- Set appropriate TTL (Time To Live) for cache entries
- Handle cache failures gracefully
- Use async updates to avoid blocking main thread
- Monitor cache hit/miss rates
- Ensure `@EnableAsync` is present in your configuration

---

## 📚 References
- [Spring Data Redis Documentation](https://docs.spring.io/spring-data/redis/docs/current/reference/html/)
- [Spring @Async Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduling-annotation-support-async)
- [Redis Official Documentation](https://redis.io/docs/)

---

## 🤔 CompletableFuture vs @Async: What’s the Difference?

|                | @Async (Spring)                                   | CompletableFuture (Java)                |
|----------------|---------------------------------------------------|-----------------------------------------|
| Usage          | Annotation-based (`@Async` on Spring beans)        | Java API, no annotation needed          |
| Management     | Managed by Spring, uses TaskExecutor               | You manage thread pool/executor         |
| Integration    | Works with Spring DI/lifecycle                     | Works anywhere in Java                  |
| Return Types   | `Future`, `CompletableFuture`, or `void`           | `CompletableFuture`                     |
| Chaining       | Limited chaining                                   | Powerful async chaining/composition     |
| Error Handling | Spring handles uncaught exceptions                 | You handle exceptions                   |
| Setup          | Requires `@EnableAsync`                            | No special setup                        |
| Use Case       | Simple async in Spring beans/services              | Advanced async flows, more control      |

---

## 📝 Summary
- Use `@Async` for simple, Spring-integrated async tasks in beans/services.
- Use `CompletableFuture` for advanced async flows, custom thread management, or when you need more control and are not limited to Spring beans.

---

Happy caching! ⚡

[⬅️ Back](./README.md)
