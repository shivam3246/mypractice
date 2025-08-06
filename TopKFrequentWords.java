//https://leetcode.com/problems/top-k-frequent-words/description/
import java.util.*;

public class TopKFrequentWords {
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        ArrayList<String> []buckets = new ArrayList[words.length+1];
        Map<String,Integer> map= new HashMap<>(); //To Store the frequencies of word
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        //Prepare the bucket
        for(String word:map.keySet()){
            int freq = map.get(word);
        if(buckets[freq]==null){
            buckets[freq]=new ArrayList<>();
        }        
        buckets[freq].add(word);
    }
    for(int i = buckets.length-1;i>0&&result.size()<k;i--){
        if(buckets[i]!=null){
            Collections.sort(buckets[i]);
        
        for(String word:buckets[i]){
            result.add(word);
            if (result.size()==k) {
                break;
            }
        }
    }
    }
        return result;
    
    }
    public static List<String> topElements(String []words,int k){
        List<String> result = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word, 0)+1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            int freqA = map.get(a);
            int freqB = map.get(b);
            if(freqA!=freqB){
                return freqA-freqB;
            }
            return b.compareTo(a);
        });
        for(String word:map.keySet()){
            pq.offer(word);
            if(pq.size()>k){
                pq.poll();
            }
        }
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        Collections.reverse(result);
        return result;
    }
    public static void main(String[] args) {
       String[] words = {"i","love","leetcode","i","love","coding"};
       int k = 2;
       //System.out.println(topKFrequent(words,k));
       System.out.println(topElements(words,k));

    }
    
}
