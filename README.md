# dbperf
How to use:
to init creation of random entities use: <br> &nbsp; POST http://host:port/create/{count:int} <br>
to init find by range (random number field) use: <br> &nbsp; GET http://host:port/find?numFrom={numFrom:int}&numTo={numTo:int}&searchCount={count:int} <br>
to init find by value (random number field) use: <br> &nbsp; GET http://host:port/findByNum?num={num:int}&searchCount={count:int} <br>
