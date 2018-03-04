import java.util.ArrayList;

public class pack {
	private ArrayList<card>lst;
	
	public pack(){
		lst = new ArrayList<card>();
		}
		
		public card get(int num) {
			return lst.get(num);
		}
		
		public void add(card elem) {
			lst.add(elem);
		}
		
		public void remove(int num) {
			lst.remove(num);
		}
		
		public int size()
		{
			return lst.size();
		}
		
		public void clear() {
			lst.clear();
		}
	

}
