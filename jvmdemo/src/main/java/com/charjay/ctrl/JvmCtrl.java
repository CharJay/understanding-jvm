package com.charjay.ctrl;

import com.charjay.ctrl.lock.DeadLock;
import com.charjay.ctrl.lock.Thread0;
import com.charjay.ctrl.lock.Thread1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 */
@RestController
public class JvmCtrl {

	private static final Logger logger = LoggerFactory.getLogger(JvmCtrl.class);

	/**
	 * cpu爆满的情况下，该接口还是可以访问
	 * @return
	 */
	@RequestMapping(value = { "hello" })
	public String hello() {
		return "hello";
	}

	/**
	 * 访问该接口，会出现cpu爆满的情况（当机器的多个cpu都爆满了还是可以使用，只是有点卡）
	 */
	@RequestMapping(value = { "cpu" })
	public void cpu() {
		while (true){

		}
	}
	@RequestMapping(value = { "ioWtite" })
	public void ioWtite() {
		while (true){
			File file = new File("/temp/iotest"+System.currentTimeMillis()+".txt");
			try {
				FileOutputStream outputStreram = new FileOutputStream(file);
				for (int i=51;i<10000;i++){
					outputStreram.write(new byte[256]);
				}
				outputStreram.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = { "jvmInfo" })
	public String jvmInfo() {
		List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
		StringBuffer sb = new StringBuffer();
		for (GarbageCollectorMXBean b:l){
			sb.append(b.getName()+"\n");
		}
		return sb.toString();
	}

	private static List<int[]> bigObj = new ArrayList<>();
	private static List<char[]> bigCharObj = new ArrayList<>();

	public static int[] genetate1M(){return new int[524288];}
	public static char[] genetate1MChar(){return new char[1048576];}

	@RequestMapping(value = { "jvmError" })
	public String jvmError() throws InterruptedException {
		for(int i=0;i<100;i++){
			if(i == 0){
				Thread.sleep(500L);
				System.out.println("start:"+new Date());
			}else{
				Thread.sleep(4000L);
			}
			bigObj.add(genetate1M());//该list一直有【大对象】生成，并且这些【大对象】有gc root指向，一直不会被回收，就会占满先占满eden，然后再占满old取，最后内存溢出
			bigCharObj.add(genetate1MChar());
		}
		return "jvmError";
	}

	@RequestMapping(value = { "jvmLock" })
	public String jvmLock() {
		DeadLock d1 = new DeadLock();
		Thread0 t0 = new Thread0(d1);
		Thread1 t1 = new Thread1(d1);
		t0.start();
		t1.start();
		return "jvmLock";
	}




}
