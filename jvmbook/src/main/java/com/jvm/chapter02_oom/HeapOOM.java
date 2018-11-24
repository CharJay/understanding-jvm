package com.jvm.chapter02_oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出异常测试<br>
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author taomk 2016年10月31日 下午8:40:02
 *
 */
public class HeapOOM {

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		int i=0;//1215488
		while(true){
//			i++;
//			System.out.println(i);
			list.add(new OOMObject());
		}
	}
}
