```java
class StringAnalyzer {
    private String text;

    public StringAnalyzer(String text) {
        this.text = text;
    }

    public int countVowels() {
        int c = 0;
        for (char ch : text.toCharArray()) {
            if ("aeiouyAEIOUY".indexOf(ch) >= 0) c++;
        }
        return c;
    }

    public int countDigits() {
        int c = 0;
        for (char ch : text.toCharArray()) {
            if (ch >= '0' && ch <= '9') c++;
        }
        return c;
    }

    public int countUppercase() {
        int c = 0;
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') c++;
        }
        return c;
    }
}
```
