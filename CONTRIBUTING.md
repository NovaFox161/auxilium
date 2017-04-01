# Contributing
I'm very glad you want to help contribute code to Auxilium, to have maximum consitency I'm going to need to have you follow some guidelines though.

# Guidelines

  - I use GoogleStyle for my code, please use it.
  - If you end up overhauling multiple things please make a seperate PR for each. Things like "minor fixes" can be all in one.


# PR or Issue?
I think the folks over at [Discord4J](https://github.com/austinv11/Discord4J) say this amazingly, so I'm going to quote them.

> So if you are trying to decide on whether you should create an issue or create a pull request, ask yourself if it is worth making a pull request for. For example, making a pull request for fixing a single typo may not be better than just submitting an issue. That being said, a pull request fixing very many typos may be better for me than an issue. This is all subjective but you should take this into consideration.


# APIs
Auxilium uses a number of open source projects to work properly:

* [Discord4J](https://github.com/austinv11/Discord4J) - Java wrapper for the Discord API.
* [Json.simple](https://code.google.com/archive/p/json-simple/) - Json Parser // Getting rid of soon
* [GSON](https://github.com/google/gson) - For pretty printing and DB
* [JWKTL](https://dkpro.github.io/dkpro-jwktl/) - For the `define` Command

Please make sure you're testing with said APIs

