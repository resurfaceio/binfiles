# resurfaceio-binfiles
Readers &amp; writers for binary files

This open source Java library implements a fast and efficient file format, which is purpose-built for storing and analyzing API
calls. This binary format is designed for fast queries from [Trino](https://trino.io), including the use of
native Trino/Airlift types. This library handles file versioning, supports incremental flushing and bitmap indexing, and
provides compression based on LZ4 and field-specific dictionaries. All out-of-the-box, without any configuration.

[![CodeFactor](https://www.codefactor.io/repository/github/resurfaceio/binfiles/badge)](https://www.codefactor.io/repository/github/resurfaceio/binfiles)
[![License](https://img.shields.io/github/license/resurfaceio/binfiles)](https://github.com/resurfaceio/binfiles/blob/v3.5.x/LICENSE)
[![Contributing](https://img.shields.io/badge/contributions-welcome-green.svg)](https://github.com/resurfaceio/binfiles/blob/v3.5.x/CONTRIBUTING.md)

## Usage

This library is used by the Resurface database and [custom Trino connector](https://github.com/resurfaceio/trino-connector),
which is also open source.

The test cases included with this project include examples of reading and writing Resurface binary files.

## Why Another Format?

Why is this format needed, when open standards like JSON, CSV, and Avro are popular for hot data, and Iceberg
(and Parquet and Orc) are popular for cold data?

* Iceberg is the best way to store cold data, but it's a batch format and not well suited to hot data. Our file format is
  designed for hot-data storage (including incremental flushing) and for efficient movement of data between Resurface and
  Iceberg formats using Trino.
* It's common to use Avro or CSV or JSON for hot data storage, and Trino has excellent built-in support for these formats.
  But our file format is significantly faster than Avro (the fastest alternative), with minimal CPU usage and the fewest possible
  memory allocations. Avro is designed to cover all kinds of cases, where Resurface is hand-optimized for this one model and for
  Trino-specific types.
* Deciding to use Avro or Iceberg or any other format is only part of the challenge. You still have to implement a schema
  for the data being stored, and this is a significant task. Our file format is built around an opinionated schema
  for API call data, with request/response attributes, dimensional fields, and bitmap indexes.
* Like Avro and Iceberg, our format and implementation is 100% open source, so you aren't locked into any proprietary bits.

## Dependencies

* Java 11
* [airlift/slice](https://github.com/airlift/slice)
* [lz4/lz4-java](https://github.com/lz4/lz4-java)

## Installing with Maven

⚠️ We publish our official binaries on [CloudSmith](https://cloudsmith.com) rather than Maven Central,
because CloudSmith is awesome.

Simply add these sections to `pom.xml` to install:

```xml
<dependency>
    <groupId>io.resurface</groupId>
    <artifactId>resurfaceio-binfiles</artifactId>
    <version>3.5.1</version>
</dependency>
<dependency>
    <groupId>io.airlift</groupId>
    <artifactId>slice</artifactId>
    <version>0.42</version>
</dependency>
<dependency>
    <groupId>org.lz4</groupId>
    <artifactId>lz4-java</artifactId>
    <version>1.8.0</version>
</dependency>
```

```xml
<repositories>
    <repository>
        <id>resurfacelabs-public</id>
        <url>https://dl.cloudsmith.io/public/resurfacelabs/public/maven/</url>
        <releases>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </releases>
    </repository>
</repositories>
```

---
<small>&copy; 2016-2022 <a href="https://resurface.io">Resurface Labs Inc.</a></small>
