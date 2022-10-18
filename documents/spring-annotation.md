```java
@AutoConfigureBefore({AAAA.class, BBBB.class})
public class CCCC {
}
说明 CCCC 将会在 AAAA 和BBBB之前加载
```

```java
@AutoConfigureAfter(AAAA.class)
public class CCCC {
}
说明 CCCC 将会在 AAAA 之后加载
```

