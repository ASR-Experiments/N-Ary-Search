file_name="output.csv"
echo "\"Number of Partitions\",\"Number of Elements\",Index,Found,\"Number of Iterations\",\"Time Took in Microseconds\",Language" > $file_name
max_array=(1 10 100 1000 10000 100000)
n_array=(2 5 10 50 100 200 500 1000)
for max in "${max_array[@]}"
  do
    for n in "${n_array[@]}"
      do
      echo "Started for n = $n and max = $max"
      for i in `seq -1 $((max+1))`
        do
        java src.Run "$1" "$n" "$max" >> $file_name
        printf "Progress: %3.2f%%\r" $((10000 * i/max))e-2
      done
      echo "Success for n = $n and max = $max"
    done
done