package com.ky.luceneSearcherSample.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearcherMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// indexReader生成 
		Directory indexDir = null;
		if (args.length == 1) {
			
			try {
				indexDir = FSDirectory.open(new File(args[0]));			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else {
			System.out.println("input indexed directory as args[0]");
			return;
		}

		// indexSearcherの生成
		IndexReader indexReader = null;
		try {
			indexReader = DirectoryReader.open(indexDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		// Analyzerの生成
		JapaneseAnalyzer japaneseAnalyzer = new JapaneseAnalyzer(
				Version.LUCENE_40);
		
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("The number of Doc is " +  indexReader.numDocs());
			System.out.println("input sentence.");
			
			String line = null;
			try {
				line = sin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (line.equals(null)) break;
				
			// Queryの生成
			Query titlesQuery= null;
			Query contentsQuery= null;
			
			String titlesField= "titles";
			String contensField= "contents";
			try {
				titlesQuery= new QueryParser(Version.LUCENE_40, titlesField, japaneseAnalyzer).parse(line); 
				contentsQuery= new QueryParser(Version.LUCENE_40, contensField, japaneseAnalyzer).parse(line); 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BooleanQuery bquery = new BooleanQuery();
			bquery.add(titlesQuery, BooleanClause.Occur.SHOULD);
			bquery.add(contentsQuery, BooleanClause.Occur.SHOULD);
			
			// Search
			int nhits = 25;
			TopScoreDocCollector collector = TopScoreDocCollector.create(nhits, true);
			
			try {
				indexSearcher.search(bquery, collector);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			
			// Display
			for (ScoreDoc hit : hits) {
				int docId = hit.doc;

				try {
					Document doc = indexSearcher.doc(docId);
					System.out.println(doc.get("id") + ": " + doc.get("titles"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}
}
