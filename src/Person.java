import java.io.UnsupportedEncodingException;


public class Person {
	String sno;
	String sname;
	String ssex;
	String sbirthday;
	String shometown;
	String smz;
	String smm;
	String saddress;
	String saddressno;
	String sxq;
	String extra;
	String clazz;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() throws UnsupportedEncodingException {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSbirthday() {
		return sbirthday;
	}
	public void setSbirthday(String sbirthday) {
		this.sbirthday = sbirthday;
	}
	public String getShometown() {
		return shometown;
	}
	public void setShometown(String shometown) {
		this.shometown = shometown;
	}
	public String getSmz() {
		return smz;
	}
	public void setSmz(String smz) {
		this.smz = smz;
	}
	public String getSmm() {
		return smm;
	}
	public void setSmm(String smm) {
		this.smm = smm;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getSaddressno() {
		return saddressno;
	}
	public void setSaddressno(String saddressno) {
		this.saddressno = saddressno;
	}
	public String getSxq() {
		return sxq;
	}
	public void setSxq(String sxq) {
		this.sxq = sxq;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return "Person [sno=" + sno + ", sname=" + sname + ", ssex=" + ssex
				+ ", sbirthday=" + sbirthday + ", shometown=" + shometown
				+ ", smz=" + smz + ", smm=" + smm + ", saddress=" + saddress
				+ ", saddressno=" + saddressno + ", sxq=" + sxq + ", extra="
				+ extra + ", clazz=" + clazz + "]";
	}
	
}
