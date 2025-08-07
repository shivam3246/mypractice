//https://leetcode.com/problems/search-suggestions-system/
import java.util.*;
public class SearchSuggestionsSystems {
    public static class TrieNode{
        Map<Character,TrieNode> children = new HashMap<>();
        List<String> suggestions = new ArrayList<>();
    }
   private static TrieNode root = new TrieNode();
    public static List<List<String>> suggestedProducts(String []products, String searchWord){
        Arrays.sort(products);
        for(String product:products){
            insert(product);
        }
        TrieNode node = root;
        List<List<String>> result = new ArrayList<>();
        for(char c:searchWord.toCharArray()){
            if(node!=null){
                node =node.children.get(c);
            }
            result.add(node==null?new ArrayList<>():node.suggestions);
        }
        
        
        return result;
    }
    public static void insert(String word){
        TrieNode node = root;
        for(char c:word.toCharArray()){
            node.children.putIfAbsent(c,new TrieNode());
            node = node.children.get(c);
            if(node.suggestions.size()<3){
                node.suggestions.add(word);
            }
        }
        
    }

    public static void main(String[] args) {
      SearchSuggestionsSystems system = new SearchSuggestionsSystems();
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        
        List<List<String>> result = system.suggestedProducts(products, searchWord);
        
        for (List<String> suggestions : result) {
            System.out.println(suggestions);
        }
    }
}