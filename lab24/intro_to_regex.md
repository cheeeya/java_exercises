Intro to Regex
==============

In order to solve the puzzles today, you'll be learning to work with _regular expressions_, often abbreviated as _regex_. Regular expressions are a special kind of syntax that allow easy searching of long documents for Strings that match special patterns. Most general purpose programming languages you come across will allow you to use regex, so rest assured that what you learn today will not only apply to Java. Regex is an essential tool for any programmer!

Checking if a String Matches a Pattern
--------------------------------------

The simplest way we can use regex is to check if a String matches a _pattern_. The String class has a method for this. An example demonstrates:

    String wugPattern = "wug";
    String w = "wug";
    System.out.println(w.matches(wugPattern)); // prints true
    w = "love";
    System.out.println(w.matches(wugPattern)); // prints false

If this is all we could do with regex, it would be pretty useless. Luckily, regex patterns can be more general than just specific sequences of characters.

Character Classes
-----------------

In the previous example, we asked if a String matched a sequence of characters. We can add to this, and ask if a String matches a sequences of characters and _character classes_. The fastest way to explain a character class is probably just to show it.

    String pattern = "w[aeiouy]g";
    String w = "wug";
    System.out.println(w.matches(pattern)); // prints true
    w = "wog";
    System.out.println(w.matches(pattern)); // also true!
    w = "waeg";
    System.out.println(w.matches(pattern)); // false

What's going on? The String "wug" doesn't look anything like the String "w[aeiouy]g"! That's because square brackets are a special symbol in regex. A string will match a regex pattern if it matches any one character inside the brackets. You can think of it as a big OR symbol. This pattern is saying: first match exactly one `w`, then match exactly one of `a`, `e`, `i`, `o`, `u`, or `y`, then match exactly one `g`.

What if you wanted to match all the letters of the alphabet, a - z? You could do:

    String pattern = "[abcdefghijklmnopqrstuvwxyz]ug";
    String w = "hug";
    System.out.println(w.matches(pattern)); // prints true
 
 This seems a little annoying. Luckily, regex has a shortcut for us:

    String pattern = "[a-z]ug";
    String w = "hug";
    System.out.println(w.matches(pattern)); // prints true

Yes, `[a-z]` will match all the characters between a and z. `[A-Z]` will match the capitals. This works for numbers too. `[0-9]` will match all the digits.

Not
---

If you include a `^` at the beginning of a character class, you will match any charater that's _not_ in the class. For example, the pattern `w[^aeiouy]g`will match `whg`, `wng`, `wvg`, etc., but not `wug`, `wig`, `wog`, etc.
