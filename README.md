# How to run

## CLI test (I could've done a better job with the output)
bash test.sh <file1> <file2> ... <fileN>
eg. bash test.sh hello pluraltest tietest

Above expects there to be the filename with 'expected' appended eg. helloexpected

## SBT test

### My unit tests
sbt test

### Print to standard out
sbt "run <any amount of filenames>
eg. sbt "run hello"

It will run the parser on all filenames with files that exist, and print the outputs in order, 
which is maybe not super useful, so I'd probably just do single runs in a loop
