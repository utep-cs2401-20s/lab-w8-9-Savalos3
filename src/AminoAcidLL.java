class AminoAcidLL {
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL() {

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon) {

    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    this.codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    this.counts = new int[codons.length];
    incrCodons(inCodon);
    next = null;
    //System.out.print(aminoAcid);
  }


  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops,
   * if not passes the task to the next node.
   * If there is no next node, add a new node to the list that would contain the codon.
   */
  private void addCodon(String inCodon) {

    //if()
    if (next == null) {
      next = new AminoAcidLL(inCodon);
    }
    // if(inCodon.equals())//this node has this codon incrCodons( AminoAcidResources.getAminoAcidFromCodon(inCodon););
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)) {
      incrCodons(inCodon);
    } else {
      if (next != null) {
        next.addCodon(inCodon);
      }

    }
  }

  private void incrCodons(String inCodon) {

    for (int i = 0; i < codons.length; i++) {
      if (codons[i].equals(inCodon.toUpperCase())) {
        counts[i]++;
      }
    }
  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount() {

    int total = 0;
    for (int i = 0; i < counts.length; i++) {
      total = counts[i] + total;
    }
    return total;


  }//Not recursive

  //Helper methods for test cases to get compare to expected output
  public int[] getCount() {
    return counts;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
   *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList) {
    return Math.abs(totalCount() - inList.totalCount());
  }
  //creates list of amino acid
  //turn from linked list to Char
  //base case (if next == null)

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
   *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList) {
    int diff = 0;
    for (int i = 0; i < codons.length; i++) {
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts.
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList) {
    sort(inList);
    if (inList == null) {
      int a = totalCount();
      return this.totalCount();
    }
    if (this == null) {
      int a = this.aminoAcidCompare(inList.next);
      return a;
    }
    if (this == inList) {
      return 0;
    }
    return 0;
  }
  //have to recursevly compare counts between two linked lists
  // a totalCount();
  //uses total diff

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList) {
    sort(inList);


    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList() {


    if (this.next == null) {
      return new char[]{aminoAcid};
    }

    char[] a = next.aminoAcidList();//abcd
    char[] b = new char[a.length + 1];//

    b[0] = aminoAcid;
    for (int i = 0; i < a.length; i++) {
      b[i + 1] = a[i];
    }

    return b;
  }

  public char[] getaminoAcidListArray() {
    return aminoAcidList();
  }
  //combination of loops and recursion.
  //just want to convert list into char array
//counts of all the nodes

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts() {


    if (this.next == null) {
      return new int[]{aminoAcid};
    } else {
      int[] a = next.aminoAcidCounts();
      int[] b = new int[a.length + 1];

      b[0] = aminoAcid;
      for (int i = 0; i < a.length; i++) {
        b[i + 1] = a[i];
      }
      return b;
    }

  }

  //similar to aminoAcidlist

  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted() {

    if(next == sort(next)) {
      return true;
    }
    return false;
  }//recursive and only compares amino acid
  //checks if all nodes are sorted

  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {

    int n = inSequence.length();
    String temp = "";
    int count = 0;
    if (n == 0) {
      return null;
    }
    temp = inSequence.substring(0, 3);
    System.out.print(temp + " ");
    AminoAcidLL list = new AminoAcidLL(temp);
    for (int i = 3; i < n; i = i + 3) {

      count = i + 3;
      System.out.print(inSequence.substring(i, count) + " ");
      if (inSequence.substring(i, count).equals("UGA") || inSequence.substring(i, count).equals("UAG") || inSequence.substring(i, count).equals("UAA")) {
        return list;
      } else {
        list.addCodon(inSequence.substring(i, count));
      }
    }
    return list;

  }

  //Helper methods for test cases to get compare to expected output
  public AminoAcidLL getNext() {
    return next;
  }
  //Helper methods for test cases to get compare to expected output
  public char getAminoAcid() {
    return aminoAcid;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {

//    AminoAcidLL temp = inList;
//    AminoAcidLL finalList = inList;
//    int x = 0;
//    while(inList != null){
//    char a = inList.aminoAcid;
//    if(a == 'A'){x = 1;};
//    else if(a == 'C'){x = 2;} ;
//    else if(a == 'D'){x = 3;} ;
//    else if(a == 'E'){x = 4;} ;
//    else if(a == 'F'){x = 5;} ;
//    else if(a == 'G'){x = 6;} ;
//    else if(a == 'H'){x = 7;} ;
//    else if(a == 'I'){x = 8;} ;
//    else if(a == 'K'){x = 9;} ;
//    else if(a == 'L'){x = 10;} ;
//    else if(a == 'M'){x = 11;} ;
//    else if(a == 'N'){x = 12;} ;
//    else if(a == 'P'){x = 13;} ;
//    else if(a == 'Q'){x = 14;} ;
//    else if(a == 'R'){x = 15;} ;
//    else if(a == 'S'){x = 16;} ;
//    else if(a == 'T'){x = 17;} ;
//    else if(a == 'V'){x = 18;};
//    else if(a == 'W'){x = 19;};
//
//      char b = inList.next.aminoAcid;
//      if(b == 'A'){x = 1;};
//    else if(b == 'C'){x = 2;} ;
//    else if(b == 'D'){x = 3;} ;
//    else if(b == 'E'){x = 4;} ;
//    else if(b == 'F'){x = 5;} ;
//    else if(b == 'G'){x = 6;} ;
//    else if(b == 'H'){x = 7;} ;
//    else if(b == 'I'){x = 8;} ;
//    else if(b == 'K'){x = 9;} ;
//    else if(b == 'L'){x = 10;} ;
//    else if(b == 'M'){x = 11;} ;
//    else if(b == 'N'){x = 12;} ;
//    else if(b == 'P'){x = 13;} ;
//    else if(b == 'Q'){x = 14;} ;
//    else if(b == 'R'){x = 15;} ;
//    else if(b == 'S'){x = 16;} ;
//    else if(b == 'T'){x = 17;} ;
//    else if(b == 'V'){x = 18;};
//    else if(b == 'W'){x = 19;};
//
//      for (int i = 0; i < n-1; i++)
//      {
//        // Find the minimum element in unsorted array
//        int min_idx = i;
//        for (int j = i+1; j < n; j++)
//          if (arr[j] < arr[min_idx])
//            min_idx = j;
//
//        // Swap the found minimum element with the first
//        // element
//        int temp = arr[min_idx];
//        arr[min_idx] = arr[i];
//        arr[i] = temp;
//      }
//    }
    return inList;
  }

}