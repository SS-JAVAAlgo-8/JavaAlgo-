import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for (int i =0 ; i <N ; i++) {
			int cnt =0;
			int chk=0;
			String pal = br.readLine();
			ArrayList<Character> pal_1 = new ArrayList<>();
			ArrayList<Character> pal_2 = new ArrayList<>();
			
			for (int j =0 ; j < pal.length(); j ++) {
				pal_1.add(pal.charAt(pal.length()-j-1));
				pal_2.add(pal.charAt(j));
			}
			for (int j = 0 ; j<=pal_2.size()/2-1; j ++) {
				if (pal_1.get(j)!=pal_2.get(j)) {
					cnt=1;
					int cnt_2=check(pal_1,pal_2,j);
					int cnt_3=check(pal_2,pal_1,j);
					//System.out.println("카운트"+cnt_2 + " " + cnt_3);
					cnt+=Math.min(cnt_2, cnt_3);
					break;
				}
			}
			System.out.println(cnt);
		}

		}
	static int check(ArrayList<Character> pal_3,ArrayList<Character> pal_4,int j) {
		ArrayList<Character> pal_1 = new ArrayList<>(pal_3);
		ArrayList<Character> pal_2 = new ArrayList<>(pal_4);
		pal_1.remove(j);
		//System.out.println("리스트"+ pal_1+ " "+ pal_2);
		for (int i=0; i <= pal_1.size()/2-1;i++) {
			if (pal_1.get(i) !=pal_2.get(i)) {
				return 1;
			}
		}
		return 0;
	}
}