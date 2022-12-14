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

## Day 5

The record continues. This year the AoC team probably understood that having
hard walls in the first 3 days is not a good way to go, so it's going nice and slow.

Loving scala so far, everything seems more elegant and my solutions are becoming
more functional, such a great fold today.

## Day 6

Cool problem probably solved in `O(23489234923489234^234894589)` complexity but JVM + M1
are strong enough :D very simple, so far so fun not hitting walls early!

## Day 7

Had to think a bit about this; it was very hard and I had to take suggestions from other
code.

I definitely need to be more fluent at trees and stuff.

My first idea of parsing with case objects was actually right, building
the state tail-recursively is a pain tho. My string solutions failed on folding
back the sizes correctly.

## Day 8

Wow today was super cool and I had a good idea for the algorithm. It just 
took me a lot of time to implement it. I got lost into the folds and the special cases
but in the end it all went well.

## Day 9
