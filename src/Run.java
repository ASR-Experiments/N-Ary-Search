package src;

import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Run {
    @SuppressWarnings("S128")
    static class Parameters {
        int element;
        int n;
        int last;
        int first;

        public Parameters(String[] args) {
            this.first = 0;
            this.last = 1000000;
            this.n = 2;
            switch (args.length) {
                case 4:
                    this.first = Integer.parseInt(args[3].trim());
                case 3:
                    this.last = Integer.parseInt(args[2].trim());
                case 2:
                    this.n = Integer.parseInt(args[0].trim());
                case 1:
                    this.element = Integer.parseInt(args[0].trim());
                    break;
                default:
                    throw new IllegalArgumentException("Number of expected parameters are between 1 and 4, received " + args.length);
            }
        }
    }

    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        Integer[] array = IntStream.range(parameters.first, parameters.last)
                .boxed()
                .toArray(Integer[]::new);

        long start = System.nanoTime();
        Search.Solution result = Search.search(array, parameters.element, parameters.n);
        long end = System.nanoTime();
        double timeTookInMs = (end - start) / 1000.0;
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(String.valueOf(parameters.n))
                .add(String.valueOf(array.length))
                .add(String.valueOf(parameters.element))
                .add(String.valueOf(result.index() != Search.FALLBACK_VALUE))
                .add(String.valueOf(result.depth()))
                .add(String.valueOf(timeTookInMs))
                .add("Java");
        System.out.printf("%s%n", joiner);
    }
}