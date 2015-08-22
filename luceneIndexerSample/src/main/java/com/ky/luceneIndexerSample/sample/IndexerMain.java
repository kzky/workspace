package com.ky.luceneIndexerSample.sample;

import java.io.File;
import java.io.FileReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author kzk
 *
 */
public class IndexerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length != 3) {
			System.out.println("usage: java -jar indexer.jar field1 field2 index");
			return;
		}
		
		// indexに登録するファイルのあるディレクトリ
		File dir1 = new File(args[0]);
		File dir2 = new File(args[1]);

		String files1[] = dir1.list();
		String files2[] = dir2.list();
		
		// indexファイルの保存先
		Directory indexDir = null;
		try {
			indexDir = FSDirectory.open(new File(args[2]));			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// analyzerの生成
		Analyzer analyzer = new JapaneseAnalyzer(Version.LUCENE_40);

		// indexerの生成
		IndexWriterConfig indexWriterconfig = new IndexWriterConfig(
				Version.LUCENE_40, analyzer);
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(indexDir, indexWriterconfig);
			
			// 文章の解析
			int count = 0;
			for (String file : files1) {
				System.out.println(count + ": " + file);
				
				// Documentの生成
				Document doc = new Document();

				// ファイルパスの登録
				doc.add(new StringField("id", file, Store.YES));

				// ファイルの中身の登録
				doc.add(new TextField("contents", 
						new FileReader(args[0] + "/" + file)));
				doc.add(new TextField("titles", 
						new FileReader(args[1] + "/" + file)));

				// un-storedの意味は，中身が保存されない
				// TextField(, , )は保存されると思う

				// indexerにdocumentを登録
				indexWriter.addDocument(doc);
				
				count++;
			}
			
			/*
			 * 同じファイル＆filedを共有しているつもりでも，
			 * Documentを複数生成すると別のドキュメント扱いになってしまう．
			 * ユニークIDをもたせることも可能？ -> solrか？ 
			count = 0;
			for (String file : files2) {
				System.out.println(count + ": " + file);
				
				// Documentの生成
				Document doc = new Document();

				// ファイルパスの登録
				doc.add(new StringField("id", file, Store.YES));

				// ファイルの中身の登録
				doc.add(new TextField("titles", 
						new FileReader(args[1] + "/" + file)));

				// indexerにdocumentを登録
				indexWriter.addDocument(doc);
				
				count++;
			}
			*/

			// indexerのクローズ
			indexWriter.forceMerge(2);
			indexWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
