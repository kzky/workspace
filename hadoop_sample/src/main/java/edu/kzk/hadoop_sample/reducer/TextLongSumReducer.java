package edu.kzk.hadoop_sample.reducer;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TextLongSumReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Context context)
			throws IOException, InterruptedException {
		long sum = 0;
		for (LongWritable value : values) {
			sum += value.get();
		}
		context.write(key, new LongWritable(sum));
	}

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		
	}
	
}
