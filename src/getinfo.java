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
		// //TODO �ϵ����� ��record.properties����
		// for (int i = 0; i < str1.length; i++) {
		// for (int j = 0; j < str2.length; j++) {
		// for (int j2 = 0; j2 < str3.length; j2++) {
		// for (int k = 0; k < str4.length; k++) {
		// param = str1[i] + str2[j] + str3[j2] + str4[k];
		// flag=Integer.parseInt(param);
		// //ҳ�洫�ݲ����ı߽�
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

		// ��Ҫ�ķִ���
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		// ���������ĸ��汾��IndexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
				Version.LUCENE_35, analyzer);
		// ����ϵͳ�ļ�----- ./ ��ǰ·���µ�
		Directory directory = new SimpleFSDirectory(new File("E:\\lucene"));
		indexWriter = new IndexWriter(directory, indexWriterConfig);
		// ��ȡʵ�����
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
		// TODO �ϵ����� ��record.properties����
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				for (int j2 = 0; j2 < str3.length; j2++) {
					for (int k = 0; k < str4.length; k++) {
						param = str1[i] + str2[j] + str3[j2] + str4[k];
						flag = Integer.parseInt(param);
						System.out.println(param);
						// ҳ�洫�ݲ����ı߽�
						if (flag >= 29100901)
							break;

						arr = hp.getAClass(param);
						if (arr.iterator() != null) {
							// System.out.println(param);
							Iterator<Person> it = arr.iterator();
							while (it.hasNext()) {
								Person p = it.next();
								try {

									// indexWriter�������
									Document doc = new Document();
									// �ı���������� ���� ����
									/*
									 * doc.add(new
									 * Field("title","�й����׶�������",Store
									 * .YES,Index.ANALYZED)); doc.add(new
									 * Field("content"
									 * ,"�й����׶��ڱ���",Store.YES,Index.ANALYZED));
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
									// ��ӵ�������ȥ
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

	// �����ѯ����Ҫ�õ����������ǽ���������ʹ���ʱ�Ľ�������ͬ
	public static void searchIndexFileResult() throws IOException {
		List<Person> Persons = new ArrayList<Person>();
		// �õ�������Ŀ¼
		Directory directory = new SimpleFSDirectory(new File("E:\\lucene"));
		// ����Ŀ¼��һ��indexReader
		IndexReader indexReader = IndexReader.open(directory);
		// System.out.println(indexReader.maxDoc());
		// ��ȡ��Сֵ��document����
		// Document doc=indexReader.document(0);
		// ��ȡ���ֵ��document����
		// Document doc=indexReader.document(indexReader.maxDoc()-1);
		// document�����get(�ֶ�����)������ȡ�ֶε�ֵ
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
	
