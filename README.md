# resurfaceio-binfiles
Readers &amp; writers for binary files

## System requirements

* Java 11
* Maven

## Running read benchmark
```
java -Xmx192M -classpath ./target/classes:./lib/fastutil-8.3.0.jar io.resurface.binfiles.ReadBenchmark $HOME/Downloads/message.blk
```
