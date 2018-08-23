# dbperf
How to use:
to init creation of random entities use: POST http://host:port/create/{count:int}
to init find by range (random number field) use: GET http://host:port/find?numFrom={numFrom:int}&numTo={numTo:int}&searchCount={count:int}
to init find by value (random number field) use: GET http://host:port/findByNum?num={num:int}&searchCount={count:int}
