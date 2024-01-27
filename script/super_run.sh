file_name="super_output"
max_array=(100000000 1000000000)
n_array=(2 5 10 25 50 100 250 500 1000)

function run_program() {
  java src.Run "$2" "$3" "$4" >> "$1"
  printf "Progress: %3.2f%%\r" $((10000 * $2 / $4))e-2
}

function greater() {
  return $(($1 > $2 ? $1 : $2))
}

for max in "${max_array[@]}"
  do
  RANDOM=123456789 # Seed for random number generation
  expanded_file_name="${file_name}_with_${max}_elements.csv"
  echo "\"Number of Partitions\",\"Number of Elements\",Index,Found,\"Number of Iterations\",\"Time Took in Microseconds\",Language" > $expanded_file_name
  for n in "${n_array[@]}"
    do
    echo "Started for n = $n and max = $max"
    # For first element (un-matched case)
    run_program "$expanded_file_name" -1 "$n" "$max"
    # Run for random of all existing elements
    greater $(((max+1)/100000)) 1 # 1st parameter is the increment calculated to evaluate 1 Lakh elements
    increment=$?
    for i in `seq 0 $increment $((max+1))`
      do
      temp=$((RANDOM%increment+i)) # Find the next random number, for current window (same number in case of 1)
      run_program "$expanded_file_name" "$temp" "$n" "$max"
    done
    # For last element (un-matched case)
    run_program "$expanded_file_name" "$((max+1))" "$n" "$max"
    echo "Completed for n = $n and max = $max"
  done
done
