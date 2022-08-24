for var in "$@"
do
    echo "Testing file $var"
    INPUT=$(cat $var)
    printf "Input is : \n%s\n" "$INPUT"
    RESULT=$(sbt --error "run $var" &)
    printf "Result is : \n%s\n" "$RESULT"
    EXPECTED=$(cat "${var}expected")
    printf "Expected is : \n%s\n" "$EXPECTED"
    CHECK=$(diff <(echo "$RESULT") <(echo "$EXPECTED") |xargs)
    if [ -z "$CHECK" ]
    then
        echo "File $var matched the expected output"
    else
        echo "File $var didn't match the expected output, check diff below"
        echo "$CHECK"
    fi
done
#CHECK=$(diff <(cat expectedhello.txt) <(sbt --error "run hello.txt"))
#cat expectedhello.txt
#echo "$(sbt --error "run hello.txt")"
