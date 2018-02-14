Repetition
==========

The true power of computer science, of course, is in its ability to repeat things. Naturally, regex supports some kind of notion of repetion. Here's an example of one in action:

    String laughingPattern = "lo+l";
    String w = "lol";
    System.out.println(w.matches(laughingPattern)); // true
    w = "loooool";
    System.out.println(w.matches(laughingPattern)); // true
    w = "ll";
    System.out.println(w.matches(laughingPattern)); // false

There's another special regex character! The `+` sign matches _one or more repetitions_ of the character, as shown above. There are some other special characters that work similarly to, but not exactly the same as, the `+` sign. They are:

- `?` matches 1 or 0
- `*` matches 0 or more
- `{n}` matches exactly n
- `{n,m}` matches at least n but not more than m

You can repeat a group of things using parentheses. For example

    String laughingPattern = "l(ol)+"; // specifies you can repeat ol
    String w = "lol";
    System.out.println(w.matches(laughingPattern)); // true
    w = "lololololol";
    System.out.println(w.matches(laughingPattern)); // true
    w = "loooooool";
    System.out.println(w.matches(laughingPattern)); // false

Special Characters and Escaping
-------------------------------

Usually when you include a character in a regex pattern, that character will only match itself. For example, the regex pattern "wug" will match the String "wug" and nothing else. As we saw earlier, however, there are certain special characters that have other functions. `[` and `]` were used to indicate character classes, allowing us to match one of several characters, while `+`,  `?`, and `*` were used to match repetitons. We'd like to now introduce one more common special character: 

- `.` matches any character, except a line terminator

Great! So now we know some special characters in regex. What if we actually wanted to match one of the special characters? What if you wanted to specifically match a String that included a `+`, for example? In this case, you have to _escape_ the character by putting a `\` in front of it.

- `\+` will match exactly the String "+". This technique works for other special characters as well.

Note! This means `\` is _itself_ a special character. So if you wanted to match a string that included `\`, you'd need to include `\\`.

Another note! The `\` is a bit of crazy character, because it's not just a special character in regex; it's a special character in _any_ Java string, regardless of if it's intended to be interpreted as regex. (For example, try `System.out.println("\n");`, and you won't see a `\` appear). Therefore, if you want to have the regex `\+`, you need to escape the `\` in Java so that it can be treated as a normal Java character so that it can be a special character in regex. Hence, to match the string `"+"`, you will actually need the String `"\\+"`, which will be intepreted as the regex `\+`..

Special Characters and Character Classes
----------------------------------------

The characters that are considered special inside of `[]` are _different_ than the characters that are considered special elsewhere. The only special characters inside `[]` are `^`, `-`, and `\`. The `\` is still used for escaping, allowing you to escape `]`, `^`, and `-` (and `\` itself) inside the character class.

Convenient Character Classes
----------------------------

Asside from escaping, the `\` in regex has an additional use. Notice that since there's no reason you'd even want to escape normal characters, such as `w`, you might imagine the sequence `\w` is useless. It's not. Intead, regex uses sequences like these for special meanings:

- `\d` matches any digit, `\D` matches any non-digit
- `\n` matches a new line (but be careful on Windows, where newlines are followed the by the return character, `\r`)
- `\s` matches any whitespace character (space, newline, tab, etc.), `\S` matches any non-whitespace
- `\w` matches any _word_ character (a-z, A-Z, or 0-9), and `\W` matches any non-word character
