package org.kzk.kuromojiSample.sample;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.analysis.ja.tokenattributes.BaseFormAttribute;
import org.apache.lucene.analysis.ja.tokenattributes.InflectionAttribute;
import org.apache.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute;
import org.apache.lucene.analysis.ja.tokenattributes.ReadingAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

public class Test {

	public static void main(String[] args) {

		try{
			JapaneseAnalyzer t = new JapaneseAnalyzer(Version.LUCENE_40);
			TokenStream ts = t.tokenStream("txt", new StringReader("今日はカレーを食べました"));

			int count = 0;
			while (ts.incrementToken()) {

				CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
				BaseFormAttribute base = ts.getAttribute(BaseFormAttribute.class);
				PartOfSpeechAttribute pos = ts.getAttribute(PartOfSpeechAttribute.class);
				ReadingAttribute read = ts.getAttribute(ReadingAttribute.class);
				InflectionAttribute inflect = ts.getAttribute(InflectionAttribute.class);
				
				System.out.println("##--- " + count + " ---##");
				System.out.println("term\t" + term);
				System.out.println("base\t" + base.getBaseForm());
				System.out.println("pos\t" + pos.getPartOfSpeech());
				System.out.println("read\t" + read.getReading());
				System.out.println("inflection\t" + inflect.getInflectionForm());
				System.out.println();
				count++;
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
