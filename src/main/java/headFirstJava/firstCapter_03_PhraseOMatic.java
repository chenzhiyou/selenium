package headFirstJava;

public class firstCapter_03_PhraseOMatic {
    public static void main(String[] args){
        String[] wordListOne = {"24/7","multiTier","30000 foot","B-to-B","win-win","front-end"};
        String[] wordListTwo = {"24/7","multiTier"};
        String[] wordListThree = {"24/7","multiTier","30000 foot","B-to-B","win-win","front-end","24/7",
                "multiTier","30000 foot","B-to-B","win-win","front-end"};
        // 计算每一组有多少个名次术语
        int oneLength = wordListOne.length;
        int twoLength = wordListTwo.length;
        int threeLength = wordListThree.length;

        //产生随机数字
        int rand1 = (int)(Math.random()* oneLength);
        int rand2 = (int)(Math.random()* twoLength);
        int rand3 = (int)(Math.random()* threeLength);
        //组合出专家术语
        String phrase = wordListOne[rand1] + " " + wordListTwo[rand2] + " " + wordListThree[rand3];
        System.out.println("what we need is a "+ phrase);
    }
}
