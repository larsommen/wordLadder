
public class wordLadders {
	private static ST<String, Integer> st;
	private static String[] keys;
	private static Digraph G;

	
    

    /*
     *
     * @returns true if two words (indecies in G) are to be considered connected
     * that is if the four last letters of the first are found in the second
    */  
	public static boolean isConnected(int index1, int index2){

		for(int i = keys[index1].length() - 1; i > keys[index1].length() - 1 - 4; i--){ // for the last 4 letters of keys[index1] 
			if(keys[index2].indexOf(keys[index1].charAt(i)) == -1){ // if one of the last 4 letters of word at index1 is not in word at index2
				return false;
			}
		}

		return true;
	}

    /*
     *
     * Takes two words as args and file content from stdin
     */

 	public static void main(String[] args) {
        
        st = new ST<String, Integer>();

        // Reading words form piped file and puts them in st
        int index = 0;   
        while (!StdIn.isEmpty()) {   	
            String a = StdIn.readLine();
            st.put(a, index);
            index ++;
        }

        // inverted index to get string keys in an aray
        // we can now get a word from an int and an int from a word
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        //initializes the directed graph with the number of verticies that corresponds to st.size()
        G = new Digraph(st.size());

        for(int i = 0; i< keys.length; i++){
        	for(int j = 0; j < keys.length; j++){
                // if it is not the same
        		if ( i != j){ 
                    // if the two words are connected
        			if(isConnected(i,j)){ 
                        // Adds the directed edge i->j to the digraph.
        				G.addEdge(i,j); 
        			}
        		}
        	}
        }

        // check if both words are in the struckture - if so - find and print path - else terminate
        if(st.contains(args[0])&&st.contains(args[1])){
            int firstword=st.get(args[0]);
            int secondword=st.get(args[1]);
            BreadthFirstDirectedPaths pathFinder=new BreadthFirstDirectedPaths(G,firstword);
            if(pathFinder.hasPathTo(secondword)){
                Iterable<Integer> list=pathFinder.pathTo(secondword);
                boolean first =true;
                for(Integer i: list){
                    if (!first){
                        System.out.print(" -> ");
                    }
                    System.out.print(keys[i]);
                    first=false;
                }
                int lenghtOfPath=pathFinder.distTo(secondword)+1;
                System.out.println("\nLength of path is: "+lenghtOfPath);
            }else{
                System.out.println("No such path");
            }
        }else{
            System.out.println("Wrong arguments or words not in file - usage java wordladder word1 word2 <fileOfWords");
            return;
        }
        System.out.println();
    }
}


 	