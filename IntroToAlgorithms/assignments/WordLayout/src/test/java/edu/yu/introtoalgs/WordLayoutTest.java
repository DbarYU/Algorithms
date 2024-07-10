package edu.yu.introtoalgs;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WordLayoutTest {

    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("FOUR");
        list.add("THE");
        list.add("THE");
        list.add("TO");
        WordLayoutBase wordLayout= new WordLayout(4,3,list);
        WordLayoutBase wordLayout1= new WordLayout(4,3,list);
        assertEquals(wordLayout1,wordLayout);
    }
    @Test
    public void testInit(){
        List<String> list = new ArrayList<>();
        list.add("BOB");
        list.add("DOG");
        list.add("CAT");
        WordLayoutBase wordLayout= new WordLayout(3,3,list);}
    @Test
    public void test332(){
        List<String> list = new ArrayList<>();
        list.add("CAT");
        list.add("BOB1");
        list.add("TO");
        list.add("BOB2");
        list.add("BOB3");
        list.add("BOB4");
        list.add("BOB5");
        list.add("BOB6");
        list.add("BOB7");list.add("BOB9");list.add("BOB12");list.add("BOB143");list.add("BOB555");list.add("BOB4444");list.add("BOB4444");list.add("BOB555");
        list.add("BOB8");list.add("BOB10");list.add("BOB11");list.add("BOB1344");list.add("BOB3333");list.add("BOB4444");list.add("BOB4445");list.add("BOB5555");

        WordLayoutBase wordLayout= new WordLayout(70000,2,list);
        WordLayoutBase wordLayout1= new WordLayout(2,7000,list);
        assertEquals(wordLayout,wordLayout1);


    }

    @Test
    public void evenCheck(){
        ArrayList<String> arrayList = new ArrayList<>(3500);
        for(int i =0; i<3501;i++){
            arrayList.add("a"+i%10);
        }
        WordLayoutBase wordLayoutBase1 = new WordLayout(3501,2,arrayList);
        WordLayoutBase wordLayoutBase2= new WordLayout(3501,2,arrayList);
        assertEquals(wordLayoutBase1,wordLayoutBase2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExpected() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("BOOOOBI");
        WordLayoutBase wordLayoutBase = new WordLayout(2, 2, arrayList);
    }

    @Test (expected = IllegalArgumentException.class)
    public void evenFail(){
        ArrayList<String> arrayList = new ArrayList<>(7000/2);
        for(int i =0; i<3500;i++){
            arrayList.add("a"+i%10);
        }
        WordLayoutBase wordLayoutBase = new WordLayout(2,3499,arrayList);
    }
    @Test
    public void aLotOfData1(){

        ArrayList<String> arrayList = new ArrayList<>(451);
        for (int z =0; z<900; z++) {
                String str = randomStringGenerator(15);
                arrayList.add(str);
        }
        WordLayoutBase wordLayoutBase1 = new WordLayout(2,7000,arrayList);
        WordLayoutBase wordLayoutBase2 = new WordLayout(7000,2,arrayList);
        assertEquals(wordLayoutBase2,wordLayoutBase1);

    }
    @Test
    public void aLotOfData2(){
        ArrayList<String> arrayList = new ArrayList<>(450);

        for (int z =0; z<450; z++) {
            int counter = (z % 30) + 1;
            String str = randomStringGenerator(counter);
            arrayList.add(str);
        }
        WordLayoutBase wordLayoutBase1 = new WordLayout(2,7000,arrayList);
        WordLayoutBase wordLayoutBase2 = new WordLayout(7000,2,arrayList);
        assertEquals(wordLayoutBase2,wordLayoutBase1);
    }
    @Test
    public void smallData(){
        List<String> list = new ArrayList<>();
        list.add("TO");
        list.add("WO");
        list.add("KO");
        WordLayoutBase wordLayout= new WordLayout(2,3,list);
        WordLayoutBase wordLayout1= new WordLayout(3,2,list);


    }
    @Test
    public void medeumData(){
        ArrayList<String> list = new ArrayList();
        int min = 2;
        int max = 50;

        Random random = new Random();

        int column = random.nextInt((max-min)+1) +min;
        int row = random.nextInt((max-min)+1) +min;

        int smallest = Math.min(row, column);
        int counter = 0;
        for(int i = 0,k=0, z=0; i <smallest;k++){
            if(k % 2 == 0) {
                String word = randomStringGenerator(row - i);
                assert(!word.isEmpty());
                list.add(word);
                counter += row-1;
                z++;
            }else {
                String word = randomStringGenerator(column - z);
                list.add(word);
                i++;
                    counter += column-z;

            }
        }
        WordLayoutBase wordLayoutBase = new WordLayout(column,row,list);
        WordLayoutBase wordLayoutBase1 = new WordLayout(column,row,list);
        boolean noUpper = true;
        List<Character> arrayList = new ArrayList<>();
        for (char[] num : wordLayoutBase1.getGrid().grid) {
            for (char character : num) {
                arrayList.add(character);
                if(Character.isUpperCase(character))
                    noUpper = false;
                    break;

            }
        }
        assertTrue(noUpper);
    }
    @Test(expected = IllegalArgumentException.class)
    public void medeumDataFail(){
        ArrayList<String> list = new ArrayList();
        int min = 2;
        int max = 100;

        Random random = new Random();

        int column = random.nextInt((max-min)+1) +min;
        int row = random.nextInt((max-min)+1) +min;

        int smallest = Math.min(row, column);
        int counter = 0;
        for(int i = 0,k=0, z=0; i <smallest;k++){
            if(k % 2 == 0) {
                list.add(randomStringGenerator(row - i));
                counter += row-1;
                z++;
            }else {
                list.add(randomStringGenerator(column-z));
                i++;
                counter += column-z;
            }
        }
        WordLayoutBase wordLayoutBase = new WordLayout(column,row-1,list);
        WordLayoutBase wordLayoutBase1 = new WordLayout(column-1,row,list);
        boolean noUpper = true;
        List<Character> arrayList = new ArrayList<>();
        for (char[] num : wordLayoutBase1.getGrid().grid) {
            for (char character : num) {
                arrayList.add(character);
                if(Character.isUpperCase(character))
                    noUpper = false;
                break;

            }
        }
        assertTrue(noUpper);
    }
    @Test
    public void smallDataTest(){
        List<String> list = new ArrayList<>();
        list.add("word");
        list.add("this");
        list.add("same");
        list.add("dada");
        list.add("tea");
        list.add("sam");
        list.add("cat");
        WordLayoutBase wordLayoutBase1 = new WordLayout(5,5,list);
        WordLayoutBase wordLayoutBase2 = new WordLayout(5,5,list);

        assertEquals(wordLayoutBase2,wordLayoutBase1);
    }
    @Test
    public void inputTest(){
        List<String> list = new ArrayList<>();
        list.add("CAT");
        list.add("TO");
        list.add("ON");
        list.add("Q");
        list.add("Z");
        WordLayoutBase wordLayoutBase = new WordLayout(3,3,list);


    }
    @Test
    public void inputTest1(){
        List<String> list = new ArrayList<>();
        list.add("makedaa");
        list.add("jamesaa");
        list.add("jakesaa");
        list.add("natanaa");
        list.add("sameaa");
        list.add("hahaaa");
        list.add("nanaaa");
        list.add("kakaaa");
        list.add("kakaaa");
        list.add("kakaaa");
        WordLayoutBase wordLayoutBase = new WordLayout(8,8,list);

    }

    @Test
    public void edgeCasesTest(){
        ArrayList<String> list = new ArrayList<>();


       // Random random = new Random();
        int num = 8;

        int counter = 0;
        for(int i = 0; i< 4; i++){
            list.add(randomStringGenerator(num-1));
        }
        for(int z=0; z<num-2;z++) {
            list.add(randomStringGenerator(num-2));
        }

        WordLayoutBase wordLayoutBase = new WordLayout(num,num,list);
        boolean noUpper = true;
        List<Character> arrayList = new ArrayList<>();
        for (char[] chars : wordLayoutBase.getGrid().grid) {
            for (char character : chars) {
                arrayList.add(character);
                if(Character.isUpperCase(character))
                    noUpper = false;
                break;

            }
        }
        assertTrue(noUpper);
        assertTrue(noUpper);
    }





    public String randomStringGenerator(int length) {
            String characters = "abcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                sb.append(randomChar);
            }

            return sb.toString();
    }

}