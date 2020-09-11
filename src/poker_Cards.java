import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class poker_Cards {
	public static void main(String[] args) {
        //tmpNum用做暫存隨機抽牌抽到幾的數字、cardsNum代表牌的總數
		int tmpNum, cardsNum;
        //optText、optText2用來紀錄Scanner傳入的字串
        String optText,optText2;
        //離開或進入while迴圈與否的boolean值
        boolean keepRun = false; 
        //使用Scanner讀取使用者輸入的資訊
        Scanner numScanner = new Scanner(System.in);
        
        //無窮迴圈，直到符合條件才會繼續
        while(true) {
        	System.out.print("請選擇是否要包含鬼牌？ 輸入 0 代表『否』、1 代表『是』(預設為『否』)\n");
            optText = numScanner.nextLine();
            
            //處理預設或手動輸入0
            if(optText.equals("") == true || optText.equals("0") == true) {
            	optText = "0";
            	cardsNum = 52;
            	break;
            }
            else {
            	//處理手動輸入1
        		if(optText.equals("1") == true) {
        			cardsNum = 54;
        			break;
            	}
        		//其他輸入值
        		else {
        			System.out.print("無效的輸入值，請重新輸入!\n");
        		}
            }
        }
        //cards為預設整齊的牌堆、newCards為洗牌後的牌堆、tmpCards為紀錄預設牌堆用
        ArrayList <String> cards = new ArrayList <String> (cardsNum);
        ArrayList <String> tmpCards = new ArrayList <String> (cardsNum);
		ArrayList <String> newCards = new ArrayList <String> (cardsNum);
		
		//存花色以及數字的字串陣列
		String[] kinds = {"黑桃", "方塊", "紅心", "黑梅"};
		String[] nums = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		//先產生52張牌的組合
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 13; j++) {
				cards.add(kinds[i-1]+" "+nums[j-1]);
			}
		}
		
		//54張時需額外加入2張鬼牌
		if(cardsNum == 54) {
			cards.add("黑鬼牌");
			cards.add("紅鬼牌");
		}
		
		//開始重排，使用do...while讓重排必定執行一次
		do {
			/*想法：洗牌的方式就是從特定數量的牌中隨機抽走一張，放到另一堆，
			持續此動作直到剩下最後一張(那就不用選了，直接把最後一張放到另一堆即可)*/
			
			//把預設牌堆的內容複製給tmpCards陣列
			for(int i = 0; i < cardsNum; i++) {
				tmpCards.add(cards.get(i));
			}
			
			//執行洗牌
			//index範圍0~(cardsNum-1)
			for(int x = (cardsNum - 1); x >= 0; x--) {
				//牌堆只剩一張時
				if(x == 0) {
					//把舊牌堆的最後一張加入新牌堆
					newCards.add(tmpCards.get(0));
					tmpCards.remove(0);
				}
				//牌堆還有其他牌時
				else {
					//決定從舊牌堆要抽走哪一張
					tmpNum = (int)(Math.random()*x)+1;
					//把舊牌堆的那一張加入新牌堆
					newCards.add(tmpCards.get(tmpNum));
					tmpCards.remove(tmpNum);
				}
			}
			
			//52張牌
			if(cardsNum == 52) {		
				//印出新牌堆內容
				for(int l = 0; l < 52; l++) {
					if(l%4 == 0 && l > 0) {
						System.out.print("\n");
					}
					System.out.print(newCards.get(l)+" ");
				}
			}
			//54張牌
			if(cardsNum == 54) {
				//印出新牌堆內容
				for(int l = 0; l < 54; l++) {
					if(l%6 == 0 && l > 0) {
						System.out.print("\n");
					}
					System.out.print(newCards.get(l)+" ");
				}
			}
			
			while(true) {
				System.out.print("\n\n請選擇是否再重新洗牌一次？ 輸入 0 代表『否』、1 代表『是』(預設為『否』)\n");
				optText2 = numScanner.nextLine();
				
				//處理預設或手動輸入0
	            if(optText2.equals("") == true || optText2.equals("0") == true) {
	            	keepRun = false;
	            	System.out.print("程式已停止...\n");
	            	break;
	            }
	            else {
	            	//處理手動輸入1
	            	if(optText2.equals("1") == true) {
	            		keepRun = true;
	            		System.out.print("再次進行洗牌...\n\n");
	            		//清空新牌堆，準備下次的洗牌
	    				newCards.clear();
	            		break;
	            	}
	            	//其他輸入值
	        		else {
	        			System.out.print("無效的輸入值，請重新輸入!\n");
	        		}
	            }
			} 
		}while(keepRun);
		
		//關閉Scanner
        numScanner.close();
	}
}
