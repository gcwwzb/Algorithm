package LRU;

import java.util.ArrayList;
import java.util.Scanner;
import javax.print.attribute.standard.PagesPerMinute;



class Cache {
	int index = -1; // 页面编号
	Cache next;

	public void indexChange(int index) {
		this.index = index;
	}
}

class CacheQueue{//queue
	
	Cache head = null;
	Cache rear = null;
	int size = 0;
	
	
	/*
	 * judge whether there is any cache empty
	 */
	public boolean isFull(int maxsize) {

	if(size<maxsize)
		return false;
	else 
		return true;

	}

	/*
	 * judge whether this page was in cache
	 */
	public boolean isExist(int page_index) {

		if(head==null)
			return false;
		
		
		if(head.index==page_index)//target node is head
		{
			rear.next = head;
			rear = head;
			head = head.next;
			rear.next = null;
			return true;
		}
		
		Cache p = head;
		while (p.next!=null) {
			if(p.next.index==page_index)
			{
				rear.next = p.next;
				rear = p.next;
				p.next = p.next.next;
				rear.next = null;
				return true;
			}
		
			p = p.next;		
		}
		return false;

	}
	
	
	
	

	
	public void addNode(int page_index) {
		
		Cache cache = new Cache();
		cache.indexChange(page_index);
		if(size==0)
		{//if no node in queue,insert to head
			head = cache;
			rear = cache;
		}else {//insert to queue's rear,represent recently used
			rear.next = cache;
			 rear = cache;
		}
		size++;
		
	}
	
	/*
	 * return the substituted page,which is least recently used
	 */
	public void updateNode(int page_index) {
		
		Cache cache = new Cache();
		cache.indexChange(page_index);
		head = head.next;
		rear.next = cache;
		rear = cache;
	}
	
}




public class LRU_queue {

	int cache_size;
	int pageNum;
	CacheQueue cacheQueue;
	int[] pages;
	int[][] Cache_Print;
	String[] tags;

	


	/*
	 * Init pages which will test
	 */
	public void init() {
	    cacheQueue = new CacheQueue();
		System.out.print("请输入Cache数目：");
		Scanner scanner = new Scanner(System.in);
		cache_size = scanner.nextInt();

		System.out.println();
		System.out.print("请输入页面总数量：");

	 pageNum = scanner.nextInt();// total page numbers

		pages = new int[pageNum];

		Cache_Print = new int[cache_size][pageNum];
		for(int i=0;i<cache_size;i++)
			for(int j=0;j<pageNum;j++)
				Cache_Print[i][j] = -1;
		
		
		tags = new String[pageNum];

		System.out.println();
		System.out.print("请按顺序输入要访问的页面：");
		for (int i = 0; i < pageNum; i++)
			pages[i] = scanner.nextInt();

	}

	public void initAuto() {
		cache_size = 3;
		 pageNum = 20;
		pages = new int[pageNum];
		Cache_Print = new int[cache_size][pageNum];
		 cacheQueue = new CacheQueue();
		for(int i=0;i<cache_size;i++)
			for(int j=0;j<pageNum;j++)
				Cache_Print[i][j] = -1;
		tags = new String[20];

		pages[0] = 7;
		pages[1] = 0;
		pages[2] = 1;
		pages[3] = 2;
		pages[4] = 0;
		pages[5] = 3;
		pages[6] = 0;
		pages[7] = 4;
		pages[8] = 2;
		pages[9] = 3;
		pages[10] = 0;
		pages[11] = 3;
		pages[12] = 2;
		pages[13] = 1;
		pages[14] = 2;
		pages[15] = 0;
		pages[16] = 1;
		pages[17] = 7;
		pages[18] = 0;
		pages[19] = 1;
	}

	public void LRU() {

		init();
	//	initAuto();
		for (int i = 0; i < pages.length; i++) {			
			
			if (cacheQueue.isExist(pages[i])==true) {// page is already in cache

				Cache p = cacheQueue.head;
				int j=0;
				while(p!=null) {
					Cache_Print[j++][i] = p.index;// 第i次替换时，各cache里面的页号
					p=p.next;
				}
				
				tags[i] = "  ";

				continue;

			} else if (cacheQueue.isFull(cache_size) == false)// empty cache remained
			{
				cacheQueue.addNode(pages[i]);// find an empty cache,set page index to the cache
			} else {// no empty cache,cache substitute		
				cacheQueue.updateNode(pages[i]);
			}

			
			Cache p = cacheQueue.head;
			int j=0;
			while(p!=null) {
				Cache_Print[j++][i] = p.index;// 第i次替换时，各cache里面的页号
				p=p.next;
			}
			
			tags[i] = "$";

		}

		print();

	}

	public void print() {

		System.out.println();
		for (int j = 0; j < pages.length; j++)
		System.out.print("----");
		System.out.println();
		System.out.print("  Pages:        ");

		for (int j = 0; j < pages.length; j++)
			System.out.print(pages[j] + "  ");

		
		System.out.println();
		for (int j = 0; j < pages.length; j++)
		System.out.print("----");

		for (int i = 0; i < cache_size; i++) {
			System.out.println();
			System.out.print("  Cache " + (i + 1) + ":     ");
			for (int j = 0; j < pages.length; j++) {
				if (Cache_Print[i][j] == -1) {
					System.out.print("  " + "  ");
				} else {
					System.out.print(Cache_Print[i][j] + "  ");
				}

			}

		}

		System.out.println();
		for (int j = 0; j < pages.length; j++)
		System.out.print("----");
		System.out.println();
		System.out.print("                     ");
		for (int j = 0; j < pages.length; j++)
			System.out.print(tags[j] + "  ");
		System.out.println();
		for (int j = 0; j < pages.length; j++)
		System.out.print("----");

	}

	public static void main(String[] args) {
		LRU_queue lru = new LRU_queue();
		lru.LRU();
	}
}
