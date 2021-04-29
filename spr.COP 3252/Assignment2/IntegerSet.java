////////////////////////////////////////////////////////////////////////////////
//  Made by Benjamin Bishop                                                   //
//  27 Jan. 2021                                                              //
//  This is 'IntegerSet.java'                                                 //
//  It will create a empty set of integers and have                           //
//  options to allow manipulation of the set.                                 //
////////////////////////////////////////////////////////////////////////////////

public class IntegerSet{
  //array of booleans named set
  private boolean set[];
  //constructor
  public IntegerSet(){
    //for a size of 0-100 booleans
    set = new boolean[101];
  }
  //creates a set that is the theorical union of two sets
  public IntegerSet union(IntegerSet iSet){
    //create a set to contain the result
    IntegerSet result = new IntegerSet();
    //loop though the set to get bool
    for (int i=0;i<set.length ;i++ ) {
      //setting set and iSet to true if Either are true and send to result
      if(set[i]||iSet.set[i]){
        result.set[i]=true;
      }
    }
    return result;
  }
  //creates a set that contains the theorical intersection of two sets
  public IntegerSet intersection(IntegerSet iSet){
    //create a set to contain the result
    IntegerSet result = new IntegerSet();
    for (int i=0;i<set.length;i++){
      //setting set and iSet to true if Both are present then send to result
      if(set[i]&&iSet.set[i]){
        result.set[i]=true;
      }
    }
    return result;
  }
  //sets and elements to true
  public IntegerSet insertElement(int data){
    //valid range of data before setting data to true
    if(data>=0&&data<set.length){
      set[data]=true;
    }
    //returns set to allow for cascading
    return this;
  }
  //sets array elements to false
  public IntegerSet deleteElement(int data){
    //valid range of data before setting data to false
    if(data>=0&&data<set.length){
      set[data]=false;
    }
    //returns set to allow for cascading
    return this;
  }
  //compares two sets and return true or false;
  public boolean isEqualTo(IntegerSet iSet){
    for (int i=0;i<set.length;i++){
      //if set doesn't equal iSet return false
      if(this.set[i]!=iSet.set[i]){
        return false;
      }
    }
    //they are equal
    return true;
  }
  //returns a string, of the set of bools as a list in ascending order
  public String toString(){
    String list="";
    //looping to find any value that is true and appending to list.
    for (int i=0;i<set.length;i++) {
      if(set[i]){
        list+=i+" ";
      }
    }
    //to display and empty sets
    if(list.equals("")){
      return"---";//indicating an empty set
    }
    else{
      //removes last white space, It looked neat to add
      return list.trim();
    }
  }
}//end of class
