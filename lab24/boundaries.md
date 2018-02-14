Find and Replace
================

A natural extention of finding is find-and-replace. The `Matcher` class has two methods, `replaceAll` and `replaceFirst` that might be helpful. For example, the following makes a famous quote gender-neutral:

    Pattern malePattern = Pattern.compile("men");
	Matcher neutralizer = malePattern
		        .matcher("We hold these truths to be self-evident, that all men are created equal...");
	System.out.println(neutralizer.replaceAll("people"));
    
However, this regex doesn't do what we want in the following case:

    Matcher neutralizer2 = malePattern
		    	.matcher("We hold these truth to be self-evident, that all men are equal mentally...");

It prints:

    We hold these truth to be self-evident, that all people are equal peopletally...

That's right, it replaced the `men` in `mentally`, too! That's not what we wanted. How do we fix this problem?

Boundaries
==========

We introduce the concept of _boundaries_. Boundaries help us specify that we only want to match patterns within certain boundary conditions. Here are some useful boundaries we can use in regex:

- `^` matches the beginning of a line
- `$` matches the end of a line
- `\b` matches a _word boundary_. Word boundaries occur at the ends of sequence of word characters. Recall the word character from earlier.
- `\B` matches a non-word boundary.

In this case, we probably only want to match the _word_ `men`, so we should demarcate it with word boundaries. Here's the regex we want:

    Pattern malePattern = Pattern.compile("\\bmen\\b");

Again, we want to treat the `\` as a normal character, not a special character, so we must escape it.
