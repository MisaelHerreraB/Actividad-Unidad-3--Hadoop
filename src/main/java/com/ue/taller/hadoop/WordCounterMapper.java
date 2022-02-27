package com.ue.taller.hadoop;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private final static Pattern SPLIT_PATTERN = Pattern.compile("\\s*\\b\\s*");
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) 
			throws IOException, InterruptedException{
		
		String line = value.toString();
		line = line.replaceAll("[^A-Z ]", " ").toLowerCase();
		Text currentWord = new Text();
		
		String workd[] = SPLIT_PATTERN.split(line);
		
		for(int i= 0 ; i<workd.length;i++) {
			if(workd[i].isEmpty()) {
				continue;
			}
			currentWord = new Text(workd[i]);
			context.write(currentWord, one);
		}
	}
	
}
