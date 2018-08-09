#!/usr/bin/env bash
mvn -pl frontend exec:exec &
mvn -pl mentionserver exec:java &
