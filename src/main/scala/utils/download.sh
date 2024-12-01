#!/bin/bash

DAY=$( date +"%d" | sed 's/^0*//' )
mkdir -p "/Users/bradley.king/dev/bracl/aoc2024/src/main/scala/day$DAY"

#DAY="    "
echo "Pulling input for day $DAY"
OUTPUT="/Users/bradley.king/dev/bracl/aoc2024/src/main/scala/day$DAY/in.txt"
echo "Writing to file: $OUTPUT"

URL="https://adventofcode.com/2024/day/$DAY/input"
SESSION="53616c7465645f5fb02e46def58ace0689eb54bf0e6280815c4217cee44f2cbc4aa3204b25403dedbc22f95233f8828012792699abc0dcaec3d557a3919c90e1"

curl -A "@bracl via curl" --location --request GET "$URL" --header "Cookie: session=$SESSION" -o "$OUTPUT"
echo "Happy Solving :)"


DESTINATION="/Users/bradley.king/dev/bracl/aoc2024/src/main/scala/day$DAY/day$DAY.scala"
cp "/Users/bradley.king/dev/bracl/aoc2024/src/main/scala/utils/template" "$DESTINATION"
sed -i.bak s/DAY/$DAY/g "$DESTINATION"
sed -i.bak s/utils$/day$DAY/g "$DESTINATION"
rm $DESTINATION.bak

