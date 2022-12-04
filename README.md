# AoC2022

This year in Scala

Will I break my negative record of last year and do even less than 3 days?

Lack of time is real

## Day 1

Easy once you chunk the input

I never remember how to functionally `chunkBy`

## Day 2

Still an easy-ish one, but I think it could be made better.

I like scoped-expression vals, very cool with the unzip I had to made
but probably a case class would have been better?

Domain modeling is okish, companion object is cool but type system
is better in F#

## Day 3

Nice and easy problem solved with `Set`s and `Range`s

It took me a long time because I didn't spot the error in the
string reduce(intersect) which at some time outputted a strange `"TT"`
that had to be re-made into a Set again 🫡

## Day 4

Record broken! Because all the problems were easy and it actually took
me more than GPT-3 to solve them. But you can't take a beer with GPT-3,
you can with me, that's why I'm better.

Sets and Ranges again, they're very powerful but I think I desire
more powerful pattern match for strings.

Doing something like

```scala
val s = "3-4,5-7"

s match {
  case "{a}-{b},{c}-{d}" => ((a to b), (c to d))
}
```

could be extremely powerful and easy. I don't remember where I saw this
feature!
