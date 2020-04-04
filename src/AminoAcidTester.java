import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AminoAcidTester {

    @Test
    public void AminoAcidLL1(){

       AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCG");//tests addcodon

       assertEquals('P',list.getAminoAcid());
        //As Expected i got P
    }
    @Test
    public void AminoAcidLL11(){

        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGUAG");

        assertEquals('P',list.getAminoAcid());
        assertEquals('L',list.getNext().getAminoAcid());//L
        //As expected the next value would be null because the codon UAG makes the list stop.

    }
    @Test
    public void AminoAcidLL2(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGUUGCCG");//tests createFromRNASequence

        //   test.createFromRNASequence("CCGUUGGCACUGUUG");
        // UUGGCACUGUUG
        assertEquals('P',list.getAminoAcid());//p
        assertEquals('L',list.getNext().getAminoAcid());//L
        assertEquals('L',list.getNext().getNext().getAminoAcid());//A
        //  assertEquals('A',list.getNext().getNext().getNext().getAminoAcid());//L
        // assertEquals('A',list.getNext().getNext().getNext().getNext().getAminoAcid());//L
    }
    @Test
    public void AminoAcidLL22(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGUUGGCACUGUUG");

        //   test.createFromRNASequence("CCGUUGGCACUGUUG");
        // UUGGCACUGUUG
        assertEquals('P',list.getAminoAcid());//p
        assertEquals('L',list.getNext().getAminoAcid());//L
        assertEquals('L',list.getNext().getNext().getAminoAcid());//A
          assertEquals('A',list.getNext().getNext().getNext().getAminoAcid());//L
         assertEquals('A',list.getNext().getNext().getNext().getNext().getAminoAcid());//L

    }


    @Test
    public void AminoAcidLL3(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGCCG");//tests TotalCount
        int [] a = list.aminoAcidCounts();

        int[] example = new int[2];
        example[0] = 80;
        example[1] = 80;
        //int [] ex = {3,4};

        assertArrayEquals(example , a);//since the counter is 2 then it is correct.
    }
    @Test
    public void AminoAcidLL33(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGUUG");

        int[] example = new int[4];
        example[0] = 1;
        example[1] = 0;
        //int [] ex = {3,4};

        assertArrayEquals(example , list.getCount());//since the counter is 1 then it is correct.
    }
    @Test
    public void AminoAcidLL4(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGCCGCCGCCG");//aminoAcid From list

       char[] a =  list.aminoAcidList();
        char[] example = new char[2];
        example[0] = 'P';
        example[1] = 'P';

        //int [] ex = {3,4};

        assertArrayEquals(example , a);//since the counter is 1 then it is correct.
    }
    @Test
    public void AminoAcidLL44(){
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("CCGUUG");//tests for aminoacid P

        char[] a =  list.aminoAcidList();
        char[] example = new char[3];
        example[0] = 'P';
        example[1] = 'L';
        example[2] = 'L';
        //int [] ex = {3,4};

        assertArrayEquals(example , a);//since the counter is 1 then it is correct.
    }

//
//    @Test
//    public void AminoAcidLL4(){
//        AminoAcidLL test = new AminoAcidLL();
//
//        int [] example = {3,4};
//        int [] ex = {3,4};
//        int [] result = test.codonCompare(example);
//
//        assertArrayEquals(ex , example);
//    }
//
//    @Test
//    public void AminoAcidLL7(){
//        AminoAcidLL test = new AminoAcidLL();
//
//        int [] example = {3,4};
//        int [] ex = {3,4};
//        int [] result = test.isSorted(example);
//
//        assertArrayEquals(ex , example);
//    }


//    @Test
//    public void AminoAcidLL9(){
//        AminoAcidLL test = new AminoAcidLL();
//
//        int [] example = {3,4};
//        int [] ex = {3,4};
//        int [] result = test.sort(example);
//
//        assertArrayEquals(ex , example);
//    }

}
