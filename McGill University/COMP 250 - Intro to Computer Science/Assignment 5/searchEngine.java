import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

// ALEXANDER BRATYSHKIN
// 260684228


// This class implements a google-like search engine
public class searchEngine {

	public HashMap wordIndex; // this will contain a set of pairs (String,
								// LinkedList of Strings)
	public directedGraph internet; // this is our internet graph
	private String s;

	// Constructor initializes everything to empty data structures
	// It also sets the location of the internet files
	searchEngine() {
		// Below is the directory that contains all the internet files. 07
		htmlParsing.internetFilesLocation = "internetFiles";
		wordIndex = new HashMap();
		internet = new directedGraph();
	} // end of constructor

	// Returns a String description of a searchEngine
	public String toString() {
		return "wordIndex:\n" + wordIndex + "\ninternet:\n" + internet;
	}

	// This does a graph traversal of the internet, starting at the given url.
	// For each new vertex seen, it updates the wordIndex, the internet graph,
	// and the set of visited vertices.

	/**
	 * 
	 * Performs a graph traversal, starting from a given link. Builds graph using 
	 * a DFS. 
	 * @param url the starting URL from which the graph will be built 
	 * @throws Exception
	 */
	void traverseInternet(String url) throws Exception {

		if (!internet.getVisited(url)) { // goes through all vertices of the
											// graph that have not been visited
			internet.setVisited(url, true);
			LinkedList<String> words = htmlParsing.getContent(url);

			Iterator<String> i = words.iterator();

			while (i.hasNext()) {
				String word = i.next();

				if (!wordIndex.containsKey(word)) { // the word is added only if
													// it is not already
													// contained in the list

					LinkedList<String> urlsWithWord = new LinkedList<String>();
					urlsWithWord.addLast(url);
					wordIndex.put(word, urlsWithWord);

				} else {
					LinkedList<String> urlList = (LinkedList<String>) wordIndex.get(word);
					if (!urlList.contains(url)) { // if the url is not already
													// contained for a given
													// key, the add it
						urlList.add(url);
					}
				}
			}
			// DFS which traverses the internet and builds the graph

			LinkedList<String> links = htmlParsing.getLinks(url);
			internet.addVertex(url);

			Iterator<String> k = links.iterator();
			while (k.hasNext()) {
				String s = k.next();
				internet.addEdge(url, s);
				this.traverseInternet(s);
			}

		}
		return;
	}

	/**
	 * Method which computes the page rank of each vertex in the graph.
	 */
	void computePageRanks() {

		LinkedList<String> vertices = (LinkedList<String>) internet.getVertices();
		Iterator<String> k = vertices.iterator();

		while (k.hasNext()) {
			String w = k.next();
			internet.setPageRank(w, 1);
		}

		// repeat 100 iterations
		for (int i = 0; i < 100; i++) {

			LinkedList<String> vertices2 = (LinkedList<String>) internet.getVertices();
			Iterator<String> k2 = vertices2.iterator();

			while (k2.hasNext()) {

				String w = k2.next();

				double totalPR = 0;

				LinkedList<String> edges = (LinkedList<String>) internet.getEdgesInto(w);
				Iterator<String> j = edges.iterator();

				while (j.hasNext()) { // compute the calculations for the
										// neighbouring vertices of a given
										// vertex
					String w2 = j.next();

					totalPR = totalPR + internet.getPageRank(w2) / internet.getOutDegree(w2);

				}

				double pageRankVertix = (double) Math.round((0.5 + 0.5 * totalPR) * 100) / 100; // calculate
																								// the
																								// pageRank
																								// of
																								// the
																								// vertex

				internet.setPageRank(w, pageRankVertix);
			}
		}
		return;

	}

	/**
	 * Returns the link which best matches the user's query according to
	 * page-rank
	 * 
	 * Find the links which contain the user's query, and then return the one
	 * which has the biggest PR.
	 * 
	 * 
	 * @param user's
	 *            query
	 * @return best link
	 */
	String getBestURL(String query) {
		String url = "";
		double pageRank = 0;
		if (wordIndex.containsKey(query)) {
			// looks for the query in the list

			LinkedList<String> index = (LinkedList<String>) wordIndex.get(query);
			Iterator<String> k = index.iterator();

			while (k.hasNext()) {
				String s = k.next();

				if (internet.getPageRank(s) > pageRank) { // finds the link with
															// the highest PR
					pageRank = internet.getPageRank(s);
					url = s;
				}
			}

		} else {
			return "Sorry, we could not find your query";
		}
		return url;
	} // end of getBestURL

	public static void main(String args[]) throws Exception {
		searchEngine mySearchEngine = new searchEngine();
		// to debug your program, start with.
		// mySearchEngine.traverseInternet("http://www.cs.mcgill.ca/~blanchem/250/a.html");

		// When your program is working on the small example, move on to
		mySearchEngine.traverseInternet("http://www.cs.mcgill.ca");

		// this is just for debugging purposes. REMOVE THIS BEFORE SUBMITTING
		// System.out.println(mySearchEngine);

		mySearchEngine.computePageRanks();

		BufferedReader stndin = new BufferedReader(new InputStreamReader(System.in));
		String query;
		do {
			System.out.print("Enter query: ");
			query = stndin.readLine();
			if (query != null && query.length() > 0) {
				System.out.println("Best site = " + mySearchEngine.getBestURL(query));
			}
		} while (query != null && query.length() > 0);
	} // end of main
}