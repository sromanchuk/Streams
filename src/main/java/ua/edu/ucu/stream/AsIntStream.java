package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import java.util.ArrayList;
import java.util.Arrays;


public class AsIntStream implements IntStream {

    private ArrayList<Integer> stream;

    private AsIntStream(int... values) {
        this.stream = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            this.stream.add(values[i]);
        }
    }

    private AsIntStream(ArrayList<Integer> stream) {
        this.stream = stream;
    }

    public static IntStream of(int... values) {
        IntStream intStream = new AsIntStream(values);
        return intStream;
    }

    @Override
    public Double average() {
        Double count = (double) this.count();
        return this.sum() / count;
    }

    @Override
    public Integer max() {
        Integer max = Integer.MIN_VALUE;
        for (Integer val : this.stream) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        Integer min = Integer.MAX_VALUE;
        for (Integer val : this.stream) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    @Override
    public long count() {
        return this.stream.size();
    }

    @Override
    public Integer sum() {
        Integer sum = 0;
        for (Integer val : this.stream) {
            sum += val;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        int[] arr = this.toArray();
        int[] res = new int[arr.length];
        //перетворюєм потік на масив
        int k = 0;
        //індексація результ масиву
        for (int i = 0; i < arr.length; i++) {
            if (predicate.test(arr[i])) {

                res[k] = arr[i];
                k++;
            }
        }
        return AsIntStream.of(Arrays.copyOf(res, k));
        //відрізаєм непотрібне
    }

    @Override
    public void forEach(IntConsumer action) {
        int[] arr = this.toArray();
        for (int i = 0; i < arr.length; i++) {
            action.accept(arr[i]);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        int[] arr = this.toArray();
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            res[i] = mapper.apply(arr[i]);
        }

        return AsIntStream.of(res);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        int[] arr = this.toArray();
        int len = func.applyAsIntStream(arr[0]).toArray().length;
        int[] preRes = new int[len];
        int[] res = new int[arr.length * len];
        for (int i = 0; i < arr.length; i++) {
            preRes = func.applyAsIntStream(arr[i]).toArray();
            for (int j = 0; j < preRes.length; j++) {
                res[len * i + j] = preRes[j];
            }
        }

        return AsIntStream.of(res);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int[] arr = this.toArray();
        int result = identity;
        for (int i = 0; i < arr.length; i++) {
            result = op.apply(result, arr[i]);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] arr = new int[this.stream.size()];
        for (int i = 0; i < this.stream.size(); i++) {
            arr[i] = this.stream.get(i);
        }
        return arr;
    }
}
