package LRU;

import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.PagesPerMinute;



public class LRU_timmer {

	
	
	class Cache {
		int index = -1; // 页面编号
		int timmer = 0; // 计时器
		boolean tag = false;// 是否被使用

		public void indexChange(int index) {
			this.index = index;
		}
	}
	
	Cache[] Caches;
	int[] pages;
	int[][] Cache_Print;
	String[] tags;
	boolean Full_FLAG = false;

	/*
	 * judge whether there is any cache empty
	 */
	public boolean isFull() {

		for (int i = 0; i < Caches.length; i++) {
			if (Caches[i].tag == false)
				return false;

		}

		return true;

	}

	/*
	 * judge whether this page was in cache
	 */
	public boolean isExist(int page_index) {

		for (int i = 0; i < Caches.length; i++) {
			if (Caches[i].index == page_index) {

				for (int k = 0; k < Caches.length; k++)// occupied caches' timmer update
				{
					Caches[k].timmer++;
				}

				Caches[i].timmer = 0;// change timmer
				return true;
			}

		}

		return false;

	}

	/*
	 * return the substituted page,which is least recently used
	 */
	public int getMax() {

		int max = 0;
		int index = 0;

		for (int i = 0; i < Caches.length; i++) {
			if (Caches[i].timmer >= max) // find the least recently used page
			{
				max = Caches[i].timmer;
				index = i;
			}

		}

		return index;// return the page index

	}

	/*
	 * Init pages which will test
	 */
	public void init() {
		System.out.print("请输入Cache数目：");
		Scanner scanner = new Scanner(System.in);
		int cache_size = scanner.nextInt();
		Caches = new Cache[cache_size];
		for (int i = 0; i < cache_size; i++)
			Caches[i] = new Cache();

		System.out.println();
		System.out.print("请输入页面总数量：");

		int pageNum = scanner.nextInt();// total page numbers

		pages = new int[pageNum];

		Cache_Print = new int[cache_size][pageNum];
		tags = new String[pageNum];

		System.out.println();
		System.out.print("请按顺序输入要访问的页面：");
		for (int i = 0; i < pageNum; i++)
			pages[i] = scanner.nextInt();

	}

	public void initAuto() {

		Caches = new Cache[3];
		pages = new int[20];
		Cache_Print = new int[3][20];
		tags = new String[20];

		for (int i = 0; i < 3; i++)
			Caches[i] = new Cache();

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

			if (isExist(pages[i])) {// page is already in cache

				for (int j = 0; j < Caches.length; j++)
					Cache_Print[j][i] = Cache_Print[j][i - 1];// 第i次替换时，各cache里面的页号不变

				tags[i] = "  ";

				continue;

			} else if (Full_FLAG == false)// empty cache remained
			{

				for (int j = 0; j < Caches.length; j++) {// find an empty cache
					if (Caches[j].tag == false) {// the cache is empty
						Caches[j].indexChange(pages[i]);// set page index to the cache
						Caches[j].tag = true;// the cache has been occupied
						Caches[j].timmer = 0;// init timmer
						for (int k = 0; k < Caches.length; k++)// occupied caches' timmer update
						{
							Caches[k].timmer++;
						}

						break;
					}

				}

				Full_FLAG = isFull();// judge whether caches are all occupied
			} else {// no empty cache,cache substitute
				int index = getMax();
				Caches[index].indexChange(pages[i]);// page substitute

				for (int k = 0; k < Caches.length; k++)// occupied caches' timmer update
				{
					Caches[k].timmer++;
				}

				Caches[index].timmer = 0;// change timmer
			}

			for (int j = 0; j < Caches.length; j++)
				Cache_Print[j][i] = Caches[j].index;// 第i次替换时，各cache里面的页号

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

		for (int i = 0; i < Caches.length; i++) {
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
		LRU_timmer lru = new LRU_timmer();
		lru.LRU();
	}
}
