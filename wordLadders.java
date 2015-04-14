
public class wordLadders {
	private static ST<String, Integer> st;
	private static String[] keys;
	private static Digraph G;

	
     
	public static boolean isConnected(int index1, int index2){

		for(int i = keys[index1].length() - 1; i > keys[index1].length() - 1 - 4; i--){ // for the last 4 letters of keys[index1] 
			if(keys[index2].indexOf(keys[index1].charAt(i)) == -1){ // if one of the last 4 letters of word at index1 is not in word at index2
				return false;
			}
		}

		return true;
	}

 	public static void main(String[] args) {
 		   st = new ST<String, Integer>();
           int index = 0;
        while (!StdIn.isEmpty()) {
        	
            String a = StdIn.readLine();
            st.put(a, index);
            index ++;
        }

        // inverted index to get string keys in an aray
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Digraph(st.size());//initializes the directed graph with the number of verticies that corresponds to st.size()

        for(int i = 0; i< keys.length; i++){
        	for(int j = 0; j < keys.length; j++){
        		if ( i != j){ // if it is not the same
        			if(isConnected(i,j)){ // if the two words are connected
        				G.addEdge(i,j); // Adds the directed edge i->j to the digraph.
        			}
        		}
        	}
        }

        System.out.println(G.toString());
    }
}


 	