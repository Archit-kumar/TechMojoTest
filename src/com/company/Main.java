package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static Map<String,HashTagData> hashTagDataDatabase = new HashMap<String,HashTagData>();

    public static void main(String ar[]){

        Scanner sc = new Scanner(System.in);

        boolean isExit = false;

        while(true){

            System.out.println("Enter tweet to process  or press enter to exit "+"\n");

            String inputTweet = sc.nextLine();

            if(inputTweet.isBlank()) {

                break;
            }

            processTweet(inputTweet);

            System.out.println("");

        }

        hashTagDataDatabase.values().stream().sorted().limit(10).forEach( key -> System.out.println(key));

    }
    static void processTweet(String tweet){

        String regex  = "(?:\\s|\\A|^)[#]+([A-Za-z0-9-_]+)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(tweet);

        while (matcher.find()) {

            String hashtag = matcher.group().trim();
            HashTagData hashTagData;

            if (hashTagDataDatabase.containsKey(hashtag)) {

                hashTagData = hashTagDataDatabase.get(hashtag);

                hashTagData.setFrequency(hashTagData.getFrequency() + 1);

            } else {

                hashTagData = new HashTagData(hashtag, 1);

            }
            hashTagDataDatabase.put(hashtag, hashTagData);

        }
    }
}
class HashTagData implements Comparable<HashTagData>{

    private String hashtag;

    private Integer frequency;

    HashTagData(String hashtag, Integer frequency){

        this.hashtag = hashtag;
        this.frequency = frequency;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString(){

        return this.getHashtag()+" "+this.frequency;
    }

    public int compareTo(HashTagData data)
    {
        return data.getFrequency() - this.getFrequency();
    }
}
