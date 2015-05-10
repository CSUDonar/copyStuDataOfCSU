import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/***
 * 
 * @author Donar
 *
 */
public class HtmlParser {
	
	public static String getHtmlContent(URL url, String encode) {
		StringBuffer contentBuffer = new StringBuffer();
		int responseCode = -1;
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载
			con.setConnectTimeout(6000);
			con.setReadTimeout(6000);// 获得网页返回信息码
			responseCode = con.getResponseCode();
			if (responseCode == -1) {
				System.out.println(url.toString()
						+ " : connection is failure...");
				con.disconnect();
				return null;
			}
			if (responseCode >= 400) // 请求失败
			{
				System.out.println("请求失败:get response code: " + responseCode);
				con.disconnect();
				return null;
			}
			InputStream inStr = con.getInputStream();
			InputStreamReader istreamReader = new InputStreamReader(inStr,
					encode);
			BufferedReader buffStr = new BufferedReader(istreamReader);
			String str = null;
			while ((str = buffStr.readLine()) != null)
				contentBuffer.append(str);
			inStr.close();
		} catch (IOException e) {
			e.printStackTrace();
			contentBuffer = null;
			System.out.println("error: " + url.toString());
		} finally {
			con.disconnect();
		}
		return contentBuffer.toString();
	}

	public static String getHtmlContent(String url, String encode) {
		if (!url.toLowerCase().startsWith("http://")) {
			url = "http://" + url;
		}
		try {
			URL rUrl = new URL(url);
			return getHtmlContent(rUrl, encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public  ArrayList<Person> getAClass(String param) {
		int begin;
		int value;
		String clazz="";
		String[] str = new String[11];
		ArrayList<Person> personArr = new ArrayList<Person>();
		String element;
		StringBuffer buf = new StringBuffer(
				getHtmlContent(
						"http://csujwc.its.csu.edu.cn/XSXJ/FB_BJXS_rpt.aspx?Sel_BJ="+param,
						"gbk"));
		
		if(buf.indexOf("没有相关数据")!=-1){
			System.out.println("查询出错");
			return personArr;
		}
		buf = new StringBuffer(buf.substring(buf.indexOf("行政班级：") + 5,
				buf.length()));
		if ( buf.indexOf("</td>")!=-1) {
			clazz =buf.substring(0, buf.indexOf("</td>"));
		}
		 
		
		if(!clazz.equals(""))System.out.println(clazz);

		while (true) {

			for (int i = 0; i < 11; i++) {

				begin = buf.indexOf("3px;'>");
				if (begin == -1)
					return personArr;

				buf = new StringBuffer(buf.substring(begin + 6, buf.length()));
				begin = buf.indexOf("<br>");

				element = buf.substring(0, begin);
				str[i] = element;
				// System.out.println(element);
				// System.out.println(buf);
			}

			Person person = new Person();
			person.setSno(str[0]);
			person.setSname(str[1]);
			person.setSsex(str[2]);
			person.setSbirthday(str[3]);
			person.setShometown(str[4]);
			person.setSmz(str[5]);
			person.setSmm(str[6]);
			person.setSaddress(str[7]);
			person.setSaddressno(str[8]);
			person.setSxq(str[9]);
			person.setExtra(str[10]);
			person.setClazz(clazz);
			personArr.add(person);
			// for (String string : str) {
			// System.out.println(string);
			// }
			

		}

	}

}