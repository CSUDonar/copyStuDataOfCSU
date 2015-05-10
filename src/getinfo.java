import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

/***
 * 
 * @author Donar
 * 
 */
public class getinfo {
	static int ig;
	static int jg;
	static int j2g;
	static int kg;
	
	static {
		Properties pro = new Properties();
		System.out.println("-----");
		try {
			pro.load(new FileInputStream(new File("src/record.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ig = Integer.parseInt(pro.getProperty("first"));
		int jg = Integer.parseInt(pro.getProperty("second"));
		int j2g = Integer.parseInt(pro.getProperty("third"));
		int kg = Integer.parseInt(pro.getProperty("fourth"));
	}

	public static void main(String[] args) throws Exception {

		// Thread t = new Thread() {
		// public synchronized void run() {
		// try {
		// Thread.sleep(3000);
		// PrintWriter pw = new PrintWriter(new File("src/record.properties"));
		// pw.write("first="+ig+"\n");
		// pw.write("second="+jg+"\n");
		// pw.write("third="+j2g+"\n");
		// pw.write("fourth="+kg+"\n");
		// pw.close();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// };
		// t.start();
		// Thread t2 = new Thread() {
		// public void run() {
		// HtmlParser hp = new HtmlParser();
		// ArrayList<Person> arr;
		// String param;
		// dbUtils dbu = new dbUtils();
		// String[] str1 = { "01", "02", "03", "04", "05", "06", "07",
		// "08", "09", "10", "11", "12", "13", "14", "15", "16",
		// "17", "18", "19", "20", "21", "22", "23", "24", "25",
		// "26", "27", "28", "29", "30" };
		// String[] str2 = { "01", "02", "03", "04", "05", "06", "07",
		// "08", "09", "10", "11", "12", "13", "14", "15", "16",
		// "17", "18", "19", "20", "21", "22", "23", "24", "25",
		// "26", "27", "28", "29", "30" };
		// String[] str3 = { "01", "02", "03", "04", "05", "06", "07",
		// "08", "09", "10", "11", "12", "13", "14", "15", "16",
		// "17", "18", "19", "20", "21", "22", "23", "24", "25",
		// "26", "27", "28", "29", "30" };
		// // String [] str4=
		// //
		// {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
		// // String [] str1=
		// // {"01","02","03","04","05","06","07","08","09","10"};
		// // String [] str2=
		// // {"01","02","03","04","05","06","07","08","09","10"};
		// // String [] str3=
		// // {"01","02","03","04","05","06","07","08","09","10"};
		// String[] str4 = { "01", "02", "03", "04", "05", "06", "07",
		// "08", "09", "10" };
		// int flag;
		// int count=0;
		// //TODO 断点续传 读record.properties即可
		// for (int i = 0; i < str1.length; i++) {
		// for (int j = 0; j < str2.length; j++) {
		// for (int j2 = 0; j2 < str3.length; j2++) {
		// for (int k = 0; k < str4.length; k++) {
		// param = str1[i] + str2[j] + str3[j2] + str4[k];
		// flag=Integer.parseInt(param);
		// //页面传递参数的边界
		// if (flag>=29100901) break;
		//
		//
		// arr = hp.getAClass(param);
		// if (arr.iterator() != null) {
		// System.out.println(param);
		// Iterator<Person> it = arr.iterator();
		// while (it.hasNext()) {
		//
		// Person p = it.next();
		// try {
		// dbu.executeUpdate("insert into user (sno,sname,ssex,sbirthday,shometown,smz,smm,saddress,saddressno,sxq,extra,clazz) values('"
		// + p.getSno()
		// + "', '"
		// + p.getSname()
		// + "', '"
		// + p.getSsex()
		// + "', '"
		// + p.getSbirthday()
		// + "', '"
		// + p.getShometown()
		// + "', '"
		// + p.getSmz()
		// + "', '"
		// + p.getSmm()
		// + "', '"
		// + p.getSaddress()
		// + "', '"
		// + p.getSaddressno()
		// + "', '"
		// + p.getSxq()
		// + "', '"
		// + p.getExtra()
		// + "','"
		// + p.getClazz() + "');");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(p);
		// dbu.close();
		// }
		// }
		//
		// }
		// }
		// }
		//
		// }
		// }
		// };
		//
		// t2.start();
//		createIndexFile();
		searchIndexFileResult();
	}

	public static void createIndexFile() throws IOException {
		IndexWriter indexWriter = null;

		// 需要的分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		// 创建的是哪个版本的IndexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
				Version.LUCENE_35, analyzer);
		// 创建系统文件----- ./ 当前路径下的
		Directory directory = new SimpleFSDirectory(new File("E:\\lucene"));
		indexWriter = new IndexWriter(directory, indexWriterConfig);
		// 获取实体对象
		HtmlParser hp = new HtmlParser();
		ArrayList<Person> arr=new ArrayList<Person>();
		String param;
		dbUtils dbu = new dbUtils();
		String[] str1 = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30" };
		String[] str2 = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30" };
		String[] str3 = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30" };
		// String [] str4=
		// {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
		// String [] str1=
		// {"01","02","03","04","05","06","07","08","09","10"};
		// String [] str2=
		// {"01","02","03","04","05","06","07","08","09","10"};
		// String [] str3=
		// {"01","02","03","04","05","06","07","08","09","10"};
		String[] str4 = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10" };
		int flag;
		int count = 0;
		// TODO 断点续传 读record.properties即可
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				for (int j2 = 0; j2 < str3.length; j2++) {
					for (int k = 0; k < str4.length; k++) {
						param = str1[i] + str2[j] + str3[j2] + str4[k];
						flag = Integer.parseInt(param);
						System.out.println(param);
						// 页面传递参数的边界
						if (flag >= 29100901)
							break;

						arr = hp.getAClass(param);
						if (arr.iterator() != null) {
							// System.out.println(param);
							Iterator<Person> it = arr.iterator();
							while (it.hasNext()) {
								Person p = it.next();
								try {

									// indexWriter添加索引
									Document doc = new Document();
									// 文本中添加内容 标题 内容
									/*
									 * doc.add(new
									 * Field("title","中国的首都在哪里",Store
									 * .YES,Index.ANALYZED)); doc.add(new
									 * Field("content"
									 * ,"中国的首都在北京",Store.YES,Index.ANALYZED));
									 */
									doc.add(new Field("sname", p.getSname()
											.toString(), Store.YES,
											Index.ANALYZED));
									doc.add(new Field("class", p.getClazz(),
											Store.YES, Index.ANALYZED));
									doc.add(new Field("sno", p.getSno()
											.toString(), Store.YES,
											Index.ANALYZED));
									doc.add(new Field("shometown", p
											.getShometown().toString(),
											Store.YES, Index.ANALYZED));
									// 添加到索引中去
									indexWriter.addDocument(doc);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
							}

						}
					}
				}

			}
		}

		
	}

	// 如果查询是需要用到解析器，那解析器必须和创建时的解析器相同
	public static void searchIndexFileResult() throws IOException {
		List<Person> Persons = new ArrayList<Person>();
		// 得到索引的目录
		Directory directory = new SimpleFSDirectory(new File("E:\\lucene"));
		// 根据目录打开一个indexReader
		IndexReader indexReader = IndexReader.open(directory);
		// System.out.println(indexReader.maxDoc());
		// 获取最小值的document对象
		// Document doc=indexReader.document(0);
		// 获取最大值的document对象
		// Document doc=indexReader.document(indexReader.maxDoc()-1);
		// document对象的get(字段名称)方法获取字段的值
		/*
		 * System.out.println(doc.get("id"));
		 * System.out.println(doc.get("title"));
		 * System.out.println(doc.get("content"));
		 */
		int n = indexReader.maxDoc();
		for (int i = 0; i < n; i++) {
			Document doc = indexReader.document(i);
			Person p = new Person();
			ArrayList<Person> list =new ArrayList<Person>();
				
				for (Fieldable field : doc.getFields()) {
					System.out.println(field);
				}
				
			}
		}
	}
	
