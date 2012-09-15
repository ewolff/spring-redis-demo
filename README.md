Spring Redis Demo
=================

This is a very simple demo to show what Spring Redis can do.
It contains quite a few JUnit tests to show the main features.
The tests assume a running Redis on localhost on port 6379 - the standard port.
The scenario is a list of high scores for players that should be kept in a database.

Here are some more details about the tests:

- RedisHighScore shows how the highscores are written to the database and how they are automatically sorted.
- RedisWriter and RedisMultiWriter show two ways to write data into the databse. RedisMultiWriter is much faster.
- RedisCreateID shows how a unique ID can be created using Redis
- RedisCache shows how Redis can be used as a cache with time to live
 
 